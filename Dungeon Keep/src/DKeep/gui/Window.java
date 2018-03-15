package DKeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DKeep.logic.Game;
import DKeep.logic.Hero;
import DKeep.logic.Ogre;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window {

	private JFrame frmDungeonKeep;
	private Game game;
	private int nOgres;
	private boolean playing;
	private boolean custom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmDungeonKeep.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JLabel lblMessage = new JLabel("Start a new game");;
		Map gameScreen = new Map();
		playing = false;
		custom = false;
		
		Options op = new Options();
		op.setVisible(false);

		frmDungeonKeep = new JFrame();
		frmDungeonKeep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(!playing)
					return;
				
				
				int id = e.getKeyCode();
				if(id == KeyEvent.VK_W)
				{
					game.heroMove('w');
				}
				else if(id == KeyEvent.VK_D)
				{
					game.heroMove('d');
				}
				else if(id == KeyEvent.VK_S)
				{
					game.heroMove('s');
				}
				else if(id == KeyEvent.VK_A)
				{
					game.heroMove('a');
				}
				else
				{
					return;
				}

				int status = game.evalStatus();

				if(status == 2)
				{
					lblMessage.setText("GAME OVER!");
					playing = false;
				}
				else if(status == 1)
				{
					if(game.getLevel() == 1)
					{
						if(custom)
						{
							game.advanceLevel(nOgres, op.map.clone());
						}
						else
							game.advanceLevel(nOgres);
					}
					else
					{
						lblMessage.setText("VICTORY!");
						playing = false;
					}					
				}
				gameScreen.setMap(game.getMap());
				frmDungeonKeep.repaint();
			}

		});
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 491, 548);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);


		lblMessage.setBounds(44, 473, 116, 16);
		frmDungeonKeep.getContentPane().add(lblMessage);

		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(44, 40, 116, 28);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);

		String[] ogreN = {"1", "2", "3", "4"};

		JComboBox<Object> cmbOgres = new JComboBox<Object>(ogreN);
		cmbOgres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		cmbOgres.setBounds(182, 43, 100, 22);
		frmDungeonKeep.getContentPane().add(cmbOgres);
		

		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(44, 81, 116, 16);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);

		String[] difficultLevels = {"Novice", "Intermediate", "Advanced"};

		JComboBox<Object> cmbPersonality = new JComboBox<Object>(difficultLevels);
		cmbPersonality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		cmbPersonality.setBounds(182, 78, 100, 22);
		frmDungeonKeep.getContentPane().add(cmbPersonality);

		gameScreen.setBounds(72, 124, 336, 323);
		frmDungeonKeep.getContentPane().add(gameScreen);



		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnExit.setBounds(340, 469, 97, 25);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				int cntHero = 0, cntKey = 0, cntOgre = 0, xHero = 0, yHero = 0;
				
				nOgres = Integer.parseInt((String)cmbOgres.getSelectedItem());
				
				if(!op.rdbtnRandom.isSelected())
				{
					for(int i = 0; i < op.map.length; i++)
					{
						for(int j = 0; j < op.map[i].length; j++)
						{
							switch(op.map[i][j])
							{
							case ' ':
							{
								break;
							}
							case 'X':
							{
								break;
							}
							case 'A':
							{
								cntHero++;
								xHero = j;
								yHero = i;
								break;
							}
							case '0':
							{
								cntOgre++;
								break;
							}
							case 'k':
							{
								cntKey++;
								break;
							}
							}
						}
					}
					if(cntHero != 1)
					{
						JOptionPane.showMessageDialog(frmDungeonKeep, "Illegal Map: Not exacly one Hero", "Error", 0);
						return;
					}
					
					if(cntKey != 1)
					{
						JOptionPane.showMessageDialog(frmDungeonKeep, "Illegal Map: Not exacly one Key", "Error", 0);
						return;
					}
					
					if(cntOgre != nOgres)
					{
						JOptionPane.showMessageDialog(frmDungeonKeep, "Illegal Map: Different number of Ogres than specified", "Error", 0);
						return;
					}
					
					if(op.map[yHero+1][xHero] == '0' || op.map[yHero-1][xHero] == '0' || op.map[yHero][xHero+1] == '0' || op.map[yHero][xHero-1] == '0')
					{
						JOptionPane.showMessageDialog(frmDungeonKeep, "Illegal Map: Hero next to an Ogre", "Error", 0);
						return;
					}
				}
				
				
				
				
				if(op.rdbtnRandom.isSelected())
					custom = false;
				else
					custom = true;
				game = new Game((String) cmbPersonality.getSelectedItem());
				playing = true;
				gameScreen.setMap(game.getMap());
				frmDungeonKeep.repaint();
				frmDungeonKeep.requestFocusInWindow();
			}
		});
		btnNewGame.setBounds(340, 40, 97, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
		
		
		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				op.setVisible(true);
			}
		});
		btnOptions.setBounds(340, 78, 97, 25);
		frmDungeonKeep.getContentPane().add(btnOptions);

	}

	public String printMap(char [][] map)
	{
		String result = "";

		for(char[] line : map)
		{
			for(char cell : line)
			{
				result += cell + " ";
			}
			result += "\n";
		}		
		return result;		
	}
}

















