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
	private JButton colorChooseButton;
	private Font font;
//	private ArrayList<Block> blocks = new ArrayList<Block>();
	private Block[][] blocks = new Block[3][3];
	private ArrayList<Player> players = new ArrayList<Player>();
	private int turn;
	private int[][] victory = new int[3][3];
	private Color deep_blue;
	private Color deep_green;
	private Color deep_red;
	private boolean won;
	private Color p1Color;
	private Color p2Color;
	private ArrayList<Color> colors = new ArrayList<Color>();
	private ArrayList<JButton> setColor1 = new ArrayList<JButton>();
	private ArrayList<JButton> setColor2 = new ArrayList<JButton>();
	private int scHolder;
	private JButton resetButton;
	
	public Screen()
    {
		font = new Font("Arial", Font.PLAIN, 25);
			
		turn = 0;
		screen = "main";
		won = false;
		
		startGameButton = new JButton("Start Game");
		startGameButton.setBounds(00,00,200,30); //Set the location and size
        startGameButton.addActionListener(this); //Add the listener to the button
		
		helpButton = new JButton("Help");  //Instantiate the button
        helpButton.setBounds(100,00,200,30); //Set the location and size
        helpButton.addActionListener(this); //Add the listener to the button
		
		backButton = new JButton("Main Menu");  //Instantiate the button
        backButton.setBounds(00,50,200,30); //Set the location and size
        backButton.addActionListener(this); //Add the listener to the button
		
		colorChooseButton = new JButton("Choose your Colors");
		colorChooseButton.setBounds(00,50,200,30);
		colorChooseButton.addActionListener(this);
		
		resetButton = new JButton("Restart the Game");
		resetButton.setBounds(250,335,200,30);
		resetButton.addActionListener(this);
		
		colors.add(new Color(255,0,0));
		colors.add(new Color(0,255,0));
		colors.add(new Color(0,0,255));
		colors.add(new Color(255,255,0));
		colors.add(new Color(0,255,255));
		colors.add(new Color(255,0,255));
		
		setColor1.add(new JButton("Set player 1 color to Color 1"));
		setColor1.add(new JButton("Set player 1 color to Color 2"));
		setColor1.add(new JButton("Set player 1 color to Color 3"));
		setColor1.add(new JButton("Set player 1 color to Color 4"));
		setColor1.add(new JButton("Set player 1 color to Color 5"));
		setColor1.add(new JButton("Set player 1 color to Color 6"));
		setColor2.add(new JButton("Set player 2 color to Color 1"));
		setColor2.add(new JButton("Set player 2 color to Color 2"));
		setColor2.add(new JButton("Set player 2 color to Color 3"));
		setColor2.add(new JButton("Set player 2 color to Color 4"));
		setColor2.add(new JButton("Set player 2 color to Color 5"));
		setColor2.add(new JButton("Set player 2 color to Color 6"));
		
		int sc1Counter = 0;
		for (JButton each:setColor1){	
			each.setBounds(00,400 + 50*sc1Counter,200,30);
			each.addActionListener(this);
			++sc1Counter;
		}
		int sc2Counter = 0;
		for (JButton each:setColor2){	
			each.setBounds(450,400 + 50*sc2Counter,200,30);
			each.addActionListener(this);
			++sc2Counter;
		}
		
		deep_green = new Color (0,127,0);
		deep_red = new Color (127,0,0);
		deep_blue = new Color (0,0,127);
		
		p1Color = deep_blue;
		p2Color = deep_green;
		
		for (int i = 0; i < 3; ++i){
			for (int j = 0; j < 3; ++j){
				blocks[i][j] = new Block(i*250,j*250,Color.white);
				victory[i][j] = -10*(i+1) + -1 * (j+1);
			}
		}
	
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
			add(colorChooseButton);
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

		if (screen.equals("colorSelect")){
			add(backButton);
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.black);
			gBuff.setFont(font);
			gBuff.drawString("Which color will each player play?", 0, 20);
			
			for (JButton each:setColor1){	
				add(each);
			}for (JButton each:setColor2){	
				add(each);
			}
			
			gBuff.setColor(Color.black);
			gBuff.fillRect(0,150,200,200);
			gBuff.fillRect(450,150,200,200);
			gBuff.setColor(p1Color);
			gBuff.fillOval(25,175,150,150);
			gBuff.setColor(p2Color);
			gBuff.fillOval(475,175,150,150);
		}

		if (screen.equals("tie")){
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.black);
			gBuff.setFont(font);
			gBuff.drawString("Congratulations Players for the Tie", 0, 20);
			add(resetButton);
		}if (screen.equals("victory2")){
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.black);
			gBuff.setFont(font);
			gBuff.drawString("Congratulations Player X", 0, 20);
			add(resetButton);
		}
		if (screen.equals("victory1")){
			gBuff.setColor(Color.white);
			gBuff.fillRect(0,0,700,700);
			gBuff.setColor(Color.black);
			gBuff.setFont(font);
			gBuff.drawString("Congratulations Player O", 0, 20);
			add(resetButton);
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
			if (!won){
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
								players.add(new X(i*250,j*250,p1Color,i,j));
								++turn;
								victory[i][j] = 1;
							}
							else {
								blocks[i][j].setHeld(true);
								players.add(new O(i*250,j*250,p2Color,i,j));
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
						//System.out.println("GG");
						if (turn%2==0){
							screen = "victory1";
						} else { 
							screen = "victory2"; 
						}
						won = true;
					}
				
				// check if impossible for either player to win
				if (turn>=9){
					for (int i = 0; i < 3; ++i){
						for (int j = 0; j < 3; ++j){
							blocks[i][j].win(deep_red);
						}
					}
					screen = "tie";
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
		}if (e.getSource() == helpButton){
			screen = "help";
			removeAll();
			repaint();
		}if (e.getSource() == backButton)
		{
			screen = "main";
			removeAll();
			repaint();
		}if (e.getSource() == colorChooseButton){
			screen = "colorSelect";
			removeAll();
			repaint();
		}if (e.getSource() == resetButton){
			screen = "main";
			
			for (int i = 0; i < 3; ++i){
				for (int j = 0; j < 3; ++j){
					victory[i][j] = -10*(i+1) + -1 * (j+1);
					blocks[i][j].win(Color.white);
					blocks[i][j].setHeld(false);
				}
			}
			
			players.clear();
			
			won = false;
			turn = 0;
			
			removeAll();
			repaint();
		}
		
		for (JButton each:setColor1){	
			if (e.getSource() == each){
				scHolder = setColor1.indexOf(each);
				p1Color = colors.get(scHolder);
				
				removeAll();
				repaint();
			}
		}for (JButton each:setColor2){	
			if (e.getSource() == each){
				scHolder = setColor2.indexOf(each);
				p2Color = colors.get(scHolder);
				
				removeAll();
				repaint();
			}
		}
	}
}