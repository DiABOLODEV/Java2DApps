import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;


public class SauverReplay {
	private static String replay;
	public static void sauver(String txt){
		replay = txt;
		 JFileChooser chooser = new JFileChooser();
		 int retour=chooser.showSaveDialog(chooser);
		 if(retour == 0){
			 try {
			    	FileWriter file_w = new FileWriter(new File(chooser.getSelectedFile().getPath()+"."+Const.EXT_REPLAY),false); //true = append
					BufferedWriter output = new BufferedWriter(file_w);
					output.write(replay);
					output.flush();
					output.close();
					Fenetre.info("Votre replay a bien été sauvegardé.");
				} catch (IOException e) {
					Fenetre.warning("Impossible de sauvegarder le replay: \n\terreur d'entrée/sortie (code:2).");
				}
		 }

	}
}

