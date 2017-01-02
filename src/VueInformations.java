


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class VueInformations  extends JPanel implements Observer{
	Donnees parent;
	public VueInformations(Donnees parent){
		this.parent = parent;
		int h = 340;
	    int l = 200;
	    this.setSize(l*6,h*2+23);
	    
	    
	}
	protected void paintComponent(Graphics g) {
			
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setRenderingHint(
	            RenderingHints.KEY_ANTIALIASING,
	            RenderingHints.VALUE_ANTIALIAS_ON);
	        if(parent.getQuiJoue() == parent.getCouleurV()){
	        	g2d.drawImage(Imageuh.createImage("infovert2.png", ""),260,15,62,62, null);
	        	g2d.drawImage(Imageuh.createImage("inforouge.png", ""),660,15,62,62, null);
			}
			else{
				g2d.drawImage(Imageuh.createImage("infovert.png", ""),260,15,62,62, null);
				g2d.drawImage(Imageuh.createImage("inforouge2.png", ""),660,15,62,62, null);
			}
	        
	        
	        g2d.setColor(Const.BG2);
	        g2d.setFont(new Font("sans-serif",Font.BOLD,15));
	        int xvert = 287;
	        int xrouge =687;
	        if((""+parent.getScoreJ()).length()>1){
	        	xvert = 283;
	        }
	        if((""+parent.getScoreJ2()).length()>1){
	        	xrouge = 683;
	        }
	        g2d.drawString(""+parent.getScoreJ(),xvert,52);
	        g2d.drawString(""+parent.getScoreJ2(),xrouge,52);
	       
	    
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		if((String)arg1 == "infos"){
			
			this.paintComponent(getGraphics());
			
		}
		
	}

}
