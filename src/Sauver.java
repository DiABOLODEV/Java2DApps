import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Sauver {
		private static JTextField nomMap = null;
		private static JFrame sauvegarder;
	public static void sauver(final Donnees donnees, final String terrain){
		JButton ok = new JButton("valider");
		sauvegarder = new JFrame();
		sauvegarder.setLocationRelativeTo(null);
		sauvegarder.setBounds(sauvegarder.getX()-200,sauvegarder.getY()-30,450, 100);
		sauvegarder.setIconImage(Imageuh.createImageIcon("icon.png","icone").getImage());
		sauvegarder.getContentPane().setBackground(Color.white);
		sauvegarder.setLayout(null);
		sauvegarder.setResizable(false);
		ok.setBounds(320, 20, 100, 30);
		nomMap = new JTextField("Nom du terrain");
		sauvegarder.setTitle("Sauvegarder un terrain");
		nomMap.setBounds(20, 20, 300, 30);
		sauvegarder.add(nomMap);
		sauvegarder.add(ok);
		
		class Cliquer implements ActionListener {
		    public void actionPerformed(ActionEvent e) {
		    	
				sauver(nomMap.getText(), terrain);
				donnees.updateVueCharger();
				sauvegarder.dispose();
				
					
		    }
		}
		ok.addActionListener(new Cliquer());
		sauvegarder.setVisible(true);
	}
	

		private static void sauver(String text, String terrain) {
			
			    try {
			    	FileWriter file_w = new FileWriter(new File(Const.PATHLVL),true); //true = append
					BufferedWriter output = new BufferedWriter(file_w);
					output.write('\n');
					output.write(terrain+Const.SPLIT+text);
					output.flush();
					output.close();
					Fenetre.info("Votre terrain a bien été sauvegardé. \nVeuillez patienter quelques instants avant que votre terrain apparaisse dans votre application.");
				} catch (IOException e) {
					Fenetre.warning("Impossible de sauvegarder le terrain: \n\terreur d'entrée/sortie (code:2).");
				}
			}

}
