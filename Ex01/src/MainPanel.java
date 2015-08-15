import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.MyComboBoxModel;
import model.MyJspinner;
import model.Planet;

/**
 * 
 * @author Ros Haitovich
 *
 */
public class MainPanel extends JPanel {

	// controls
	JPanel pnlMain;
	private JButton cmdReset,cmdStart;
	private JSpinner spinnerElasticity, spinnerHeight;
	private MyJspinner spinnerGravitiy;
	private JComboBox<Planet> planetsList;

	//fields
	private Timer timer;
	private int interval = 5;
	private int currentHeight = 50;
	private int startY = 50; 
	private final int topY = 50;
	private final int bottomY = 450;
	private boolean isFalling = true;
	private boolean isRunning = false;
	private Planet selectedPlanet;
	Planet[] planets;

	public MainPanel()
	{
		// init data
		planets = new Planet[] {
			new Planet("Earth",9.8,0.8,false),
			new Planet("Moon",1.6,0.8,false),
			new Planet("Mars",3.8,0.0,false),
			new Planet("Select",5.0,0.8,true)
		};
		selectedPlanet = planets[0];
		// set controls
		initControls();
	}

	/**
	 * Init controls
	 */
	private void initControls() {

		timer = new Timer(interval, new TimerListener());

		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setLocation(100, 1000);

		JPanel pnlTop = new JPanel(new GridLayout(1, 6, 0, 0));

		SpinnerModel gravitiyModel = new SpinnerNumberModel(0, 0, 15, 0.1);
		spinnerGravitiy = new MyJspinner(gravitiyModel);

		SpinnerModel elasticityModel = new SpinnerNumberModel(0, 0, 1, 0.1);
		spinnerElasticity = new JSpinner(elasticityModel);

		SpinnerModel heightModel = new SpinnerNumberModel(1, 0, 20, 1);
		spinnerHeight = new JSpinner(heightModel);
		spinnerHeight.addChangeListener (new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				heightValueChanged(e);
			}
		});

		planetsList = new JComboBox<>(new MyComboBoxModel<Planet>(planets));
		planetsList.addActionListener (new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				planetsSelectionChanged(e);
			}
		});
		planetsList.setSelectedIndex(0);

		cmdReset = new JButton("Reset");
		cmdReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked(e);
			}
		});

		cmdStart = new JButton("Start");
		cmdStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startButtonClicked(e);
			}
		});

		pnlTop.add(planetsList);
		pnlTop.add(spinnerGravitiy);
		pnlTop.add(spinnerElasticity);
		pnlTop.add(spinnerHeight);
		pnlTop.add(cmdReset);
		pnlTop.add(cmdStart);

		pnlMain.add(pnlTop, BorderLayout.NORTH);

		add(pnlMain);
	}

	/*
	 * Paint component
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Image img = Toolkit.getDefaultToolkit().getImage(selectedPlanet.getName().toLowerCase() + ".jpg");
		g2d.drawImage(img, 0, 0, this);

		int w = getWidth();
		//int h = getHeight();
		if(isRunning){
			g.setColor(Color.GREEN);
			g.fillOval(w/2-12, currentHeight, 25, 25);
		}
	}

	/*
	 * Planet selected
	 */
	private void planetsSelectionChanged(ActionEvent e) {
		// get selected planet
		JComboBox<Planet> cb = (JComboBox<Planet>)e.getSource();
		Planet s = (Planet)cb.getSelectedItem();
		// set values
		spinnerGravitiy.setValue(s.getGravity());
		spinnerElasticity.setValue(s.getElasticity());
		spinnerHeight.setValue(10);
		//System.out.println(s.getIsEditable());
		spinnerGravitiy.setEditable(s.getIsEditable());
	}

	/*
	 * Height changed
	 */
	private void heightValueChanged(ChangeEvent e) {
		JSpinner sp = (JSpinner)e.getSource();
		int selected = (int)sp.getValue();
		currentHeight = bottomY - selected*20;
		startY = currentHeight;
		System.out.println(currentHeight);
	}

	/*
	 * Reset the game
	 */
	private void resetButtonClicked(ActionEvent e) {
		planetsList.setSelectedItem(selectedPlanet);
		spinnerHeight.setValue(10);
		timer.stop();
		isRunning = false;
		interval = 5;
		timer.setDelay(interval);
		currentHeight = 250;
		isFalling = true;
		repaint();
	}

	/*
	 * Start the game
	 */
	private void startButtonClicked(ActionEvent e) {
		///pnlMain.repaint();
		selectedPlanet= (Planet) planetsList.getSelectedItem();
		currentHeight = bottomY -(int)spinnerHeight.getValue() * 20;
		interval = 5;
		timer.setDelay(interval);
		timer.start();
		isRunning = true;
		this.repaint();
	}

	/*
	 * Time tick event
	 */
	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if(bottomY <= currentHeight){
				//currentHeight = bottomY+1;
				isFalling = false;
				interval *= (2 - (double)spinnerElasticity.getValue());
				startY *= (2-(double)spinnerElasticity.getValue());
				System.out.println("startY -        " + startY);
				System.out.println("currentHeight - " + currentHeight);
				System.out.println("bottomY -	    " + bottomY);
				System.out.println("interval -	    " + interval);
				timer.setDelay(interval);
			}
			else if(startY >= currentHeight){
				//currentHeight = startY;
				isFalling = true;
			}
			if(startY >= bottomY)
			{
				startY = bottomY -(int)spinnerHeight.getValue() * 20;
				timer.stop();
			}
			currentHeight = !isFalling ? currentHeight-5 : currentHeight+5;
			//System.out.println("interval - " + interval);
			//System.out.println("startY - " + startY);
			//System.out.println("currentHeight - " + currentHeight);
			repaint();
		}
	}

}
