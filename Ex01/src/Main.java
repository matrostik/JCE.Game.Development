import javax.swing.JFrame;

public class Main {

	public static void main(String[] args)
    {
        JFrame frame = new JFrame("Exersice 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 510);
        MainPanel p = new MainPanel();
        frame.add(p);
        frame.setVisible(true);
    }
	
}
