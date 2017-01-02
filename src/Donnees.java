import java.util.Observable;
public class Donnees extends Observable{
	private Boolean iAauto = false;
	private String commentaire = "Match de présentation ;)";
	private int quiJoue = 2;
	private int couleurV = 2;
	private int couleurR = 3;
	private int scoreJ = 0;
	private int scoreJ2 = 0;
	private Boolean match = false; //match encours
	private Boolean rep = false; //replay encours
	private int drag = -1; //Le buffer pour change l'etat d'une case par faux DnD
	private Terrain terrain = new Terrain(this);
	private int niveauIA = 3;
	private String replay = "";
	private int speed = 3;
	private String lastMove = "";
	public int getSpeed(){
		return speed;
	}
	
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getReplay(){
		return replay;
	}
	public void addReplay(String txt){
		if(replay != ""){
			replay += "\n"+txt;
		}
		else{
			replay += txt;
		}
		replay += commentaire;
		commentaire = "pas de commentaires";
	}
	public void resetReplay(){
		replay ="";
	}
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}

	public int getQuiJoue() {
		return quiJoue;
	}
	public void changerQuiJouer(){
		if (this.quiJoue == 2){
			this.quiJoue = 3;
		}
		else{
			this.quiJoue = 2;
		}
		this.setChanged();
		this.notifyObservers("infos");
	}
	public void setQuiJoue(int quiJoue) {
		this.quiJoue = quiJoue;
	}
	public int getCouleurV() {
		return couleurV;
	}

	public void setCouleurV(int couleurV) {
		this.couleurV = couleurV;
	}

	public int getCouleurR() {
		return couleurR;
	}

	public void setCouleurR(int couleurR) {
		this.couleurR = couleurR;
	}

	public int getScoreJ() {
		return scoreJ;
	}

	public void setScoreJ(int scoreJ) {
		this.scoreJ = scoreJ;
		this.setChanged();
		this.notifyObservers("infos");
	}

	public int getScoreJ2() {
		return scoreJ2;
	}

	public void setScoreJ2(int scoreJ2) {
		this.scoreJ2 = scoreJ2;
		this.setChanged();
		this.notifyObservers("infos");
	}

	public Terrain getTerrain() {
		return terrain;
	}
	public void updateVueTerrain(){
		this.setChanged();
		this.notifyObservers("terrain");
	}
	public void updateVueReplay(String msg){
		this.setChanged();
		this.notifyObservers("replay");
	}
	public void updateVueInfos(String msg){
		this.setChanged();
		this.notifyObservers(msg);
	}

	public void configuration(int quiJoue){
		if (quiJoue == 1)this.quiJoue = couleurV;
		else this.quiJoue = couleurR;
		this.setChanged();
		this.notifyObservers("jouer");
	}

	public void setScoreJ2() {
		this.scoreJ2++;
		setChanged();
		notifyObservers("infos");
		
	}
	public void setScoreJ() {
		this.scoreJ++;
		setChanged();
		notifyObservers("infos");
		
	}

	public int getDrag() {
		return drag;
	}

	public void setDrag(int drag) {
		this.drag = drag;
	}

	public Boolean getMatch() {
		return match;
	}

	public void setMatch(Boolean match) {
		this.match = match;
	}

	public Boolean getRep() {
		return rep;
	}
	public void setRep(Boolean rep) {
		this.rep = rep;
	}
	public Boolean getiAauto() {
		return iAauto;
	}

	public void setiAauto(Boolean iAauto) {
		this.iAauto = iAauto;
	}

	public int getNiveauIA() {
		// TODO Auto-generated method stub
		return niveauIA;
	}

	public void setNiveauIA(int niveauIA) {
		this.niveauIA = niveauIA;
	}
	public void arreterMatch(){
		this.setChanged();
		this.notifyObservers("arreter");
	}
	public void updateVueCharger() {
		this.setChanged();
		this.notifyObservers("charger");
		
	}
	public String getLastMove() {
		// TODO Auto-generated method stub
		return lastMove;
	}
	public void setLastMove(String lm) {
		// TODO Auto-generated method stub
		lastMove = lm;
	}



	
	

}
