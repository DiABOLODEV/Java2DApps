/*Permet de débuter un match*/
public class Match extends Thread{
	private Donnees donnees;
	private Boolean running = true;
	private VueTerrain vueTerrain;
	private Boolean receivPos = false;
	private IA2 ia;
	private boolean fini = false;

	
	public Match(Donnees donnees, VueTerrain vueTerrain){
		this.ia = new IA2();
		this.vueTerrain = vueTerrain;
		this.donnees = donnees;
		donnees.resetReplay();
		//donnees.getTerrain().setAleaTerrain();
		//On met le damierPrec a jour
		donnees.getTerrain().setDamierPrec();
		donnees.updateVueTerrain();
		/*On raffraichi le score*/
		donnees.getTerrain().calculerScore();
		this.vueTerrain.noAutoriser(); 
		
	}
	public void run() {

		/*oN REGARDE QUE LE MATCH NE SOIT PAS FINI POUR LES MALIN QUI FONT DE L'edition a la mormoilenoeud*/
		if (donnees.getTerrain().isFini()){
			this.arreter();
		}
		
		donnees.setMatch(!fini); // match en cours yes
		donnees.setLastMove(donnees.getTerrain().toString()); //enregistrer le dernier coup pour "annuler vcoup"
		
		while(this.running && !fini){
	
			
			//1) Si on a l'option jouer un coup sur deux on fait jouer l'ordinateur 1c/2
			if(donnees.getiAauto()){
				this.vueTerrain.noAutoriser();
				this.jouerIA(donnees.getNiveauIA(),true);
				donnees.getTerrain().calculerScore();
				donnees.changerQuiJouer();
				
				if (donnees.getTerrain().isFini()){

					this.arreter();
				}
				
			}
			
			//2) Sinon le jeu se déroule normalement avec une interruption lors de la réception d'une position
			this.vueTerrain.autoriser(donnees.getQuiJoue());

			try {
				while(true){
					Thread.sleep(20);
					this.receivPositionEff(); //Si on reçois la pos alors on a la position reçue
				}
			}
			//J'attend que qqun joue avec une interrrrrruption
			
		    catch (InterruptedException e) {
		    	this.receivPos=false;
			}
			/*CONFLIT CAR L'INTTERUPTION INPTERROMPTS AUSSI LE SLEEP DE PREVIEW DUP ET DEP pour l'IA :s*/
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//vueTerrain.refresh();
			donnees.getTerrain().calculerScore();
			/*On change de joueur*/
			this.vueTerrain.noAutoriser();
			donnees.changerQuiJouer();
			
			if (donnees.getTerrain().isFini()){

				this.arreter();
			}

		}
		
	}
	public VueTerrain getVueTerrain() {
		return vueTerrain;
	}

	//Si on reçois une position Hop on fait une intteruption dans l'attente de notre Thread pour faire jouer le jouer suivant
	public synchronized void receivPositionEff() throws InterruptedException {
		if(this.receivPos)throw new InterruptedException();
        
	} 	
	public void arreter() {
		donnees.setMatch(false);
		this.fini = true;
		this.vueTerrain.refresh();
		donnees.getTerrain().calculerScore();
		donnees.updateVueInfos("fini");
		/*On autorise pour le mode d'édition*/
		this.vueTerrain.autoriser(0);
		this.vueTerrain.autoriser(1);
		this.vueTerrain.autoriser(2);
		this.vueTerrain.autoriser(3);
		donnees.setLastMove(""); //match terminé pas de possibilité de retourner en arriére
		this.running = false;
		
	}
	public void setReceivPos(Boolean receivPos) {
		this.receivPos = receivPos;
	}
	public void jouerIA(int niveau, Boolean auto){
		donnees.getTerrain().resetPossibilite(); // on enléve les possibilité si on clique sur jouer avant de jouer le bot
		ia.setDamier(donnees.getTerrain().getDamier());
		ia.setJoueur(donnees.getQuiJoue());
		int[] j = ia.jouer(niveau);
		System.out.print("x : "+j[2]+"; y :"+j[3]+"; orgx :"+j[0]+"; orgy : "+j[1]);
		if(j[0] != -1 && j[1] != -1){
			this.vueTerrain.setOrigine(vueTerrain.getVueDamier()[j[0]][j[1]]);
			if(ia.getOrigine()){
				//vueTerrain.previewDep(j[0], j[1],j[2],j[3]);
				this.vueTerrain.terrainJouerIA(j[2], j[3], 1);
			}
			else {
				//vueTerrain.previewDup(j[0], j[1]);
				//donnees.getTerrain().jouer(j[0], j[1]);
				this.vueTerrain.terrainJouerIA(j[2], j[3], 0);
			}
			
		}
		else{
			System.out.println("L'ordinateur ne sais pas où jouer");
		}
		//Si on jouer pas en automatique
		if(!auto){
			this.setReceivPos(true);
		}
	}
	//Permet de sauter son tour
	public void passerTour() {
		this.setReceivPos(true);
		
	}
	
	public void setFini(boolean fini) {
		this.fini = fini;
	}
	public boolean isFini() {
		// TODO Auto-generated method stub
		return fini;
	}


		
	

}
