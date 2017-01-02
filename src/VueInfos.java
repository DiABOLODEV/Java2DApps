import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class VueInfos extends VueModele implements Observer, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton sauver;
	private JButton commenter;
	private JTextPane message;
	private HTMLEditorKit html;



	

	public VueInfos(Donnees parent,int x, int y, int l, int h, String txt, Color backgroundTitre, Color background) {
		super(parent, x, y, l, h, txt, backgroundTitre, background);
		
		this.message= new JTextPane();
		JScrollPane scrollPane = new JScrollPane(this.message);
    	scrollPane.setBounds(10,60,this.getWidth()-13,this.getHeight()-90);
    	scrollPane.getVerticalScrollBar().setUnitIncrement(1);
    	/*STYLE*/
    	scrollPane.setBorder(null);
		this.message.setEditable(false);
		/*insertion de code html*/
    
        this.message.setContentType("text/html");
        this.html=new HTMLEditorKit();
        this.message.setEditorKit(this.html);
        this.message.setDocument(new HTMLDocument());
		this.add(scrollPane);
		insererMessage(titre("Bienvenue sur Bactéflip !") +
				"Vous ne comprenez rien à l'interface ? <b>DON'T PANIC !</b> Cliquez sur le menu <i> Aide</i> ci-dessus ;)");

		sauver = new JButton("Sauvegarder le replay ...");
		sauver.setBounds(3,this.getHeight()-30,this.getWidth()-6,30);
		sauver.setBorder(null);
		sauver.setVisible(false);
		sauver.setBackground(Const.BGBT1);
		sauver.setForeground(Const.FGBT);
		sauver.addActionListener(this);
		sauver.setFocusable(false); //sinon rectangle de sélection bien moche
		
		commenter = new JButton("Commenter le coup suivant ");
		commenter.setBounds(3,this.getHeight()-30,this.getWidth()-6,30);
		commenter.setBorder(null);
		commenter.setVisible(false);
		commenter.setBackground(Const.BGBT1);
		commenter.setForeground(Const.FGBT);
		commenter.addActionListener(this);
		commenter.setFocusable(false); //sinon rectangle de sélection bien moche
		this.add(commenter);
		this.add(sauver);
	
	}
	public String titre(String txt){
		return "<font color='#0099FF' size='4'><b>"+txt+"</b></font> <br/><br/>";
	}
	public void insererMessage(String txt){
		 try {
			 	this.message.setText("");
				this.html.insertHTML((HTMLDocument) this.message.getDocument(), this.message.getDocument().getLength(), txt, 0, 0, null);
				this.message.setCaretPosition(0); //Position du scroll en haut
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void insererMessage(String titre,String txt){
		 try {
			 	this.message.setText("");
				this.html.insertHTML((HTMLDocument) this.message.getDocument(), this.message.getDocument().getLength(), this.titre(titre)+txt, 0, 0, null);
				this.message.setCaretPosition(0); //Position du scroll en haut
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void refresh(){
		sauver.setVisible(false);
		if(parent.getRep()){
			insererMessage(titre("Into the wild")+"Si vous voulez revenir à une situation propre cliquez sur le bouton <i>coup précédent</i>");
		}
		
		else if(!this.message.getText().contains("portez")){
			insererMessage(titre("Bactématch")+"Ne portez pas une attention particulière à ce message, essayez plutôt de vous concentrer sur votre match :D");
			if(!commenter.isVisible()){
				commenter.setVisible(true);
				this.setComponentZOrder(commenter, 0);
				System.out.println("test");}
			
			
		}
		
	}
	public void fini(){
		Son.jeuFini();
		String msg = "";
		
		if(parent.getScoreJ() > parent.getScoreJ2()){
			msg ="<font color='green'><b>Les verts gagnent ce bactématch de folie! </b></font><br/><br/>";
			
		}
		else if(parent.getScoreJ() < parent.getScoreJ2()){
			msg ="<font color='red'><b>Les rouges gagnent ce bactématch de folie! </b></font><br/><br/>";
		}
		else{
			msg ="<font color='#0099FF'><b>Après tant d'efforts ce match ce termine par une égalité ! </b></font><br/><br/>";
		}
		this.insererMessage(msg+"Si le match vous a plu vous pouvez sauvegarder le replay pour le matter plus tard ;)");
		commenter.setVisible(false);
		sauver.setVisible(true); //on permet à l'utilisateur de sauvegarder le replay
		this.setComponentZOrder(sauver, 0);
		
	}

	public void update(Observable arg0, Object arg1) {
		
		if((String)arg1 == "fini"){
			this.fini();
		}
		if((String)arg1 == "finreplay"){
			
			this.insererMessage(titre("Byebye le replay")+"J'espère qu'il vous a enjaillé ;) ");
		}

		if((String)arg1 == "infos"){
	
			refresh();
		}
	}
	public void setMessage(String msg){
		insererMessage(msg);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == commenter){
			parent.setCommentaire(Fenetre.dialogue());
			Son.action();
		}
		else if(e.getSource() == sauver){
			//sauver le replay
			SauverReplay.sauver(parent.getReplay());
			//Sauver.sauver(parent,parent.getReplay(),Const.REPLAY);
			Son.action();
		}
		
	}

	
}
