

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class VueBacteria extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	private VueTerrain terrain;
	private VueInfos infos;
	private VueIA ia;
	private VueConfiguration configuration;
	private Match match = null;
	private Donnees donnees;
	private VueConfigurationTerrain configTerrain;
	private VueCharger charger;
	private VueReplay replay;
	private VueWin win;
	
	 public VueBacteria() {
		 	//pannel droit = 200 et
		 	//pannel gauche = 560 (70*8) et 560+50 =610
		 	//total = 760
		 	/*Avant tout on regarde si le fichier de config est crée :)*/
		 	if(Fichier.creerFichier(Const.PATHLVL)){
		 		this.dispose();
		 	}
		 	this.setTitle("Bacteflip");
		 	this.setIconImage(Imageuh.createImageIcon("icon.png","icone").getImage());
		 	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  	this.setResizable(false);
		  	
		    Container content = getContentPane();
		    content.setBackground(Color.white);
		    content.setLayout(null); 
		    
		    
		    donnees = new Donnees();
		    donnees.addObserver(this);
		   
		    /*
		    VueModele credits = new VueModele(donnees,0, 0, 560, 610,"" +
		    		"Informations: Bactérization a été développé par Thibault Gauthier en Java en 2012"
		    		,new Color(2,133,219));
		    credits.setBounds(0,600,this.getWidth(),60);
		    this.add(credits);*/
		    
		    int h = 340;
		    int l = 200;
		    
		    this.setSize(l*6,h*2+23); //23 barre de menu
		    this.setLocationRelativeTo(null);
		    //barre de menu
		    this.setJMenuBar(new VueMenu(this));
		   
		    win = new VueWin();
		    content.add(win);
		    content.setComponentZOrder(win, 0);
		    win.setVisible(true);
		    VueLogo logo = new VueLogo();
		    content.add(logo);
		    content.setComponentZOrder(logo, 1);
		    VueInformations informations = new VueInformations(donnees);
		    donnees.addObserver(informations);
		    content.add(informations);
		    content.setComponentZOrder(informations, 0);
		    charger = new VueCharger(donnees, l*5, 0,l,h*2,"Charger un terrain",  Const.TITRE1,Const.BG1);
		    content.add(charger);
		
		    donnees.addObserver(charger);
		    
		    infos = new VueInfos(donnees, 0, 0,l,(h/2)+30,"Informations", Const.TITRE2,Const.BG2);
		    content.add(infos);
		
		    donnees.addObserver(infos);
		    
			 
		    terrain = new VueTerrain(donnees,match,l, 0, l*3, h*2,"Terrain de jeu",Const.TITRE1,Const.BG1);
		    content.add(terrain);

		    donnees.addObserver(terrain);
		    
		    replay = new VueReplay(donnees,this, 0, (h/2)+30,l,(h/2),"Replay", Const.TITRE2,Const.BG2);
		    content.add(replay);
	
		    donnees.addObserver(replay);
		    
		    configuration = new VueConfiguration(this,donnees, 0, h+30, l,h-30,"Configuration Générale", Const.TITRE2,Const.BG2);
		    content.add(configuration);
	
		    
		    ia = new VueIA(donnees, this, l*4, 0, l, h,"Ordinateur",Const.TITRE2,Const.BG2);
		    content.add(ia);
	
		    
		    configTerrain = new VueConfigurationTerrain(donnees, l*4,h,l,h,"Configuration du Terrain",Const.TITRE2,Const.BG2);
		    content.add(configTerrain);

		
		    //On regarde que le fichier existe ^^
		  //Un petit spashScreen avant de mettre la fram a  true :)
		    //Fenetre.splash("u=info",1000);
		    
		    this.setVisible(true);
    }
	 
	 public VueInfos getInfos() {
		return infos;
	}/*
	 public VueAide getAide(){
		 return aide;
	 }*/
	/*Permet de définir la configuaration de jeu*/
	 public void jouer(){
		 //Si il a déjà un match on l'arrête
		 if (match != null){
			match.arreter();
			donnees.setMatch(false);
		}
		match = new Match(donnees, terrain);
		match.start();
		this.terrain.setMatch(match);
		donnees.setMatch(true);
		
		 
	 }
		public void update(Observable o, Object arg) {
			if ((String) arg == "jouer"){
				this.jouer();
			}
		}
		
	public VueTerrain getTerrain(){
		return terrain;
	}
	public Match getMatch() {
			return match;
		}
	public static void main(String[] args) {
		 
		new VueBacteria();

	}

	

}
