



import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;



public class Fenetre {

	
	public static void error(String txt){
		JOptionPane.showMessageDialog(new JDialog(),txt,"Erreur",JOptionPane.INFORMATION_MESSAGE,Imageuh.createImageIcon("/error.png", "Erreur"));
		
	}
	public static void warning(String txt){
		JOptionPane.showMessageDialog(new JDialog(),txt,"Attention",JOptionPane.WARNING_MESSAGE);
	}
	public static void info(String txt){
		JOptionPane.showMessageDialog(new JDialog(),txt, "Informations",JOptionPane.INFORMATION_MESSAGE);
	}
	public static int question(String txt,String[] options){

		return JOptionPane.showOptionDialog(new JDialog(), txt, "Question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, Imageuh.createImageIcon("/defi.png", "Defi"), options, options[0]);
	}
	public static void safe(String txt){
		JOptionPane.showMessageDialog(new JDialog(),txt, "Informations",JOptionPane.INFORMATION_MESSAGE);
	}
	public static void terrain(String txt) {
		JFrame frame = new JFrame("Terrain");
		JTextArea text = new JTextArea(txt);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		text.setEditable(true);
		text.selectAll();
		frame.add(text);
		frame.setSize(20,160);
		frame.setVisible(true);
		
		
	}
	public static void splash(String txt, int ms) {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setBounds(frame.getX()-207, frame.getY()-140, 514,280);

		frame.setLayout(null);
		frame.setUndecorated(true);
		
		JLabel credits = new JLabel(Imageuh.createImageIcon("splash.png", "credits"));
		credits.setBounds(0,0,514,280);
		frame.add(credits);
		frame.setVisible(true);
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.dispose();
		
	}
	public static String dialogue() {
		return JOptionPane.showInputDialog(new JDialog(), "Pourquoi vous allez jouer ce coup?","",JOptionPane.INFORMATION_MESSAGE);
	}

	
	

}
