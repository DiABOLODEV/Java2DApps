
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;




public class VueCase extends JButton implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8375245329089388132L;
	private int etat;
	private int etatPrec;
	private VueTerrain parent;
	public int x;
	public int y;
	public VueCase(VueTerrain parent, int etat ,int x, int y) {
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.etat = etat;
		this.etatPrec = etat;
		this.setBorder(null);
		this.setContentAreaFilled(false); //Pas de fond bouton
		this.setFocusable(false); //PAs de rectangle de sélection
		this.attribImg();
	}
	
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etatPrec = this.etat;
		this.etat = etat;
		this.attribImg();
	}
	public void setCliquable(boolean t){
		this.removeMouseListener(this);
		if (t){
			this.addMouseListener(this);
			this.setCursor(new Cursor(Cursor.HAND_CURSOR ));
		}
		else{
			this.removeMouseListener(this);
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR ));
		}
		
	}
	public void attribImg(){
		ImageIcon img = null;

		//Nouvelle verte
		if (etatPrec == 0 && etat == 2){
			
		}
		if (etatPrec == 0 && etat == 3){
			
		}
		
		if (etatPrec == 2 && etat == 3){
			
		}
		if (etatPrec == 3 && etat == 2){
			
		}
		
		if(etat ==-1){
			img = Imageuh.createImageIcon("case0.png", "Case vide");

		}
		if(etat ==1){
			img = Imageuh.createImageIcon("case1.png", "Case pleine");
		}
		else if(etat == 2){
			img = Imageuh.createImageIcon("case2.png", "Case verte");
		}
		else if(etat == 3){
			img = Imageuh.createImageIcon("case3.png", "Case rouge");
		}
		else if(etat == 4){
			img = Imageuh.createImageIcon("case4.png", "Case de duplication");
		}
		else if(etat == 5){
			img = Imageuh.createImageIcon("case5.png", "Case de déplacement");
		}
		else if(etat == 6){
			img = Imageuh.createImageIcon("case6.png", "");
		}
		else if(etat == 7){
			img = Imageuh.createImageIcon("case7.png", "");
		}
		else if(etat == 8){
			img = Imageuh.createImageIcon("case8.png", "");
		}
		else if(etat == 9){
			img = Imageuh.createImageIcon("case9.png", "");
		}

		/*On attribut img Ã  la case*/
		this.setIcon(img);
		
	}


	//Jpeux pas mettre getX c'est redéfinition de la position
	public int getI() {
		return x;
	}
	public int getJ() {
		return y;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
			
	
		
		
		if(parent.isEditable()){ //Si on est mode édition du terrain (match fini || match null)
			this.parent.drop(this);
			Son.pop();
		}
		else if(parent.isReplay()){
			
			/*On permet au joueur de jouer un coup*/
			if(etat == 2 || etat == 3){
				
				//On affiche les possibilitées
				parent.setJoueurReplay(etat);
				parent.terrainResetPossibilite();
				parent.terrainAfficherPossibilite(x,y);
				parent.setOrigine(this);
			}
			else if (etat == 4){
				parent.terrainResetPossibilite();
				parent.terrainJouerRep(x,y,0); //type= 0 = duplication on garde l'origine pour l'animation
			}
			else if (etat == 5){
				parent.terrainResetPossibilite();
				parent.terrainJouerRep(x,y,1);
			}
		}
		else{
			
			/*On permet au joueur de jouer un coup*/
			if(etat == 2 || etat == 3){
				
				//On affiche les possibilitées
				parent.terrainResetPossibilite();
				parent.terrainAfficherPossibilite(x,y);
				parent.setOrigine(this);
			}
			else if (etat == 4){
				parent.terrainResetPossibilite();
				parent.terrainJouer(x,y,0); //type= 0 = duplication on garde l'origine pour l'animation
			}
			else if (etat == 5){
				parent.terrainResetPossibilite();
				parent.terrainJouer(x,y,1);
			}
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



}
