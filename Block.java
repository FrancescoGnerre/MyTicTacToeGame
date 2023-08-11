import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
//IMPORT FONT//

public class Block{
	private int y;
	private int x;
	private Color color;
	private boolean held;
	
	public Block(int a, int b, Color c)
	{
		x = a;
		y = b;
		color = c;
		held = false;
	}
	
	public int getX(){
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void drawMe(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x,y,200,200);
	}
}