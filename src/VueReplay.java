import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class VueReplay extends VueModele implements ActionListener,Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton charger,sauver,controle;
	private JLabel message;
	private VueBacteria app;
	private VueControle exit;
	private Replay replay;
	private VueControle next;
	private VueControle prevv;
	private VueControle prev;
	private VueControle nextt;


	
	public VueReplay(Donnees parent,VueBacteria app, int x, int y, int l, int h, String txt, Color backgroundTitre, Color background) {
		
		super(parent, x, y, l, h, txt,backgroundTitre,background);
		this.app = app;
		
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
		int div = (this.getWidth()/6)+6;
		
		prevv = new VueControle(this,1);
		prevv.setBounds(5,65,37,37);
		prevv.addActionListener(this);
		prevv.setMnemonic(KeyEvent.VK_DOWN);
		this.add(prevv);
		
		prev = new VueControle(this,2);
		prev.setBounds(div,65,37,37);
		prev.addActionListener(this);
		prev.setMnemonic(KeyEvent.VK_LEFT);
		this.add(prev);
		
		next = new VueControle(this,3);
		next.setBounds(div*2,65,37,37);
		next.addActionListener(this);
		next.setMnemonic(KeyEvent.VK_RIGHT);
		this.add(next);
		
		nextt = new VueControle(this,4);
		nextt.setBounds(div*3,65,37,37);
		nextt.addActionListener(this);
		nextt.setMnemonic(KeyEvent.VK_UP);
		this.add(nextt);
		
		exit = new VueControle(this,5);
		exit.setBounds(div*4, 65, 37,37);
		exit.addActionListener(this);
		this.add(exit);
		
		message =new JLabel("Aucun replay n'est chargé.");
		message.setBounds(15,100,this.getWidth()-15,30);
		this.add(message);
		charger = new JButton("Charger un replay ...");
		charger.setBounds(3,this.getHeight()-30,this.getWidth()-6,30);
		charger.setBorder(null);
		charger.setBackground(Const.BGBT2);
		charger.setForeground(Const.FGBT);
		charger.setFocusable(false); //sinon rectangle de sélection bien moche
		charger.addActionListener(this);
		this.add(charger);
	
		
		
	}


	public void actionPerformed(ActionEvent e) {
			if (e.getSource() == charger){
				 JFileChooser chooser = new JFileChooser();
				 int retour=chooser.showOpenDialog(chooser);
				 if(retour == 0){
					 if(parent.getMatch()){
							parent.setMatch(false);
							app.getMatch().arreter();
						}
					 replay = new Replay(parent,this.app.getInfos(),this,app.getTerrain(),chooser.getSelectedFile().getPath());
					
				 }
			}
		if(replay != null){
			if(e.getSource() == exit){
					replay = null;
					parent.setRep(false);
					this.setMessage("Replay arrété.");
					parent.updateVueInfos("finreplay");
					app.getTerrain().autoriser(0);
					app.getTerrain().autoriser(1);
					app.getTerrain().autoriser(2);
					app.getTerrain().autoriser(3);
					
			}
			else if(e.getSource() == next){
				replay.next();
		}
			else if(e.getSource() == nextt){
				replay.nextt();
		}
			else if(e.getSource() == prev){
				replay.prev();
		}
			else if(e.getSource() == prevv){
				replay.prevv();
		}
		}
			
	}
	
	public JLabel getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message.setText(message);
	}


	@Override
	public void update(Observable arg0, Object arg1) {

		
	}



}
