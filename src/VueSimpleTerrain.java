import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JPanel;



public class VueSimpleTerrain extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String map;
	private String titre;
	private int numero;
	private BufferedImage image;
	VueCharger parent;
	

	public VueSimpleTerrain (VueCharger parent,int numero, String map, String titre){
		this.numero = numero;
		this.parent = parent;
		this.titre = titre;
		this.map = map;

		this.setLayout(null);
		this.addMouseListener(parent);
	}
	public VueSimpleTerrain (int numero, String map, String titre){
		this.numero = numero;
		this.parent = null;
		this.titre = titre;
		this.map = map;

		this.setLayout(null);
		this.addMouseListener(parent);
	}
	
	  public void creerImage() {
		 // On crée l'image en RGB.
		 image = new BufferedImage(this.getWidth(),this.getHeight(), BufferedImage.TYPE_INT_RGB);
	  }
	  public void sauverImage(String nom, String format) {
			try {
			    // On sauve l'image histoire de rire un peu :
			    File fic= new File(nom+ "." + format);
			    ImageIO.write(image, format, fic);
			} catch (IOException e) {
			    e.printStackTrace();
			}
		    }
	  public void dessinerImage(){
			
			Graphics2D g2= (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
			
			int sizeTxt = this.getHeight()-90;
			int sizeCase = sizeTxt/8;
			Color case0 = new Color(176,223,253);
			Color case0bg = new Color(148,210,252);
			Color case1 = new Color(255,219,66);
			Color case1bg = new Color(253,180,11);
			Color case2 = new Color(3,202,2);
			Color case3 = new Color(240,0,0);
			Color txt = new Color(255,255,255);
			Color txt2 = new Color(187,227,253);
			
			//Arriere plan
			g2.setColor(Const.BGBT1);
			g2.fillRect(0, sizeTxt, this.getWidth(), this.getHeight());
			
			
			int pad = 2;
			int padTxt = 5;
			int posY = 0;
			int posX = 0;
			
			int t= 0;
			int v = 0;
			int r = 0;
			
			for(int x = 0;x < 64; x++){
				int im = 3;
				if (map.charAt(x) == '0'){
					im = 3;
					/*
					g2.setColor(case0);
					g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
					g2.setColor(case0bg);
					g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
				}
				else if (map.charAt(x) == '1'){/*
					g2.setColor(case1);
					g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
					g2.setColor(case1bg);
					g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
					im = 4;
					t++;
				}
				else if (map.charAt(x) == '2'){/*
					g2.setColor(case2);
					g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
				
					g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
					im = 2;
					v++;
				}
				else if (map.charAt(x) == '3'){/*
					g2.setColor(case3);
					g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
					g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
					im = 3;
					r++;
				}
				else{/*
					g2.setColor(case0);
					g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
					g2.setColor(case1);
					g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
				}
				g2.drawImage(Imageuh.createImage(im+".png", ""),posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad, null);
				
				
				posX ++;
				/*x+1 car on démarre a 0*/
				if((x+1)%8 == 0){
					posX = 0;
					posY++;
				}
				

			}
			g2.setColor(txt);
			Font f1 = new Font("Calibri",Font.BOLD,20);
			Font f2 = new Font("Calibri",Font.ITALIC,13);
			g2.setFont(f1);
			g2.drawString(titre, padTxt,((sizeCase*8)+(pad*8))+padTxt);
			g2.setFont(f2);
			g2.setColor(txt2);
			g2.drawString("Cases pleines: "+t, padTxt,((sizeCase*8)+(pad*8)+16)+padTxt);
			g2.drawString("Départ : "+r+" rouge(s),"+v+" verte(s)", padTxt,((sizeCase*8)+(pad*8)+32)+padTxt);

			
		}
	
	public void paintComponent(Graphics g){
		
		Graphics2D g2= (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
		
		int sizeTxt = this.getHeight()-90;
		int posSuppr = this.getHeight()-30;
		int sizeCase = sizeTxt/8;
		Color case0 = new Color(176,223,253);
		Color case0bg = new Color(148,210,252);
		Color case1 = new Color(255,219,66);
		Color case1bg = new Color(253,180,11);
		Color case2 = new Color(3,202,2);
		Color case3 = new Color(240,0,0);
		Color txt = new Color(255,255,255);
		Color txt2 = new Color(187,227,253);
		
		//Arriere plan
		g2.setColor(Const.BGBT1);
		g2.fillRect(0, sizeTxt, this.getWidth(), this.getHeight());
		
		JButton suppr = new JButton("supprimer ...");
		suppr.addMouseListener(parent);
		suppr.setBorder(null);
		suppr.setBackground(Const.BGBTSUPPR);
		suppr.setForeground(Const.FGBTSUPPR);
		suppr.setFocusable(false); //sinon rectangle de sélection bien moche
		suppr.setBounds(0, posSuppr, this.getWidth(), 30);
		this.add(suppr);
		
		
		int pad = 2;
		int padTxt = 5;
		int posY = 0;
		int posX = 0;
		
		int t= 0;
		int v = 0;
		int r = 0;
		
		for(int x = 0;x < 64; x++){
			int im = 3;
			if (map.charAt(x) == '0'){
				im = 3;
				/*
				g2.setColor(case0);
				g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
				g2.setColor(case0bg);
				g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
			}
			else if (map.charAt(x) == '1'){/*
				g2.setColor(case1);
				g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
				g2.setColor(case1bg);
				g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
				im = 4;
				t++;
			}
			else if (map.charAt(x) == '2'){/*
				g2.setColor(case2);
				g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
			
				g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
				im = 1;
				v++;
			}
			else if (map.charAt(x) == '3'){/*
				g2.setColor(case3);
				g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
				g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
				im = 2;
				r++;
			}
			else{/*
				g2.setColor(case0);
				g2.fillOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);
				g2.setColor(case1);
				g2.drawOval(posX*sizeCase, posY*sizeCase, sizeCase-pad,sizeCase-pad);*/
			}
			g2.drawImage(Imageuh.createImage(im+".png", ""),posX*sizeCase, posY*sizeCase, 21,21, null);
			
			
			posX ++;
			/*x+1 car on démarre a 0*/
			if((x+1)%8 == 0){
				posX = 0;
				posY++;
			}
			

		}
		g2.setColor(txt);
		Font f1 = new Font("Calibri",Font.BOLD,20);
		Font f2 = new Font("Calibri",Font.ITALIC,13);
		g2.setFont(f1);
		g2.drawString(titre, padTxt,((sizeCase*8)+(pad*8))+padTxt);
		g2.setFont(f2);
		g2.setColor(txt2);
		g2.drawString("Cases pleines: "+t, padTxt,((sizeCase*8)+(pad*8)+16)+padTxt);
		g2.drawString("Départ : "+r+" rouge(s),"+v+" verte(s)", padTxt,((sizeCase*8)+(pad*8)+32)+padTxt);

		
	}

	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public int getNumero() {
		return numero;
	}

}
