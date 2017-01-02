import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class VueIA extends VueModele implements ActionListener,ChangeListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel infoNiveauIA;
	private JLabel precision;
	private JButton jouer;
	private JButton passer;
	private JButton annuler;
	private JSlider compNiveauIA;
	private VueBacteria vue;
	private JCheckBox jouerIA;

	
	public VueIA(Donnees parent,VueBacteria vue,int x, int y, int l, int h, String txt, Color backgroundTitre,Color background) {
		super(parent, x, y, l, h, txt, backgroundTitre, background);
		this.vue = vue;
		int pos = 60;
		infoNiveauIA = new JLabel("Niveau de l'ordinateur: ");
		infoNiveauIA.setBounds(10,pos,this.getWidth()-13,30);
		this.add(infoNiveauIA);
		pos += 30;
		compNiveauIA = new JSlider(1,4,1);
		compNiveauIA.setMajorTickSpacing(1);
		compNiveauIA.setPaintTicks(true);
		compNiveauIA.setPaintLabels(true);
		//compNiveauIA.setEnabled(false);
		compNiveauIA.setBorder(null);
		compNiveauIA.setBounds(3,pos,this.getWidth()-6,60);
		compNiveauIA.setBackground(background);
		compNiveauIA.addChangeListener(this);
		this.add(compNiveauIA);
		
		pos += 60;
		JSeparator separation = new JSeparator(SwingConstants.HORIZONTAL);
		separation.setBounds(10,pos,this.getWidth()-13,30);
		this.add(separation);
		pos += 30;
		infoNiveauIA = new JLabel("Jouer un coup sur deux : ");
		infoNiveauIA.setBounds(10,pos,this.getWidth()-13,30);
		this.add(infoNiveauIA);
		jouerIA = new JCheckBox();
		jouerIA.addActionListener(this);
		jouerIA.setBounds(this.getWidth()-50,pos,30,30);
		jouerIA.setBackground(background);
		this.add(jouerIA);
		pos += 30;
		precision = new JLabel("(Effectif au prochain coup)");
		precision.setBounds(10,pos,this.getWidth()-6,30);
		this.add(precision);
		
		//annuler = new JButton("Annuler le dernier coup");
		//annuler.setBounds(3,this.getHeight()-90,this.getWidth()-6,30);
		//annuler.setBorder(null);
		//annuler.setFocusable(false);
		//annuler.setBackground(Const.BGBT1);
		//annuler.setForeground(Const.FGBT);
		//annuler.addActionListener(this);
		//this.add(annuler);
		
		passer = new JButton("Passer le tour");
		passer.setBounds(3,this.getHeight()-60,this.getWidth()-6,30);
		passer.setBorder(null);
		passer.setFocusable(false); //sinon rectangle de sélection bien moche
		
		passer.setBackground(Const.BGBT2);
		passer.setForeground(Const.FGBT);
		passer.addActionListener(this);
		this.add(passer);

		jouer = new JButton("Jouer le coup");
		jouer.setBounds(3,this.getHeight()-30,this.getWidth()-6,30);
		jouer.setBorder(null);
		jouer.setFocusable(false); //sinon rectangle de sélection bien moche
		jouer.setBackground(Const.BGBT1);
		jouer.setForeground(Const.FGBT);
		jouer.addActionListener(this);
		this.add(jouer);


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jouerIA){
			if(jouerIA.isSelected()){
				Son.action();
				parent.setiAauto(true);
				parent.setNiveauIA((Integer) this.compNiveauIA.getValue());
			}
			else{
				
				parent.setiAauto(false); //On joue contre l'IA
			}
		}
		else if(e.getSource() == annuler){
			String dernierCoup = this.parent.getLastMove();
			if(dernierCoup != ""){
				Son.action();
				parent.getTerrain().init(dernierCoup);
				vue.getTerrain().refresh();
				parent.changerQuiJouer();
				vue.getTerrain().noAutoriser();
				vue.getTerrain().autoriser(parent.getQuiJoue());
				parent.getTerrain().calculerScore();
				parent.setLastMove("");
				parent.notifyObservers();
			}
			
			
		}
		else{
			
		if(parent.getMatch()){
			
			if(e.getSource() == passer){
				
				vue.getMatch().passerTour();
				Son.passer();
			}
			else{
				Son.action();
				//System.out.println("On joue a la place du joueur au niveau "+(int)this.compNiveauIA.getValue());
				vue.getMatch().jouerIA((Integer) this.compNiveauIA.getValue(),false);
				
			}
		}
		
	}}


	@Override
	public void stateChanged(ChangeEvent arg0) {
		parent.setNiveauIA((Integer) this.compNiveauIA.getValue());
		
	}
		
	}

	
