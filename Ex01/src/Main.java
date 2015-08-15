import javax.swing.JFrame;

public class Main {

	public static void main(String[] args)
    {
        JFrame frame = new JFrame("Exersice 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 510);
       // GraphicsPanel p = new GraphicsPanel();
       // Graphics2DPanel p = new Graphics2DPanel();
       // ClipPanel p = new ClipPanel();
       // ListenerPanel p = new ListenerPanel();
       // TimerPanel p = new TimerPanel();
        //DiminishPanel p = new DiminishPanel();
        MainPanel p = new MainPanel();
        frame.add(p);
        frame.setVisible(true);
        
    }
	
}
