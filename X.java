import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
//IMPORT FONT//

public class X extends Player{
	public X(int a, int b, Color c, int a2, int b2){ 
		super(a,b,c, a2, b2, 1);
	}
	
	public void drawMe(Graphics g){
		super.drawMe(g);
		g.setColor(color);
		g.fillRect(getX()+5,getY()+75,190,50);
		g.setColor(color);
		g.fillRect(getX()+75,getY()+5,50,190);
	}
}