import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Terrain {
	private int[][] damier = new int[8][8];
	private int[][] damierPrec = new int[8][8];
	private Donnees donnees;
	public Terrain(Donnees donnees){
		String t="00000000"+"00000000"+"00000000"+"00000000"+"00000000"+"00000000"+"00000000"+"00000000";
	this.init(t);
	this.donnees = donnees;
	}
	//Pour l'animation et mettre l'origne a 0 avant toutes les autres
	public void setCase(int x,int y,int etat){
		damier[x][y] = etat;
	}
	void init(String txt) {
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				damier[x][y] = Integer.parseInt(""+txt.charAt(x+(y*8))); // (caractére=>chaine=>entier)
				//System.out.donnees.setScoreJ();
				//if (Integer.parseInt(""+txt.charAt(x+(y*8))) == donnees.getCouleurJ2()) donnees.setScoreJ2();
			}
		}
		affect(damierPrec,damier);
		
	}
	public String toString(){
		String retour = "";
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				retour += ""+damier[x][y];
			}
		}
		return retour;
	}
	public int[][] getDamier() {
		return damier;
	}
	public void setDamier(int[][] damier) {
		this.damier = damier;
		affect(damierPrec,damier);
	}

	public void setAleaTerrain(){
		//lecture du fichier texte	
		String t="";
		try{
			/*On tire un terrain au hasard*/
			
			File repertoire=new File(Terrain.class.getResource("./levels").getFile());
			File[] list = repertoire.listFiles();
			int nombreAleatoire = (int) (Math.random()*list.length-1);
			//System.out.println(list[nombreAleatoire].toString());
			File terrainAlea=list[nombreAleatoire];
			/********************************************************************/
			/*On récupére le contenu de ce fichier texte*/
			InputStream ips=new FileInputStream(terrainAlea); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			String chaine="";
			while ((ligne=br.readLine())!=null){
				chaine+=ligne; //ON ajoute pas /n car on lit tout sur une ligne
			}
			t=chaine;
			br.close(); 
		}		
		catch (Exception e){
			t="22000000"+
					"00000000"+
					"00000000"+
					"00011000"+
					"00011000"+
					"00000000"+
					"00000000"+
					"00000033";
		}
		this.init(t);
	}

	public void resetPossibilite() {
		for (int y = 0;y < 8;y ++){
			for (int x = 0;x < 8;x ++){
				if(damier[x][y] == 4 || damier[x][y] == 5){
					damier[x][y] = 0;
				}
			}
		}
		
	}

		
		public void calculerScore(){
			int scoreJ = 0;
			int scoreJ2 = 0;
			for (int y = 0;y < 8;y ++){
				for (int x = 0;x < 8;x ++){
					if(damier[x][y] == donnees.getCouleurV()){
						scoreJ ++;
					}
					else if(damier[x][y] == donnees.getCouleurR()){
						scoreJ2 ++;
					}
				}
			}
			donnees.setScoreJ2(scoreJ2);
			donnees.setScoreJ(scoreJ);
			
	}
	public void afficherPossibilite(int i , int j) {
		/*On cherche les voisines et on les met à 4/5*/
		if(i+1 < 8 && damier[i+1][j] == 0) damier[i+1][j] = 4;
		if(i-1 >= 0 && damier[i-1][j] == 0) damier[i-1][j] = 4;
		if(j-1 >= 0 && damier[i][j-1] == 0) damier[i][j-1] = 4;
		if(j+1 < 8 && damier[i][j+1] == 0) damier[i][j+1] = 4;
		if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0) damier[i+1][j+1] = 4;
		if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0) damier[i+1][j-1] = 4;
		if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0) damier[i-1][j+1] = 4;
		if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0) damier[i-1][j-1] = 4;
		
		if(i+2 < 8 && damier[i+2][j] == 0) damier[i+2][j] = 5;
		if(i-2 >= 0 && damier[i-2][j] == 0) damier[i-2][j] = 5;
		if(j+2 < 8 && damier[i][j+2] == 0) damier[i][j+2] = 5;
		if(j-2 >= 0 && damier[i][j-2] == 0) damier[i][j-2] = 5;
				
			
		
		
	}

	public void jouer(int i, int j) {
		donnees.setLastMove(this.toString()); //enregistrer le dernier coup pour "annuler vcoup"
		//Jouer une duplication
		int adv;
		if (donnees.getQuiJoue() == 2){
			adv = 3;
		}
		else{
			adv = 2;
		}
		damier[i][j] = donnees.getQuiJoue();
		if(i+1 < 8 && damier[i+1][j] == adv) damier[i+1][j] = donnees.getQuiJoue();
		if(i-1 >= 0 && damier[i-1][j] == adv) damier[i-1][j] = donnees.getQuiJoue();
		if(j-1 >= 0 && damier[i][j-1] == adv) damier[i][j-1] = donnees.getQuiJoue();
		if(j+1 < 8 && damier[i][j+1] == adv) damier[i][j+1] = donnees.getQuiJoue();
		if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == adv) damier[i+1][j+1] = donnees.getQuiJoue();
		if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == adv) damier[i+1][j-1] = donnees.getQuiJoue();
		if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == adv) damier[i-1][j+1] = donnees.getQuiJoue();
		if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == adv) damier[i-1][j-1] = donnees.getQuiJoue();

				
		
	}
	//Permet de savoir si le jeu est fini ou non -
	//Si le jeu est fini on transforme toutes les cases vides par celle du joueur qui a encore des possibilités
	public Boolean isFini(){
		Boolean finVert = true;
		Boolean finRouge = true;
		
		for(int j = 0;j < 8;j++){
			for(int i = 0;i < 8;i++){
				if(damier[i][j] == 2){
					if((i+1 < 8 && damier[i+1][j] == 0)||
					(i-1 >= 0 && damier[i-1][j] == 0)||
					(j-1 >= 0 && damier[i][j-1] == 0)||
					(j+1 < 8 && damier[i][j+1] == 0)||
			        (i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0)||
					(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0)||
					(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0)||
					(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0)||
					(i+2 < 8 && damier[i+2][j] == 0)||
					(i-2 >= 0 && damier[i-2][j] == 0)||
					(j+2 < 8 && damier[i][j+2] == 0)||
					(j-2 >= 0 && damier[i][j-2] == 0)) {
						finVert = false;
					}
				}
				else if(damier[i][j] == 3){
						if((i+1 < 8 && damier[i+1][j] == 0)||
						(i-1 >= 0 && damier[i-1][j] == 0)||
						(j-1 >= 0 && damier[i][j-1] == 0)||
						(j+1 < 8 && damier[i][j+1] == 0)||
				        (i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0)||
						(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0)||
						(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0)||
						(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0)||
						(i+2 < 8 && damier[i+2][j] == 0)||
						(i-2 >= 0 && damier[i-2][j] == 0)||
						(j+2 < 8 && damier[i][j+2] == 0)||
						(j-2 >= 0 && damier[i][j-2] == 0)) {
							finRouge = false;
						}
				}
			}
		}	
		//On remplace les cases vides en cases rouges si les verts n'ont plus de possibilités et réciproquement
		if(finVert == true){
			remplace(0,3);
		}
		else if(finRouge == true){
			remplace(0,2);
		}
		return(finVert == true || finRouge == true);
	}
	
	private void remplace(int a, int b) {
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				if(damier[i][j] == a)damier[i][j] = b;
			}
		}
		
	}

	public void jouer(int i, int j, int orgi, int orgj) {
		//Jouer en déplacemet
		int adv;
		if (donnees.getQuiJoue() == 2){
			adv = 3;
		}
		else{
			adv = 2;
		}
		damier[orgi][orgj] = 0;
		damier[i][j] = donnees.getQuiJoue();
		if(i+1 < 8 && damier[i+1][j] == adv) damier[i+1][j] = donnees.getQuiJoue();
		if(i-1 >= 0 && damier[i-1][j] == adv) damier[i-1][j] = donnees.getQuiJoue();
		if(j-1 >= 0 && damier[i][j-1] == adv) damier[i][j-1] = donnees.getQuiJoue();
		if(j+1 < 8 && damier[i][j+1] == adv) damier[i][j+1] = donnees.getQuiJoue();
		if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == adv) damier[i+1][j+1] = donnees.getQuiJoue();
		if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == adv) damier[i+1][j-1] = donnees.getQuiJoue();
		if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == adv) damier[i-1][j+1] = donnees.getQuiJoue();
		if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == adv) damier[i-1][j-1] = donnees.getQuiJoue();
				
		
	}

	public void clean() {
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				damier[i][j] = 0;
			}
		}
		
	}

	public void setDamierPrec() {
		affect(damierPrec,damier);
		
	}
	public void mettreDamierPrec() {
		affect(damier,damierPrec);
	}
	public void affect(int[][] damier,int[][] damier2) {
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				damier[i][j] = damier2[i][j];
			}
		}
	}

	public void cleanB() {
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				if(damier[i][j] == 2 || damier[i][j] == 3) damier[i][j] = 0;
			}
		}
		
	}


}
