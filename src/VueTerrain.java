import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;


public class VueTerrain extends VueModele implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VueCase[][] vueDamier = new VueCase[8][8];
	private VueCase origine = null;
	private Match match;
	private int joueurReplay;

	
	public VueTerrain(Donnees parent,Match match, int x, int y, int l, int h, String txt, Color backgroundTitre, Color background) {
		super(parent, x, y, l, h, txt, backgroundTitre, background);
		this.match = match;
		init();
	}




	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Const.TITRE2);
		int init = 50;
		int pas = 66;
		//Ordonées
		for(int i=1;i<=8;i++){
			g.drawString(""+i, 10,init+(pas*(9-i)));
		}
		//Abscices
		init = -8;
		int ascii = 96;
		for(int i=1;i<=8;i++){
			g.drawString(""+(char)(ascii+i),init+(pas*(i)),this.getHeight()-55);
		}
		
	}
	public void init(){
		Terrain terrain = parent.getTerrain();
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
			
				vueDamier[x][y] = new VueCase(this,terrain.getDamier()[x][y],x,y);
				
				vueDamier[x][y].setBounds((x*Const.TAILLE_IMG)+30, (y*Const.TAILLE_IMG)+80, Const.TAILLE_IMG, Const.TAILLE_IMG);
				this.add(vueDamier[x][y]);
				this.setComponentZOrder(vueDamier[x][y], 0);
				/*On ajoute les cases vides à part*/
				VueCase caseVide = new VueCase(this,-1,x,y);
				caseVide.setBounds((x*Const.TAILLE_IMG)+30, (y*Const.TAILLE_IMG)+80, Const.TAILLE_IMG, Const.TAILLE_IMG);
				caseVide.setCliquable(false);
				this.add(caseVide);
			}
		}
		/*On autorise pour le mode d'édition*/
		this.autoriser(0);
		this.autoriser(1);
		this.autoriser(2);
		this.autoriser(3);
	}
	public void refresh(){
		Terrain terrain = parent.getTerrain();
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				vueDamier[x][y].setEtat(terrain.getDamier()[x][y]);
				vueDamier[x][y].setVisible(true);
			}
		}
	}
	/**
	* Méthode qui déplace le boutton de sa position d'origine vers la position de destination donnée en argument.
	*/
	public void move(int x, int y, int destx, int desty){
		Timer t = new Timer();
		t.scheduleAtFixedRate(new ElementActionMove(this,vueDamier[x][y].getEtat(),t,x,y, destx, desty), 3, parent.getSpeed());
	}
	
	public void autoriser(int etat){
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				if(parent.getTerrain().getDamier()[x][y] == etat){
					vueDamier[x][y].setCliquable(true);
				}
			}
		}
	}
	public void noAutoriser(){
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				if(vueDamier[x][y].getEtat() != Const.CASE0) vueDamier[x][y].setCliquable(false);
				}
			}
		}
	
	@Override
	public void update(Observable arg0, Object arg) {
		if ((String)arg == "terrain") this.refresh();

	}
	/*On enléve les possibilités*/
	public void terrainResetPossibilite() {
		this.parent.getTerrain().resetPossibilite();
		this.refresh();
		
	}
	/*On affiche les possibilitées*/
	public void terrainAfficherPossibilite(int i, int j) {
		this.parent.getTerrain().afficherPossibilite(i,j);

		this.refresh();
		this.autoriser(4);
		this.autoriser(5);
		
	}
	public void terrainJouer(int x, int y, int type) {
		if(parent.getReplay() == ""){
			parent.addReplay("i;"+parent.getTerrain().toString()+";");
		}
		if(type == 0){
			parent.addReplay(parent.getQuiJoue()+";0;"+x+";"+y+";"+origine.getI()+";"+origine.getJ()+";");
			this.parent.getTerrain().jouer(x,y);
		
		}
		else{
			//On refresh le terrain dans l'origine pour avoir une vraie animation
			parent.addReplay(parent.getQuiJoue()+";1;"+x+";"+y+";"+origine.getI()+";"+origine.getJ()+";");
			origine.setVisible(false);
			this.parent.getTerrain().jouer(x,y,origine.getI(),origine.getJ());
			
		}
		
	
		
		/*On indique au match qu'on a joué*/
		if (match != null)this.match.setReceivPos(true);
		this.move(origine.getI(),origine.getJ(),x,y);
		//this.refresh();
	}
	public void terrainJouerIA(int x, int y, int type) {
		if(parent.getReplay() == ""){
			parent.addReplay("i;"+parent.getTerrain().toString()+";presentation");
		}
		if(type == 0){
			this.parent.getTerrain().jouer(x,y);
			parent.addReplay( parent.getQuiJoue()+";0;"+x+";"+y+";"+origine.getI()+";"+origine.getJ()+";");
		}
		else{
			origine.setVisible(false);
			this.parent.getTerrain().jouer(x,y,origine.getI(),origine.getJ());
			parent.addReplay(parent.getQuiJoue()+";1;"+x+";"+y+";"+origine.getI()+";"+origine.getJ()+";");
		}
		this.move(origine.getI(),origine.getJ(),x,y);
		
	}
	public void terrainJouerRep(int x, int y, int type) {
		parent.setQuiJoue(joueurReplay);
		if(type == 0){
			this.parent.getTerrain().jouer(x,y);
		
		}
		else{
			origine.setVisible(false);
			this.parent.getTerrain().jouer(x,y,origine.getI(),origine.getJ());
			
		}
		parent.getTerrain().calculerScore();
		parent.updateVueInfos("infos");
		this.move(origine.getI(),origine.getJ(),x,y);
		
	}

	public void terrainJouerReplay(int x, int y, int type) {
	
		if(type == 0){
			this.parent.getTerrain().jouer(x,y);
		
		}
		else{
			origine.setVisible(false);
			this.parent.getTerrain().jouer(x,y,origine.getI(),origine.getJ());
			
		}
		this.move(origine.getI(),origine.getJ(),x,y);
		
	}
	public void deJouer(int x, int y, String situation) {
		//On fait le mouv et on met a jour la situation précédente
		this.parent.getTerrain().init(situation);
		this.vueDamier[x][y].setVisible(false);
		this.move(x,y,origine.getI(),origine.getJ());
		
	}
	public void deJouer(String situation) {
		//On fait le mouv et on met a jour la situation précédente
		this.parent.getTerrain().init(situation);
		this.refresh();
	}
	public VueCase getOrigine() {
		return origine;
	}
	public void setOrigine(VueCase origine) {
		this.origine = origine;
	}
	

	public VueCase[][] getVueDamier() {
		return vueDamier;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public void enleverDrag(){
		parent.setDrag(-1);
	}
	public void drop(VueCase c) {
		if(parent.getDrag() != -1 && isEditable()){
			//SI le drag est égal a la valeur de la case on met une case vide sinon on l'attribut
			if(parent.getTerrain().getDamier()[c.getI()][c.getJ()] == parent.getDrag()){
				parent.getTerrain().getDamier()[c.getI()][c.getJ()] = 0;
				c.setEtat(0);
			}
			else{
				parent.getTerrain().getDamier()[c.getI()][c.getJ()] = parent.getDrag();
				c.setEtat(parent.getDrag());
			}
			
			
		}
		
	}
	public boolean isEditable() {
		// TODO Auto-generated method stub
		Boolean retour = false;
		if(match == null && !parent.getRep()){
			retour = true;
		}
		else{
			retour = false;
		}
		if(match != null){
			if(match.isFini() && !parent.getRep()){
				retour = true;
			}
		}
		return retour;
	}
	public boolean isReplay() {
		// TODO Auto-generated method stub
		return parent.getRep();
	}
	public void previewDup(int i, int j) {
		vueDamier[i][j].setEtat(6);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void previewDep(int i, int j, int i2, int j2) {
		vueDamier[i2][j2].setEtat(7);
		vueDamier[i][j].setEtat(6);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}




	public void setJoueurReplay(int etat) {
		this.joueurReplay = etat;
		
	}





		
	}

	
	
    
	





