import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Font;
//IMPORT FONT//

public class O extends Player{
	public O(int a, int b, Color c, int a2, int b2){ 
		super(a,b,c,a2,b2,2);
	}
	
	public void drawMe(Graphics g){
		super.drawMe(g);
		g.setColor(color);
		g.fillOval(getX()+10,getY()+10,180,180);
		g.setColor(Color.white);
		g.fillOval(getX()+35,getY()+35,130,130);
	}
}