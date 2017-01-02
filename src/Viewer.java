import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;


public class Viewer extends JFrame{
	public Viewer(){
		this.setTitle("Viewer");
	 	this.setIconImage(Imageuh.createImageIcon("icon.png","icone").getImage());
	 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setResizable(false);
	  	
	    Container content = getContentPane();
	    content.setBackground(Color.white);
	    content.setLayout(null); 
	    this.setSize(800, 800);
	    this.add(new VueSimpleTerrain( 2, "0000000020000003010000100010010001000010200000030000000000000000", "map"));
	    this.setVisible(true);
	}
}
