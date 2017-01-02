import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class VueControle extends JButton implements ActionListener{
	private VueReplay parent;
	private int etat;

	public VueControle(VueReplay parent, int etat){
		this.parent = parent;
		this.etat = etat;
		this.setBorder(null);
		this.setContentAreaFilled(false); //Pas de fond bouton
		this.setFocusable(false); //PAs de rectangle de sélection
		this.attribImg();
		this.addActionListener(this);
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	private void attribImg() {
		ImageIcon img = null;
		if(etat == 1){
			img = Imageuh.createImageIcon("bouton1.png", "Premier coup");
		}
		else if(etat == 2){
			img = Imageuh.createImageIcon("bouton2.png", "Coup précédent");
		}
		else if(etat == 3){
			img = Imageuh.createImageIcon("bouton3.png", "Coup suivant");
		}
		else if(etat == 4){
			img = Imageuh.createImageIcon("bouton4.png", "Coup final");
		}
		else if(etat == 5){
			img = Imageuh.createImageIcon("bouton5.png", "Quitter le mode replay");
		}
		this.setIcon(img);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
