
public class IA2 {
	private int[][] damier = new int[8][8];
	private int joueur;
	private int videDepart;
	private int vide;
	private int adversaire;
	private Boolean origine = false;
	
	public Boolean getOrigine() {
		return origine;
	}


	public void setOrigine(Boolean origine) {
		this.origine = origine;
	}


	public int min(int profondeur, int alpha, int beta){
		int evaluation = this.evaluation();
		if(profondeur == 0 || evaluation == -1000){
			return evaluation;
		}
		int valeur = 1000;
		/**/
		int[][] situation = new int[8][8];
		affect(situation,damier); //On sauvegarde notre situation initiale
		/*Pour toutes les cases*/
		for (int j = 0;j < 8; j++){
			for (int i = 0;i < 8 ; i++){
			/*Pour toutes les voisines ont applique l'algorithme min max et on prend la max*/
				//pour pas traiter les mêmes voisines on les prends à partie des cases vide
			if(damier[i][j] == Const.CASE0){
				if(i+1 < 8 && damier[i+1][j] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				
				}
				else if(i-1 >= 0 && damier[i-1][j] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(j-1 >= 0 && damier[i][j-1] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(j+1 < 8 && damier[i][j+1] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == adversaire) {
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == adversaire) {
					propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == adversaire) {
						damier[i+2][j] = 0; //déplacement
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == adversaire) {
						damier[i-2][j] = 0; //déplacement
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == adversaire) {
						damier[i][j+2] = 0; //déplacement
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == adversaire) {
						damier[i][j-2] = 0; //déplacement
						propagation(adversaire, i, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
						
				}
			}
		}
		/**/
		return valeur;
		
	}
	
	
	public int max(int profondeur,int alpha,int beta){
		int evaluation = this.evaluation();
		if(profondeur == 0 || evaluation == -1000){
			return evaluation;
		}
		int valeur = -1000;
		/**/
		int[][] situation = new int[8][8];
		affect(situation,damier); //On sauvegarde notre situation initiale
		
		/*Pour toutes les cases*/
		for (int j = 0;j < 8; j++){
			for (int i = 0;i < 8 ; i++){
			/*Pour toutes les voisines ont applique l'algorithme min max et on prend la max*/
			if(damier[i][j] == Const.CASE0){
				
				if(i+1 < 8 && damier[i+1][j] == joueur) {
						propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					
					//3)Annuler le coup
					affect(damier,situation);
				
				}
				else	if(i-1 >= 0 && damier[i-1][j] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(j-1 >= 0 && damier[i][j-1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(j+1 < 8 && damier[i][j+1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				else if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == joueur) {
					propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == joueur) {
						damier[i+2][j] = 0; //déplacement
						propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == joueur) {
						damier[i-2][j] = 0; //déplacement
						propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == joueur) {
						damier[i][j+2] = 0; //déplacement
						propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == joueur) {
						damier[i][j-2] = 0; //déplacement
						propagation(joueur, i, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
						
				}
			}
		}
		/**/
		return valeur;
		
	}

	public  int[] jouer(int profondeur)
	{
		int[] retour = new int[4]; //case + origine
		retour[0] = -1;
		retour[1] = -1;
		retour[2] = -1;
		retour[3] = -1;
		int max = -1000;
		int alpha = -10000;
		int beta = 10000;
		int[][] situation = new int[8][8];
		affect(situation,damier); //On sauvegarde notre situation initiale
		
		/*Pour toutes les cases*/
		for (int j = 0;j < 8; j++){
			for (int i = 0;i < 8 ; i++){
			/*Pour toutes les voisines ont applique l'algorithme min max et on prend la max*/
			if(damier[i][j] == Const.CASE0){
				if(i+1 < 8 && damier[i+1][j] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha,max);
					affect(damier,situation);

					
				}
				if(i-1 >= 0 && damier[i-1][j] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				else	if(j-1 >= 0 && damier[i][j-1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j-1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				else if(j+1 < 8 && damier[i][j+1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j+1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				else	if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j+1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				else if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j-1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
					
					
				}
				else if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j+1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				else	if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == joueur) {
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j-1;
						retour[2] = i;
						retour[3] = j;
						origine = false;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == joueur) {
					damier[i+2][j] = 0; //déplacement
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					//< au lieu de <= contre l'anti jeu de l'ordinateur
					if (max < evaluation){
						max = evaluation;
						retour[0] = i+2;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
						origine = true;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == joueur) {
					damier[i-2][j] = 0; //déplacement
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i-2;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
						origine = true;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == joueur) {
					damier[i][j+2] = 0; //déplacement
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j+2;
						retour[2] = i;
						retour[3] = j;
						origine = true;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == joueur) {
					damier[i][j-2] = 0; //déplacement
					propagation(joueur, i, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j-2;
						retour[2] = i;
						retour[3] = j;
						origine = true;
					}

					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
						
				}
			}
		}
		return retour;
		
	}/*
	int alphabeta(int profondeur, int alpha, int beta)
	{
	if (game_over or profondeur <= 0)
	return eval();
	move meilleur_coup;
	for (chaque coup possible m) {
	jouer le coup m;
	int score = -alphabeta(profondeur - 1, -beta, -alpha)
	annuler le coup m;
	if (score >= alpha){
	alpha = score ;
	meilleur_coup = m ;
	if (alpha >= beta)
	break;
	}
	}
	return alpha;
	}*/
	/*Permet de propager lorsqu'on joue une case*/
	private void propagation(int joueur, int i, int j){
		int adversaire = 2;
		if (joueur == 2){
			adversaire = 3;
		} 
		damier[i][j] = joueur;
		if(i+1 < 8 && damier[i+1][j] == adversaire) damier[i+1][j] = joueur;
		if(i-1 >= 0 && damier[i-1][j] == adversaire) damier[i-1][j] = joueur;
		if(j-1 >= 0 && damier[i][j-1] == adversaire) damier[i][j-1] = joueur;
		if(j+1 < 8 && damier[i][j+1] == adversaire) damier[i][j+1] = joueur;
		if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == adversaire) damier[i+1][j+1] = joueur;
		if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == adversaire) damier[i+1][j-1] = joueur;
		if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == adversaire) damier[i-1][j+1] = joueur;
		if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == adversaire) damier[i-1][j-1] = joueur;
	}
	
	
	/*Permet d'évaluer la situation du damier*/
	private int evaluation(){
		//Jeu cassé: aucune possibilité de bouffer l'adversaire;
		int eval = 0;
		for(int j = 0;j < 8;j++){
			for(int i = 0;i < 8;i++){
				if(damier[i][j] == this.joueur){
					eval++;
				}
				else if(damier[i][j] == this.adversaire){
					eval--;
				}
			}
		}	
		return eval;
	}
	public int[][] getDamier() {
		return damier;
	}
	public void setDamier(int[][] damier) {
		videDepart = 0;
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				this.damier[i][j] = damier[i][j];
				if(damier[i][j] == 0) videDepart++;
			}
		}
	}
	public void affect(int[][] damier,int[][] damier2) {
		vide = 0;
		for (int j = 0;j < 8 ; j++){
			for (int i = 0;i < 8 ; i++){
				damier[i][j] = damier2[i][j];
				if(damier[i][j] == 0) vide++;
			}
		}
	}


	public int getJoueur() {
		return joueur;
	}
	public void setJoueur(int joueur) {
		this.joueur = joueur;
		if(joueur == 2){
			adversaire = 3;
		}
		else{
			adversaire = 2;
		}
	}
	
	
}
