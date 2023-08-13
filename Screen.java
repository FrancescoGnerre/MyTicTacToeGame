import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/* sound /\                    */
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
/* graphics /\                 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/* UI /\                       */
import java.util.ArrayList;
 
public class Screen extends JPanel implements KeyListener, MouseListener, ActionListener
{
	private String screen;
    private BufferedImage buffered;
	private JButton startGameButton;
	private JButton helpButton;
	private JButton backButton;
	private Font font;
//	private ArrayList<Block> blocks = new ArrayList<Block>();
	private Block[][] blocks = new Block[3][3];
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn;
	private int[][] victory = new int[3][3];
	private Color deep_blue;
	private Color deep_green;
	private Color deep_red;
	
	public Screen()
    {
		font = new Font("Arial", Font.PLAIN, 25);
			
		turn = 0;
		screen = "main";
		
		startGameButton = new JButton("Start Game");
		startGameButton.setBounds(00,00,200,30); //Set the location and size
        startGameButton.addActionListener(this); //Add the listener to the button
		
		helpButton = new JButton("Help");  //Instantiate the button
        helpButton.setBounds(00,50,200,30); //Set the location and size
        helpButton.addActionListener(this); //Add the listener to the button
		
		backButton = new JButton("Main Menu");  //Instantiate the button
        backButton.setBounds(00,50,200,30); //Set the location and size
        backButton.addActionListener(this); //Add the listener to the button
		
		deep_green = new Color (0,212,0);
		deep_red = new Color (212,0,0);
		deep_blue = new Color (0,0,212);
		
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
				blocks[i][j] = new Block(i*250,j*250,Color.white);
				victory[i][j] = -10*(i+1) + -1 * (j+1);
			}
		}
	
/*	
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
			if (turn%2 == 0){
				players.add(new X(i*250,j*250,deep_blue,i,j));
				}
			else {				
				players.add(new O(i*250,j*250,deep_green,i,j));
				}
				++turn;
			}
		}
	*/
	
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
	}
	public Dimension getPreferredSize() 
    {
		//Sets the size of the panel
		return new Dimension(700,700);
    }
	 
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
     
        //Create Buffered
        if( buffered == null )
        {
            buffered = (BufferedImage)(createImage( getWidth(), getHeight() ) );
        }
        
        //Create a temporary graphics buffered to draw
        Graphics gBuff = buffered.createGraphics();

		if (screen.equals("main")){
			add(startGameButton); //Add button to JPanel
			add(helpButton);
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
		}

		if (screen.equals("help")){
			add(backButton);
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.black);
			gBuff.setFont(font);
			gBuff.drawString("This is a game of tic-tac-toe. glhf", 0, 20);
		}

		if (screen.equals("game")){
			gBuff.setColor(Color.black);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.white);
			
			for (int i = 0; i < 3; ++i){
				for (int j = 0; j < 3; ++j){
					blocks[i][j].drawMe(gBuff);
				}
			}
				
			for (Player each: players){
				each.drawMe(gBuff);
			}
		}
		g.drawImage(buffered, 0, 0, null);
	}
	
	//ANIMATE
 
    public void animate()
    {
        while(true)
        {
            //wait for .01 second
            try 
			{
                Thread.sleep(10);
            }
			catch(InterruptedException ex) 
			{
                Thread.currentThread().interrupt();
            }
		
			repaint();
		}
	}
	
	//KEYS
	//http://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
	//Key Codes /\
	
	public void keyPressed(KeyEvent e)
	{ 
		
	}
    public void keyReleased(KeyEvent e)
	{
		
	}
    public void keyTyped(KeyEvent e) 
	{
		
	}
	
	// MOUSE
    public void mouseClicked(MouseEvent e)
	{
		
	}
	public void mousePressed(MouseEvent e) 
    {
		
    }
    public void mouseReleased(MouseEvent e) 
	{
		if (screen.equals("game")){
			for (int i = 0; i < 3; ++i){
				for (int j = 0; j < 3; ++j){
					if (blocks[i][j].isHeld()==false &&
						e.getX()>blocks[i][j].getX() && 
						e.getX()<blocks[i][j].getX()+250 && 
						e.getY()>blocks[i][j].getY() && 
						e.getY()<blocks[i][j].getY()+250){
							//
						if (turn%2==0){
							blocks[i][j].setHeld(true);
							players.add(new X(i*250,j*250,deep_blue,i,j));
							++turn;
							victory[i][j] = 1;
						}
						else {
							blocks[i][j].setHeld(true);
							players.add(new O(i*250,j*250,deep_green,i,j));
							++turn;
							victory[i][j] = 2;
						}
					}
				}
			}
			
			//check for victory
			if (victory[0][0] == victory[0][1] &&  victory[0][0]== victory[0][2] ||
				victory[1][0] == victory[1][1] && victory[1][0] == victory[1][2] ||
				victory[2][0] == victory[2][1] && victory[2][0] == victory[2][2] ||
				victory[0][0] == victory[1][1] && victory[0][0] == victory[2][2] ||
				victory[2][0] == victory[1][1] && victory[2][0] == victory[0][2] ||
				victory[0][0] == victory[1][0] && victory[0][0] == victory[2][0] ||
				victory[0][1] == victory[1][1] && victory[0][1] == victory[2][1] ||
				victory[0][2] == victory[1][2] && victory[0][2] == victory[2][2]) {
					System.out.println("GG");
				}
			
			// check if impossible for either player to win
			if (turn>=9){
				for (int i = 0; i < 3; ++i){
					for (int j = 0; j < 3; ++j){
						blocks[i][j].win(deep_red);
					}
				}
			}
		}
	}
    public void mouseEntered(MouseEvent e) 
	{
		
	}
    public void mouseExited(MouseEvent e) 
	{
		
	}
	
	//ACTIONS
	public void actionPerformed(ActionEvent e)
    {
		if (e.getSource() == startGameButton){
			screen = "game";
			removeAll();
			repaint();
		}if (e.getSource() == helpButton)
		{
			screen = "help";
			removeAll();
			repaint();
		}if (e.getSource() == backButton)
		{
			screen = "main";
			removeAll();
			repaint();
		}
	}
}