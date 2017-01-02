import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class VueConfiguration extends VueModele implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelQuiJoue;
	private JRadioButton compQuiJoueJ;
	private JRadioButton compQuiJoueJ2;
	private JButton jouer;
	private JButton rejouer;
	private JButton arreter;
	private VueBacteria vueBacteria;

	
	public VueConfiguration(VueBacteria vueBacteria, Donnees parent, int x, int y, int l, int h, String txt,Color backgroundTitre,Color background) {
	
		super(parent, x, y, l, h, txt,backgroundTitre,background);
		this.vueBacteria = vueBacteria;
		/*
		labelNiveauJ2 = new JLabel("Niveau de l'ordinateur: ");
		labelNiveauJ2.setBounds(10,60,this.getWidth(),30);
		this.add(labelNiveauJ2);
		
		SpinnerModel modele = new SpinnerNumberModel(3,1,7,1);     
		compNiveauJ2 = new JSpinner(modele);
		compNiveauJ2.setBorder(null);
		compNiveauJ2.setEditor(new JSpinner.DefaultEditor(compNiveauJ2)); //Enlève la permission d'édition sur le texte
		compNiveauJ2.setBounds(this.getWidth()-50,60,30,30);
		this.add(compNiveauJ2);
		*/
		
		int pos = 60;

		
		labelQuiJoue = new JLabel("Qui commence? ");
		labelQuiJoue.setBounds(10,pos,this.getWidth(),30);
		this.add(labelQuiJoue);
		pos+= 30;
		compQuiJoueJ = new JRadioButton("Vert");
		compQuiJoueJ.setBounds(10,pos,this.getWidth()-13,30);
		compQuiJoueJ.setBackground(background);
		this.add(compQuiJoueJ);
		pos+= 30;
		compQuiJoueJ2 = new JRadioButton("Rouge");
		compQuiJoueJ2.setBounds(10,pos,this.getWidth()-13,30);
		compQuiJoueJ2.setBackground(background);
		this.add(compQuiJoueJ2);
		
		arreter = new JButton("Arrêter le match en cours");
		arreter.setBounds(3,this.getHeight()-119,this.getWidth()-6,30);
		arreter.setBorder(null);
		arreter.setBackground(Const.BGBT1);
		arreter.setForeground(Const.FGBT);
		arreter.setFocusable(false); //sinon rectangle de sélection bien moche
		arreter.addActionListener(this);
		this.add(arreter);
		jouer = new JButton("Jouer");
		jouer.setBounds(3,this.getHeight()-59,this.getWidth()-6,30);
		jouer.setBorder(null);
		jouer.setBackground(Const.BGBT1);
		jouer.setForeground(Const.FGBT);
		jouer.setFocusable(false); //sinon rectangle de sélection bien moche
		jouer.addActionListener(this);
		this.add(jouer);
		rejouer = new JButton("Rejouer");
		rejouer.setBounds(3,this.getHeight()-89,this.getWidth()-6,30);
		rejouer.setBorder(null);
		rejouer.setBackground(Const.BGBT2);
		rejouer.setForeground(Const.FGBT);
		rejouer.setFocusable(false); //sinon rectangle de sélection bien moche
		rejouer.addActionListener(this);
		this.add(rejouer);
		
		
	}


	public void actionPerformed(ActionEvent e) {

			Son.jouer();
			if(e.getSource() == arreter && parent.getMatch() && !parent.getRep()){
				parent.updateVueInfos("fini");
				vueBacteria.getMatch().arreter();
				
			}
			else if(!parent.getRep()){
			    //Les jeux jouer et rejouer
				if(e.getSource() == rejouer){
					parent.getTerrain().mettreDamierPrec();
					parent.updateVueTerrain();
					parent.updateVueInfos("rejouer");
				}
				
				int quiJoue;
				//Niveau J2
				Random r = new Random();
				//Qui joue
				if ((compQuiJoueJ.isSelected() && compQuiJoueJ2.isSelected()) || (!compQuiJoueJ.isSelected() && !compQuiJoueJ2.isSelected())){
					
					if(r.nextInt(2) == 0){
						quiJoue = 1;
					}
					else{
						quiJoue = 0;
					}
				}
				else if(compQuiJoueJ.isSelected()){
					quiJoue = 1;
				}
				else{
					quiJoue = 0;
				}
				
				//Hoplà on envoie tout
				this.parent.configuration(quiJoue);
			}
		
	}



}
