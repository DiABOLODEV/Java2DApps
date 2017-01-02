import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;


public class VueWin extends JPanel {
	public VueWin(){
		int h = 340;
	    int l = 200;
	    this.setSize(l*6,h*2+23);
	    
	    
	}
	protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.drawImage(Imageuh.createImage("bacteflip.png", ""),400,25,223,45, null);
    
}
}
