import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;



public class VueCaseConfig extends JButton implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2585158561172612476L;
	/**
	 * 
	 */

	private int etat;
	VueConfigurationTerrain parent ;
	public VueCaseConfig(VueConfigurationTerrain parent, int etat) {
		this.parent = parent;
		this.etat = etat;
		this.setBorder(null);
		this.setContentAreaFilled(false); //Pas de fond bouton
		this.setFocusable(false); //PAs de rectangle de sélection
		this.attribImg();
		this.addActionListener(this);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

	}

	public void attribImg(){
		ImageIcon img = null;
		
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

		/*On attribut img Ã  la case*/
		this.setIcon(img);
		
	}

	public void dragOn(){
		this.setContentAreaFilled(true);
		this.setBackground(new Color(0,153,255));
	}
	public void dragOff(){
		this.setContentAreaFilled(false); //Pas de fond bouton
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Son.action();
		parent.setFocus(this);
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}     

	

}
