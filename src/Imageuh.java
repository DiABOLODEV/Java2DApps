

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;



public class Imageuh {
	 protected static ImageIcon createImageIcon(String path,String description) {
		 URL imgURL = Fichier.chargerImg(path);
		 if (imgURL != null) {
			 return new ImageIcon(imgURL, description);
		 } else {
			 System.out.println("Erreur d'ouverture d'image");
			 return null;
		 }
		
	 }
	 protected static Image createImage(String path,String description) {
		 URL imgURL = Fichier.chargerImg(path);
		 if (imgURL != null) {
			 return new ImageIcon(imgURL, description).getImage();
		 } else {
			 System.out.println("Erreur d'ouverture d'image");
			 return null;
		 }
		
	 }
}
