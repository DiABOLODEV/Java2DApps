import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;


public class ElementActionMove extends TimerTask {
	private Timer actionTimer;
	private VueCase component;
	private int x;
	private int y;
	private Rectangle rectangle;
	private int destx;
	private int desty;
	private VueTerrain terrain;
	
	public ElementActionMove(VueTerrain terrain, int etat, Timer t, int x, int y, int destx, int desty) {
		this.terrain = terrain;
		this.actionTimer = t;
		component = new VueCase(terrain,etat, x, y);
		component.setBounds((x*Const.TAILLE_IMG)+30, (y*Const.TAILLE_IMG)+80, Const.TAILLE_IMG,Const.TAILLE_IMG);
		terrain.add(component);
		terrain.setComponentZOrder(component, 0);
		this.x = x;
		this.y = y;
		this.destx = (destx*Const.TAILLE_IMG)+30;
		this.desty = (desty*Const.TAILLE_IMG)+80;
		//On cr�e le bouton en x,y et on le d�place en destx,desty
	}

	/**
	 * D�marre l'action
	 */
	public void run() {
	 //r�cup�re la position actuelle de l'�l�ment
		 this.rectangle = this.component.getBounds();
		 //Si on a pas encore atteint la position x finale on d�place x de 1
		 if(rectangle.x != destx){
		 rectangle.x = (rectangle.x > destx) ? --rectangle.x : ++rectangle.x;}
		 //Si on a pas encore atteint la position y finale on d�place y de 1
		 if(rectangle.y != y){
		 rectangle.y = (rectangle.y > desty) ? --rectangle.y : ++rectangle.y;}
		 //on d�place le composant
		 this.component.setBounds(rectangle);
		 //si le composant a atteint sa destination finale on arr�te l'action 
		    if(rectangle.x == destx && rectangle.y == desty){
		     actionTimer.cancel();
		     terrain.remove(component);
		     terrain.getVueDamier()[x][y].setVisible(true); //dans le cas d'une deprop
		     terrain.getOrigine().setVisible(true); //yes animation pour pas voir l'origine
		     terrain.refresh();
		 } 
		}
	}

