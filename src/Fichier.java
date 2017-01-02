import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;


public class Fichier {
	private static URLClassLoader urlLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
	public static URL chargerImg(String fichier) {
		return urlLoader.findResource(Const.PATHIMG+"/"+fichier);
	}
	public static URL chargeSon(String fichier) {
		return urlLoader.findResource(Const.PATHSONS+"/"+fichier);
	}
	public static InputStream chargeLvl() {
		return urlLoader.getResourceAsStream(Const.PATHLVL);

	}

	
	public static Boolean fichierExist(String path){
		return false;
	}
	public static Boolean creerFichier(String path){
		Boolean retour = false;
		File file = new File(Const.PATHLVL);
		if(!file.exists()){
			try{
				file.createNewFile();
				Fenetre.info("Oups le fichier "+Const.PATHLVL+" est introuvable dans le répertoire courant du logiciel.\n Ce " +
						"fichier sert à stocker tout vos terrains de jeu. Le fichier viens d'être crée \n-" +
						" ne le supprimé pas si vous voulez garder vos terrains et ne déplacez pas le programme\n sans déplacer ce fichier avec et vice/versa.");
				retour = true;
			}
			catch(Exception e){
				Fenetre.info("Oups le fichier "+Const.PATHLVL+" est introuvable dans le répertoire courant du logiciel.\n Ce " +
						"fichier sert à stocker tout vos terrains de jeu. Le fichier n'a pas pû être crée, l'application va se fermer.\n" +
						"Vous pouvez essayer de le créer par vous même et relancer l'application.");
			}
				
			}
		
		
	
		return retour;
	}


}
