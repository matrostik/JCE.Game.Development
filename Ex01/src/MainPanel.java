import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.MyComboBoxModel;
import model.Planet;


/**
 * 
 * @author matro
 *
 */
public class MainPanel extends JPanel {

	// controls
	JPanel pnlMain;
	private JButton cmdReset,cmdStart;
	private JSpinner spinnerGravitiy, spinnerElasticity, spinnerHeight;
	private JComboBox<Planet> planetsList;
	//fields
	private int startHeight = 50;

	private Planet selectedPlanet;
	Planet[] planets;

	public MainPanel()
	{
		// init data
		planets = new Planet[] {
				new Planet("Earth",9.8,0.0),
				new Planet("Moon",1.6,0.0),
				new Planet("Mars",3.8,0.0),
				new Planet("Default",9.8,0.8),
				new Planet("Select",9.8,0.8)
		};
		selectedPlanet = planets[0];
		// set controls
		initControls();
	}

	private void initControls() {

		pnlMain = new JPanel(new BorderLayout());
		pnlMain.setLocation(100, 1000);

		JPanel pnlTop = new JPanel(new GridLayout(1, 6, 0, 0));

		SpinnerModel gravitiyModel = new SpinnerNumberModel(0, 0, 15, 0.1);
		spinnerGravitiy = new JSpinner(gravitiyModel);

		SpinnerModel elasticityModel = new SpinnerNumberModel(0, 0, 15, 0.1);
		spinnerElasticity = new JSpinner(elasticityModel);

		SpinnerModel heightModel = new SpinnerNumberModel(10, 0, 100, 1);
		spinnerHeight = new JSpinner(heightModel);

		planetsList = new JComboBox<>(new MyComboBoxModel(planets));
		planetsList.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				planetsSelectionChanged(e);
			}
		});
		planetsList.setSelectedIndex(0);




		cmdReset = new JButton("Reset");
		cmdReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetButtonClicked(e);
			}
		});
		//cmdStart.setPreferredSize(new Dimension(0, 35));
		cmdStart = new JButton("Start");
		cmdStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startButtonClicked(e);
			}
		});

		//cmdStop.setPreferredSize(new Dimension(0, 35));

		pnlTop.add(planetsList);
		pnlTop.add(spinnerGravitiy);
		pnlTop.add(spinnerElasticity);
		pnlTop.add(spinnerHeight);
		pnlTop.add(cmdReset);
		pnlTop.add(cmdStart);

		pnlMain.add(pnlTop, BorderLayout.NORTH);

		add(pnlMain);
	}


	/**
	 * Paint component
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Image img = Toolkit.getDefaultToolkit().getImage(selectedPlanet.getName().toLowerCase() + ".jpg");
		g2d.drawImage(img, 0, 0, this);

		int w = getWidth();
		int h = getHeight();

		g.setColor(Color.GREEN);
		g.fillRect(w/2-12, startHeight, 25, 25);
	}

	/*
	 * Planet selected
	 */
	private void planetsSelectionChanged(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox<Planet> cb = (JComboBox<Planet>)e.getSource();

		Planet s = (Planet)cb.getSelectedItem();
		System.out.println(s);
		spinnerGravitiy.setValue(s.getGravity());

	}

	/*
	 * 
	 */
	private void resetButtonClicked(ActionEvent e) {

	}

	/*
	 * 
	 */
	private void startButtonClicked(ActionEvent e) {
		///pnlMain.repaint();
		selectedPlanet= (Planet) planetsList.getSelectedItem();
		this.repaint();
	}

}
