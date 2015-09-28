import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Ros Haitovich
 */
public class GamePanel extends KeyAdapter {

	// fields
	private JFrame frame;
	private JTextField[][] tiles;

	public GamePanel()
	{
		// tiles
		tiles = new JTextField[4][4];
		// frame
		frame = new JFrame();
		frame.setTitle("2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// panel
		JPanel panel = new JPanel(new GridLayout(4, 4, 5, 5));
		setup(panel);
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);

		panel.addKeyListener(this);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
	}

	/**
	 * Start game
	 */
	public void start()
	{
		createNumber();
	}
	
	/**
	 * Restart game
	 */
	public void restart()
	{
		frame.dispose();
		(new GamePanel()).start();
	}

	/**
	 * Setup panel and create tiles
	 * @param panel
	 */
	private void setup(JPanel panel)
	{
		panel.setBackground(new Color(184, 148, 112));
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				tiles[i][j] = new JTextField();
				tiles[i][j].setBackground(new Color(0xcdc1b4));
				tiles[i][j].setFont(new Font("Tahoma", Font.BOLD, 25));
				tiles[i][j].setEditable(false);
				tiles[i][j].setBorder(null);
				tiles[i][j].setHorizontalAlignment(0);
				tiles[i][j].setForeground(new Color(0x776e65));
				tiles[i][j].setPreferredSize(new Dimension(100, 100));
				panel.add(tiles[i][j]);
			}
		}
	}

	/**
	 * Generate number
	 */
	private void createNumber()
	{
		Random random = new Random();
		int x = random.nextInt(4);
		int y = random.nextInt(4);
		while (!tiles[x][y].getText().equals(""))
		{
			x = random.nextInt(4);
			y = random.nextInt(4);
		}
		JTextField newButton = tiles[x][y];
		int randomNumber = random.nextInt(8);
		if (randomNumber < 7)
			updateTile(newButton, 2);
		else
			updateTile(newButton, 4);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		System.out.println(" keyPressed ");
		// do moving
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			moveLeft();
		else if (e.getKeyCode() == KeyEvent.VK_UP)
			moveUp();
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			moveRight();
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			moveDown();
		
		// check game status
		createNumber();      
		checkWin();
		checkLose();
	}

	/**
	 * Move tiles left
	 */
	private void moveLeft()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				if (!tiles[j][i].getText().equals(""))
				{
					String text = tiles[j][i].getText();
					int tmp = i;
					while (tmp > 0)
					{
						if (tiles[j][tmp - 1].getText().equals(text))
						{
							updateTile(tiles[j][tmp], 0);
							int newNumber = Integer.parseInt(text);
							newNumber *= 2;
							updateTile(tiles[j][tmp - 1], newNumber);
						}
						if (!tiles[j][tmp - 1].getText().equals(""))
						{
							break;
						}
						updateTile(tiles[j][tmp], 0);
						updateTile(tiles[j][tmp - 1], text);
						tmp--;
					}
				}
			}
		}
	}

	/**
	 * Move tiles up
	 */
	private void moveUp()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{

				if (!tiles[i][j].getText().equals(""))
				{
					String text = tiles[i][j].getText();
					int tmp = i;
					while (tmp > 0)
					{
						if (tiles[tmp - 1][j].getText().equals(text))
						{
							updateTile(tiles[tmp][j], 0);
							int newText = Integer.parseInt(text);
							newText *= 2;
							updateTile(tiles[tmp - 1][j], newText);
						}
						if (!tiles[tmp - 1][j].getText().equals(""))
						{
							break;
						}
						updateTile(tiles[tmp][j], 0);
						updateTile(tiles[tmp - 1][j], text);
						tmp--;
					}
				}
			}
		}
	}

	/**
	 * Move tiles right
	 */
	private void moveRight()
	{
		for (int i = tiles.length - 1; i >= 0; i--)
		{
			for (int j = tiles[i].length - 1; j >= 0; j--)
			{
				if (!tiles[j][i].getText().equals(""))
				{
					String text = tiles[j][i].getText();
					int tmp = i;
					while (tmp < 3)
					{
						if (tiles[j][tmp + 1].getText().equals(text))
						{
							updateTile(tiles[j][tmp], 0);
							int newText = Integer.parseInt(text);
							newText *= 2;
							updateTile(tiles[j][tmp + 1], newText);
						}
						if (!tiles[j][tmp + 1].getText().equals(""))
						{
							break;
						}
						updateTile(tiles[j][tmp], 0);
						updateTile(tiles[j][tmp + 1], text);
						tmp++;
					}
				}
			}
		}
	}

	/**
	 * Move tiles down
	 */
	private void moveDown()
	{
		for (int i = tiles.length - 1; i >= 0; i--)
		{
			for (int j = tiles[i].length - 1; j >= 0; j--)
			{

				if (!tiles[i][j].getText().equals(""))
				{
					String text = tiles[i][j].getText();
					int tmp = i;
					while (tmp < 3)
					{
						if (tiles[tmp + 1][j].getText().equals(text))
						{
							updateTile(tiles[tmp][j], 0);
							int newText = Integer.parseInt(text);
							newText *= 2;
							updateTile(tiles[tmp + 1][j], newText);
						}
						if (!tiles[tmp + 1][j].getText().equals(""))
						{
							break;
						}
						updateTile(tiles[tmp][j], 0);
						updateTile(tiles[tmp + 1][j], text);
						tmp++;
					}
				}
			}
		}
	}

	/**
	 * Check if game finished
	 */
	private void checkWin()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				if (tiles[i][j].getText().equals("2048"))
				{
					if (JOptionPane.showConfirmDialog(null, "You Win, Play again?", "You Win", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
						restart();
					else
						System.exit(0);
				}
			}
		}
	}

	/**
	 * Check if game is lost
	 */
	private void checkLose()
	{
		for (int i = 0; i < tiles.length; i++)
		{
			for (int j = 0; j < tiles[i].length; j++)
			{
				if (tiles[i][j].getText().equals(""))
					return;
			}
		}
		if (JOptionPane.showConfirmDialog(null, "Game over, Play again?", "Game over", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
			restart();
		else
			System.exit(0);
	}

	/**
	 * Set tile text. background and foreground
	 * @param tile
	 * @param number
	 */
	private void updateTile(JTextField tile, String number)
	{
		updateTile(tile, Integer.parseInt(number));
	}

	private void updateTile(JTextField tile, int number)
	{
		switch (number) {
		case 0:
			tile.setBackground(new Color(0xcdc1b4));
			break;
		case 2:
			tile.setBackground(new Color(0xeee4da));
			break;
		case 4:
			tile.setBackground(new Color(0xede0c8));
			break;
		case 8:
			tile.setBackground(new Color(0xf2b179));
			break;
		case 16:
			tile.setBackground(new Color(0xf59563));
			break;
		case 32:
			tile.setBackground(new Color(0xf67c5f));
			break;
		case 64:
			tile.setBackground(new Color(0xf65e3b));
			break;
		case 128:
			tile.setBackground(new Color(0xedcf72));
			break;
		case 256:
			tile.setBackground(new Color(0xedcc61));
			break;
		case 512:
			tile.setBackground(new Color(0xedc850));
			break;
		case 1024:
			tile.setBackground(new Color(0xedc53f));
			break;
		case 2048:
			tile.setBackground(new Color(0xedc22e));
			break;
		}
		tile.setForeground(number < 16 ? new Color(0x776e65) : new Color(0xf9f6f2));
		tile.setText(number == 0 ? "" : Integer.toString(number));
	}

}
