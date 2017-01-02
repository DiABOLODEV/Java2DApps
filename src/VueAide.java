import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class VueAide extends JPanel{
	public VueAide(){
		int h = 340;
	    int l = 200;
	    this.setSize(l*6,h*2+23);
	    
	    
	}
	protected void paintComponent(Graphics g) {

	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);

	        g2d.drawImage(Imageuh.createImage("aide.png", ""),0,0,1191,649, null);
	    
	}

}
