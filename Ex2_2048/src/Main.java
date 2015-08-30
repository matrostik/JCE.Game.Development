import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * 
 */

/**
 * @author Ros
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame game = new JFrame(); 
		game.setTitle("2048 Game"); 
		game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
		game.setSize(350, 400); 
		game.setResizable(false); 
		game.add(new GamePanel()); 
		game.setLocationRelativeTo(null); 
		game.setVisible(true);

	}

}
