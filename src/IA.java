public class IA {
	private int[][] damier = new int[8][8];
	private int joueur;
	private int videDepart;
	private int vide;
	private int adversaire;
	private int[] pattern ={
			0,0,1,2,2,1,0,0,
			0,2,2,3,3,2,2,0,
			1,2,3,4,4,3,2,1,
			2,3,4,5,5,4,3,2,
			2,3,4,5,5,4,3,2,
			1,2,3,4,4,3,2,1,
			0,2,2,3,3,2,2,0,
			0,0,1,2,2,1,0,0,
			};

	
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
			if(damier[i][j] == adversaire){
				
				if(i+1 < 8 && damier[i+1][j] == 0) {
						propagation(adversaire, i+1, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					
					//3)Annuler le coup
					affect(damier,situation);
				
				}
				if(i-1 >= 0 && damier[i-1][j] == 0) {
						propagation(adversaire, i-1, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-1 >= 0 && damier[i][j-1] == 0) {
						propagation(adversaire, i, j-1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+1 < 8 && damier[i][j+1] == 0) {
						propagation(adversaire, i, j+1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0) {
						propagation(adversaire, i+1, j+1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0) {
						propagation(adversaire, i+1, j-1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0) {
						propagation(adversaire, i-1, j+1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0) {
						propagation(adversaire, i-1, j-1);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation <= valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(adversaire, i+2, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(adversaire, i-2, j);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(adversaire, i, j+2);
						evaluation = max(profondeur-1,alpha,beta);
						if (evaluation < valeur) valeur = evaluation ;
						//Noeud min : coupure alpha
						if (alpha > valeur) return valeur;
						beta = Math.min(beta,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(adversaire, i, j-2);
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
			if(damier[i][j] == joueur){
				
				if(i+1 < 8 && damier[i+1][j] == 0) {
						propagation(joueur, i+1, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					
					//3)Annuler le coup
					affect(damier,situation);
				
				}
				if(i-1 >= 0 && damier[i-1][j] == 0) {
						propagation(joueur, i-1, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-1 >= 0 && damier[i][j-1] == 0) {
						propagation(joueur, i, j-1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+1 < 8 && damier[i][j+1] == 0) {
						propagation(joueur, i, j+1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0) {
						propagation(joueur, i+1, j+1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0) {
						propagation(joueur, i+1, j-1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0) {
						propagation(joueur, i-1, j+1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0) {
						propagation(joueur, i-1, j-1);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(joueur, i+2, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(joueur, i-2, j);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(joueur, i, j+2);
						evaluation = min(profondeur-1,alpha,beta);
						if (evaluation > valeur) valeur = evaluation ;
						//Noeud max = coupure beta
						if (beta < valeur) return valeur;
						alpha = Math.max(alpha,valeur);
					//3)Annuler le coup
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == 0) {
						damier[i][j] = 0; //déplacement
						propagation(joueur, i, j-2);
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
			if(damier[i][j] == joueur){
				if(i+1 < 8 && damier[i+1][j] == 0) {
					propagation(joueur, i+1, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha,max);
					affect(damier,situation);

					
				}
				if(i-1 >= 0 && damier[i-1][j] == 0) {
					propagation(joueur, i-1, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j-1 >= 0 && damier[i][j-1] == 0) {
					propagation(joueur, i, j-1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j-1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j+1 < 8 && damier[i][j+1] == 0) {
					propagation(joueur, i, j+1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j+1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(i+1 < 8 && j+1 < 8 && damier[i+1][j+1] == 0) {
					propagation(joueur, i+1, j+1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j+1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(i+1 < 8 && j-1 >= 0 && damier[i+1][j-1] == 0) {
					propagation(joueur, i+1, j-1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i+1;
						retour[1] = j-1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
					
					
				}
				if(i-1 >= 0 && j+1 < 8 && damier[i-1][j+1] == 0) {
					propagation(joueur, i-1, j+1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j+1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(i-1 >= 0 && j-1 >= 0 && damier[i-1][j-1] == 0) {
					propagation(joueur, i-1, j-1);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max <= evaluation){
						max = evaluation;
						retour[0] = i-1;
						retour[1] = j-1;
						retour[2] = -1;
						retour[3] = -1;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				
				if(i+2 < 8 && damier[i+2][j] == 0) {
					damier[i][j] = 0; //déplacement
					propagation(joueur, i+2, j);
					int evaluation = min(profondeur-1,alpha,beta);
					//< au lieu de <= contre l'anti jeu de l'ordinateur
					if (max < evaluation){
						max = evaluation;
						retour[0] = i+2;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(i-2 >= 0 && damier[i-2][j] == 0) {
					damier[i][j] = 0; //déplacement
					propagation(joueur, i-2, j);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i-2;
						retour[1] = j;
						retour[2] = i;
						retour[3] = j;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j+2 < 8 && damier[i][j+2] == 0) {
					damier[i][j] = 0; //déplacement
					propagation(joueur, i, j+2);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j+2;
						retour[2] = i;
						retour[3] = j;
					}
					//Noeud max = coupure beta
					if (beta < max) return retour;
					alpha = Math.max(alpha, max);
					affect(damier,situation);
				}
				if(j-2 >= 0 && damier[i][j-2] == 0) {
					damier[i][j] = 0; //déplacement
					propagation(joueur, i, j-2);
					int evaluation = min(profondeur-1,alpha,beta);
					if (max < evaluation){
						max = evaluation;
						retour[0] = i;
						retour[1] = j-2;
						retour[2] = i;
						retour[3] = j;
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
		int eval = 0;
		int augmentation = 0;
		/*
		int vide = 0;
		int plein = 0;
		int possibiliteJ = 0;
		int possibiliteAdv = 0;
		*/
		//Caractéristique n°1 Nombre de pions - important en milieu fin
		//Caractéristique n°3 : position - on ajoute +1/-1 si on a une possibilité de jouer -important en debut en milieu
		//Caractéristique n°2 : Mobilite -important en fin
		
		for(int j = 0;j < 8;j++){
			for(int i = 0;i < 8;i++){
				/*
				if (damier[i][j] == 0){
					vide ++;
				}
				else if (damier[i][j] == 1){
					plein ++;
				}*/
				if(damier[i][j] == this.joueur){
					//Caractéristique n°1
					//importance en fin de milieur et fin
					if(((float)vide/(float)videDepart) >= 0.65){
						eval += 5;
					}
					else{
						eval +=3;
					}
					eval += 6;
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
						//Caractéristique n°2 - à améliorer en fin
						if(((float)vide/(float)videDepart) < 0.8){
							eval += 3;
						}
						else{
							eval += 5;
						}
					}
					//Caractéristique n°3
					//System.out.println("Case en "+i+","+j+" - pattern : "+pattern[i+(8*j)]);
					//Caractéristique n°3 - important en début et milieu
					if(((float)vide/(float)videDepart) < 0.65){
						eval += pattern[i+(8*j)];
					}
					else{
						eval += pattern[i+(8*j)]-2;
					}
					
					
				}
				else if(damier[i][j] == this.adversaire){
						//Caractéristique n°1
						//importance en fin de milieur et fin
						if(((float)vide/(float)videDepart) >= 0.65){
							eval -= 5;
						}
						else{
							eval -=3;
						}
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
							//Caractéristique n°2 
							if(((float)vide/(float)videDepart) < 0.8){
								eval -= 3;
							}
							else{
								eval -= 5;
							}
						}
						//Caractéristique n°3
						//Caractéristique n°3 - important en début et milieu
						if(((float)vide/(float)videDepart) < 0.65){
							eval -= pattern[i+(8*j)];
						}
						else{
							eval -= pattern[i+(8*j)]-2;
						}
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
