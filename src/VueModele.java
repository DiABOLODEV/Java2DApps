import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class VueModele extends JPanel{

	private static final long serialVersionUID = 1L;
	protected Donnees parent;
	private Color backgroundTitre;
	private Color background;
	
	public VueModele(Donnees parent, int x,int y,int l,int h,String txt,Color backgroundTitre,Color background){
		this.backgroundTitre = backgroundTitre;
		this.background = background;
		this.parent = parent;
		this.setBounds(x, y, l, h);
		this.setLayout(null);
		JLabel titre = new JLabel(txt);
		titre.setFont(new Font("Arial",Font.PLAIN,14));
		titre.setBounds((this.getWidth()/2)-(txt.length()*3), 12, this.getWidth(), 25);
		titre.setBackground(Color.black);
		titre.setForeground(Color.white);
		this.add(titre);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(78,184,254));
		g.drawRect(0, 0, this.getWidth(),this.getHeight());
		g.setColor(background);
		g.fillRect(0, 0, this.getWidth(), this.getHeight()); //On rempli la zone compléte sinon on a des bugs graphiques
		g.setColor(backgroundTitre);
		g.fillRect(0, 0, this.getWidth(), 50);
		g.fillRect(this.getWidth()-3,0,3,this.getHeight());
		g.fillRect(0,0,3,this.getHeight());
		
	}



	
	
}
