import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class VueCharger extends VueModele implements MouseListener,Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedReader input;

	public VueCharger(Donnees parent, int x, int y, int l, int h, String txt,
			Color backgroundTitre, Color background) {
		super(parent, x, y, l, h, txt, backgroundTitre, background);
		this.refresh();
	
	    
	}
	
	public void refresh(){

		JPanel layout = new JPanel();
		VueSimpleTerrain courant;
		String titre, terrain;
		int taille = this.getWidth()-6;
		int tailleMap = this.getWidth()-30;
		int hauteur = 0;
		int ecart = 20;
		int i = 3;
		int j = 0;
		int compteur = 0;
		
		
		try {
			FileReader file = new FileReader(new File(Const.PATHLVL));
			input = new BufferedReader(file);
			String ligne;
			ligne = input.readLine();
			while(ligne != null){
				if (ligne.length() > 0){
					//ligne valide
					String[] elements = ligne.split(Const.SPLIT);
					terrain = elements[0];
					titre = elements[1];
					courant = new VueSimpleTerrain(this,compteur,terrain,titre);
					layout.add(courant);
					//Paramétres de position
					courant.setBounds(i, j,tailleMap,tailleMap+90);
			  		layout.add(courant);
			  		j += tailleMap+110;
			  		hauteur += tailleMap+70+2*ecart;	
				}
				compteur ++;
				ligne= input.readLine(); 
			}
		} catch (IOException e) {
			Fenetre.info("Impossible de lire le fichier contenant les niveaux.");
		}


		//Enfin on ajoute notre layout dans un conteneur avec scrollbat
		layout.setLayout(null);
		layout.setPreferredSize(new Dimension(taille-3,hauteur));
		layout.setBounds(3, 60, taille, hauteur);  	
		JScrollPane bar = new JScrollPane();
		bar.getVerticalScrollBar().setUnitIncrement(20);
		bar.getViewport().add(layout);
		bar.setBounds(3, 50, taille-2, this.getHeight()-60);  	
		this.add(bar);
				 
		
	}


	@Override
	public void update(Observable o, Object arg1) {
		if (((String)arg1).equals("charger")){
			this.removeAll();
			this.refresh();
			this.setVisible(true);
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//clic sur un terrain
		try{
			if(!parent.getMatch() && !parent.getRep()){
				VueSimpleTerrain terrain =(VueSimpleTerrain) e.getSource();
				parent.getTerrain().init(terrain.getMap());
				parent.updateVueTerrain();
			}
		}
		catch(Exception e2){
		}
		//Clic sur supprimer
		try{
			JButton suppr = (JButton) e.getSource();
			VueSimpleTerrain terrain = (VueSimpleTerrain) suppr.getParent();
			this.suppr(terrain.getNumero());
			this.update(null, "charger");
		}
		catch(Exception e2){

		}
		
	}

	private void suppr(int numero) {
		//Je lis le fichier sauf la ligne à supprimer et j'écrit le tout dans mon fichier
		String buffer = "";
		try {
			input = new BufferedReader(new FileReader(new File(Const.PATHLVL)));
			String ligne = input.readLine();
			int i = 0;
			int debut = 0;
			while(ligne != null){
				//On enregistre pas la ligne si i == numero
				if(i != numero ){
					if(debut != 0) {
						//Si on est pas au début on ajoute un saut de ligne
						buffer += '\n';
					}
					buffer += ligne;
					debut ++;
				}
				i++;
				ligne = input.readLine();
				
			}
			//là on a notre buffer, on supprime le fichier et on le réecrit avec buffer en false (sans append)
			FileWriter file_w = new FileWriter(new File(Const.PATHLVL),false);
			BufferedWriter output = new BufferedWriter(file_w);
			output.write(buffer);
			output.flush();
			output.close();
			Fenetre.info("Votre terrain a bien été supprimé. \nVeuillez patienter quelques instants avant que votre terrain disparaisse de votre application.");
		 }
		 catch (FileNotFoundException e) {
			 Fenetre.info("Le terrain n'a pas été supprimé: \n\tfichier de configuration introuvable");
		} catch (IOException e) {
			Fenetre.warning("Le terrain n'a pas été supprimé: \n\terreur d'entrée/sortie (code:1).");
		}
		
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
