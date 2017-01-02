import java.awt.Color;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


public class VueApropos extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titre;
	private JTextPane message;
	private HTMLEditorKit html;
	public VueApropos(){
		this.setLocationRelativeTo(null);
		this.setBounds(this.getX()-350, this.getY()-250, 600,200);
		this.setLayout(null);
		this.setResizable(false);
		this.setIconImage(Imageuh.createImageIcon("icon.png","icone").getImage());
		this.setTitle("à propos");
		this.getContentPane().setBackground(Color.white);
		/*
		titre = new JLabel(Imageuh.createImageIcon("titre_apropos.png", "credits"));
		titre.setBounds(120,0,384,150);
		titre.setBackground(null);
		this.add(titre);*/
		this.message= new JTextPane();

		/**/
    	JScrollPane scrollPane = new JScrollPane(this.message);
    	scrollPane.setBounds(50,50,this.getWidth()-30,this.getHeight());
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
		String txt = "" +
				"<font color='#333333'><b> Bacteflip </b>  (plus ou moins terminé), fait par Thibault Gauthier [sept 2012,2013]</font><br/>" +
				"suggestions/bugs: envoyé un mail à <b>moithibault@gmail.com</b>";
		 try {
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
		
		this.setVisible(true);
		
	}

}
