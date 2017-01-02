import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public class Son {
	public static final void pop(){
		URL url = Fichier.chargeSon("pop.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	public static final void action(){
		URL url =Fichier.chargeSon("bot_jouer.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	public static final void jouer(){
		URL url = Fichier.chargeSon("jouer.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	public static final void jeuFini(){
		URL url = Fichier.chargeSon("jeufini.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	
	public static final void dep(){
		URL url = Fichier.chargeSon("insertion.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	public static final void clean(){
		URL url = Fichier.chargeSon("etat0.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}

	public static final void bot(){
		URL url = Fichier.chargeSon("mc.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}
	public static final void passer(){
		URL url = Fichier.chargeSon("prompt.wav");
		AudioClip ac = Applet.newAudioClip(url);
		ac.play();
	}

}
