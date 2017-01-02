import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class VueMenu extends JMenuBar implements ActionListener{
	JMenu aide;
	JMenuItem aPropos,aideInterface,regles;
	Boolean aideAfficher = false;
	VueBacteria parent;
	public VueMenu(VueBacteria parent){
		this.parent = parent;
		/*
	    aide = new JMenu("Aide");
	    aideInterface = new JMenuItem("Afficher/Cacher l'aide à l'interface");
	    aideInterface.addActionListener(this);
	    aide.add(aideInterface);
	    regles = new JMenuItem("Afficher/Cacher les règles du jeu");
	    regles.addActionListener(this);
	    aide.add(regles);
	    this.add(aide);
	    */
	    aPropos = new JMenuItem("à propos");
	    aPropos.addActionListener(this);
	    this.add(aPropos);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == aPropos){
			new VueApropos();
			Son.action();
		}
		if(e.getSource() == aideInterface){
			/*if(aideAfficher){
				parent.getAide().setVisible(false);
				aideAfficher = false;
			}
			else{
				parent.getAide().setVisible(true);
				aideAfficher = true;
			}
			Son.action();
		}
*/
		}
	}

}
