import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Replay{
private Donnees parent;
private String map;
private ArrayList<int[]> coups = new ArrayList<int[]>();
private ArrayList<String> situation = new ArrayList<String>();
private ArrayList<String> commentaires = new ArrayList<String>();
private BufferedReader input;
private VueTerrain vueTerrain;
private VueReplay vueReplay;
private int coup = 0;
private VueInfos vueInfos;
private String presentation = "";
private String replay = "";

	public Replay(Donnees parent,VueInfos vueInfos,VueReplay vueReplay,VueTerrain vueTerrain,String replay){
		this.replay = replay;
		this.vueReplay = vueReplay;
		this.vueInfos = vueInfos;
		this.parent = parent;
		this.vueTerrain = vueTerrain;
		try {
			File f = new File(replay);
			FileReader file = new FileReader(f);
			this.replay = f.getName();
			input = new BufferedReader(file);
			String ligne = input.readLine();
			String[] elements;
			while(ligne != null){
				if (ligne.length() > 0){
					elements = ligne.split(Const.SPLIT);
					if(elements[0].equals("i")){
						map = elements[1];
						presentation = elements[2];
						
					}
					else{
						int[] coup = new int[6];
						coup[0] = Integer.valueOf(elements[0]);
						coup[1] = Integer.valueOf(elements[1]);
						coup[2] = Integer.valueOf(elements[2]);
						coup[3] = Integer.valueOf(elements[3]);
						coup[4] = Integer.valueOf(elements[4]);
						coup[5] = Integer.valueOf(elements[5]);
						coups.add(coup);
						commentaires.add(elements[6]);
					}
				}
				ligne= input.readLine(); 
			
			}
			//On met la map à jour
			parent.setRep(true); //REPLAY EN COURS
			parent.setQuiJoue(coups.get(coup)[0]);
			init();
		} catch (IOException e) {
			vueReplay.setMessage("Impossible de lire le replay.");
			Fenetre.info("J'ai beau faire de mon mieux, j'arrive pas à lire ton replay <D");
		}
		

	}
	
	public void init(){
		coup = 0;
		parent.getTerrain().init(map);
		parent.getTerrain().calculerScore();
		parent.updateVueInfos("infos");
		vueInfos.insererMessage("Replay <i>"+replay+"</i>",presentation);
		parent.updateVueTerrain();
		updateMessage();
		vueTerrain.noAutoriser();
		vueTerrain.autoriser(2);
		vueTerrain.autoriser(3);
	}
	public void updateMessage(){
		vueReplay.setMessage("Coup "+coup+"/"+(coups.size()));

	}
	public void next() {
		if(coup<coups.size()){
			int[] c = coups.get(coup);
			parent.setQuiJoue(c[0]);
			//On enregistre la situation
			try{
				situation.get(coup);
			}
			catch (Exception e){
				parent.getTerrain().resetPossibilite();
				situation.add(parent.getTerrain().toString());
			}
			
			vueTerrain.setOrigine(vueTerrain.getVueDamier()[c[4]][c[5]]);
			vueTerrain.terrainJouerReplay(c[2], c[3], c[1]);
			parent.getTerrain().calculerScore();
			//vueTerrain.autoriser(2);
			//vueTerrain.autoriser(3);
			//vueTerrain.terrainResetPossibilite();
			parent.updateVueInfos("infos");
			vueInfos.insererMessage("Replay <i>"+replay+"</i>",commentaires.get(coup));
			
			coup ++;
			updateMessage();
		}
		
	}

	public void nextt() {
		//on boucle jusqu'à arriver au debut
		while(coup < coups.size()){
			int[] c = coups.get(coup);
			parent.setQuiJoue(c[0]);
			//On enregistre la situation
			try{
				situation.get(coup);
			}
			catch (Exception e){
				situation.add(parent.getTerrain().toString());
			}

			vueTerrain.setOrigine(vueTerrain.getVueDamier()[c[4]][c[5]]);
			vueTerrain.terrainJouerReplay(c[2], c[3], c[1]);
			coup ++;
		}
		parent.getTerrain().calculerScore();
		vueTerrain.autoriser(2);
		vueTerrain.autoriser(3);
		vueTerrain.terrainResetPossibilite();
		parent.updateVueInfos("infos");
		vueInfos.insererMessage("Replay <i>"+replay+"</i>",commentaires.get(coup-1));
		
		updateMessage();
		
		
	}

	public void prev() {
		if(coup>0){
			coup--;
			
			int[] c = coups.get(coup);
			vueTerrain.setOrigine(vueTerrain.getVueDamier()[c[4]][c[5]]);
			vueTerrain.deJouer(c[2], c[3], situation.get(coup));
			parent.getTerrain().calculerScore();
			parent.updateVueInfos("infos");
			if(coup == 0){
				vueInfos.insererMessage("Replay <i>"+replay+"</i>",presentation);
			}else{
				vueInfos.insererMessage("Replay <i>"+replay+"</i>",commentaires.get(coup));
			}
			vueTerrain.autoriser(2);
			vueTerrain.autoriser(3);
			vueTerrain.terrainResetPossibilite();
			updateMessage();
			
			
		}
		else{
			//on charge la situation de départ
			init();
		}
		
	}

	public void prevv() {
		//on boucle jusqu'à arriver au debut
		while(coup > 0){
			coup --;
			int[] c = coups.get(coup);
			vueTerrain.setOrigine(vueTerrain.getVueDamier()[c[4]][c[5]]);
			vueTerrain.deJouer(c[2], c[3], situation.get(coup));
			
		}
		parent.getTerrain().calculerScore();
		parent.updateVueInfos("infos");
		vueInfos.insererMessage("Replay <i>"+replay+"</i>",presentation);
		updateMessage();
	}


}