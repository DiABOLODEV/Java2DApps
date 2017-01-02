import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class VueConfigurationTerrain extends VueModele implements ActionListener{
	/**
	 * 
	 */
	private JButton clear;
	private JButton clearB;

	private JButton sauver;
	private static final long serialVersionUID = 1L;
	VueCaseConfig plein,vert,rouge;
	public VueConfigurationTerrain(Donnees parent, int x, int y, int l, int h,
			String txt, Color backgroundTitre, Color background) {
		super(parent, x, y, l, h, txt, backgroundTitre, background);

		plein = new VueCaseConfig(this,1);
		int unTier = this.getWidth()/3;
		plein.setBounds(3, 50, unTier,60);
		
		vert = new VueCaseConfig(this,2);
		vert.setBounds(unTier, 50, unTier,60);
		rouge = new VueCaseConfig(this,3);
		rouge.setBounds(unTier*2, 50, unTier,60);
		
		this.add(plein);
		this.add(vert);
		this.add(rouge);
		

		
		clearB = new JButton("Enlever les bactéries");
		clearB.setBounds(3,this.getHeight()-119,this.getWidth()-6,30);
		clearB.setBorder(null);
		clearB.setBackground(Const.BGBT1);
		clearB.setForeground(Const.FGBT);
		clearB.setFocusable(false); //sinon rectangle de sélection bien moche
		clearB.addActionListener(this);
		this.add(clearB);
		
		clear = new JButton("Tout enlever");
		clear.setBounds(3,this.getHeight()-89,this.getWidth()-6,30);
		clear.setBorder(null);
		clear.setBackground(Const.BGBT2);
		clear.setForeground(Const.FGBT);
		clear.setFocusable(false); //sinon rectangle de sélection bien moche
		clear.addActionListener(this);
		this.add(clear);
		
		sauver = new JButton("Sauvegarder ce terrain ...");
		sauver.setBounds(3,this.getHeight()-59,this.getWidth()-6,30);
		sauver.setBorder(null);
		sauver.setBackground(Const.BGBT1);
		sauver.setForeground(Const.FGBT);
		sauver.setFocusable(false); //sinon rectangle de sélection bien moche
		sauver.addActionListener(this);
		this.add(sauver);
		
	}
	public void setFocus(VueCaseConfig c) {
		plein.dragOff();
		vert.dragOff();
		rouge.dragOff();
		c.dragOn();
		parent.setDrag(c.getEtat());
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == sauver){
			
			Sauver.sauver(parent,parent.getTerrain().toString());
			Son.action();
		}
	
		else if(e.getSource() == clear){
		//cleaner le terrian
		if(!parent.getMatch()){
			
			parent.getTerrain().clean();
			parent.updateVueTerrain();
			Son.clean();
		}
		}
		else if(e.getSource() == clearB){
		//cleaner le terrian
		if(!parent.getMatch()){
			
			parent.getTerrain().cleanB();
			parent.updateVueTerrain();
			Son.clean();
		}
		}
		
	}

}
