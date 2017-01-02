import java.awt.Color;
import java.awt.Container;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


public class VueBacteriaOnline extends JFrame implements Observer{
	private Donnees donnees;
	private VueTerrain terrain;
	private Match match = null;
	private VueInfos infos;
	
	public VueBacteriaOnline() {
		this.setTitle("Bacteflip Online");
	 	this.setIconImage(Imageuh.createImageIcon("icon.png","icone").getImage());
	 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	this.setResizable(false);
	    int h = 340;
	    int l = 200;
	    
	    this.setSize(l*3+5,h*2+150); //23 barre de menu
	  	
	    Container content = getContentPane();
	    content.setBackground(Color.white);
	    content.setLayout(null); 
	    
	    
	    donnees = new Donnees();
	    donnees.addObserver(this);
		 
	    terrain = new VueTerrain(donnees,match,0, 0, l*3, h*2,"Terrain de jeu",Const.TITRE1,Const.BG1);
	    content.add(terrain);
	    
	    infos = new VueInfos(donnees, 0, h*2,l*3,150,"Informations", Const.TITRE2,Const.BG2);
	    content.add(infos);


	    donnees.addObserver(terrain);
	    donnees.addObserver(infos);
	    
	    setVisible(true);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
