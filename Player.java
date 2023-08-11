import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
//IMPORT FONT//

public class Player{
	int y;
	int x;
	Color color;
	int yLoc;
	int xLoc;
	int player;
	
	public Player(int a, int b, Color c, int a2, int b2, int p)
	{
		x = a;
		y = b;
		color = c;
		xLoc = a2;
		yLoc = b2;
		player = p;
	}
	
	public void setXY(int a,int b){
		x = a;
		y = b;
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
	}
}