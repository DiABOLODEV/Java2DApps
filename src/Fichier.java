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
				Fenetre.info("Oups le fichier "+Const.PATHLVL+" est introuvable dans le r�pertoire courant du logiciel.\n Ce " +
						"fichier sert � stocker tout vos terrains de jeu. Le fichier viens d'�tre cr�e \n-" +
						" ne le supprim� pas si vous voulez garder vos terrains et ne d�placez pas le programme\n sans d�placer ce fichier avec et vice/versa.");
				retour = true;
			}
			catch(Exception e){
				Fenetre.info("Oups le fichier "+Const.PATHLVL+" est introuvable dans le r�pertoire courant du logiciel.\n Ce " +
						"fichier sert � stocker tout vos terrains de jeu. Le fichier n'a pas p� �tre cr�e, l'application va se fermer.\n" +
						"Vous pouvez essayer de le cr�er par vous m�me et relancer l'application.");
			}
				
			}
		
		
	
		return retour;
	}


}
