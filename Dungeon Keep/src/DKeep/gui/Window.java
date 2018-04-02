package DKeep.gui;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import DKeep.logic.Drunken;
import DKeep.logic.Game;
import DKeep.logic.Ogre;
import DKeep.logic.Rookie;
import DKeep.logic.Suspicious;

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
		frmDungeonKeep.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/DungeonKeep_TORCH.png")));
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
							char[][] map_c = new char[op.map.length][op.map[0].length];
							for(int i = 0; i < op.map.length; i++)
							{
								map_c[i] = op.map[i].clone();
							}
							game.advanceLevel(nOgres, map_c);
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


		lblMessage.setBounds(44, 473, 105, 16);
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

		btnExit.setBounds(366, 469, 97, 25);
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

					if(!isGamePossible(op.map, xHero, yHero))
					{
						JOptionPane.showMessageDialog(frmDungeonKeep, "Illegal Map: The level is impossible", "Error", 0);
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

			private boolean isGamePossible(char[][] map, int xHero, int yHero) {

				boolean[][] visited = new boolean[map.length][map[0].length];
				int[] xyKey = {0,0};
				int[] xyDoor = {0,0};

				return findGoal(map,visited,xHero,yHero,xyKey,xyDoor);
			}

			private boolean findGoal(char[][] map, boolean[][] visited, int x, int y, int[] xyKey, int[] xyDoor) {

				visited[y][x] = true;

				if(map[y][x] == 'k')
				{					
					xyKey[0] = x;
					xyKey[1] = y;
				}
				if(map[y][x] == 'I')
				{
					xyDoor[0] = x;
					xyDoor[1] = y;										
				}

				if(visited[xyKey[1]][xyKey[0]] && visited[xyDoor[1]][xyDoor[0]])
				{
					return true;
				}

				if(y-1 >= 0 && map[y-1][x] != 'X' && !visited[y-1][x])
				{
					if(findGoal(map,visited,x,y-1,xyKey,xyDoor))
						return true;
				}

				if(map[y+1][x] != 'X' && !visited[y+1][x])
				{
					if(findGoal(map,visited,x,y+1,xyKey,xyDoor))
						return true;
				}

				if(map[y][x+1] != 'X' && !visited[y][x+1])
				{
					if(findGoal(map,visited,x+1,y,xyKey,xyDoor))
						return true;
				}

				if(x-1 >= 0 && map[y][x-1] != 'X' && !visited[y][x-1])
				{
					if(findGoal(map,visited,x-1,y,xyKey,xyDoor))
						return true;
				}

				return false;
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

		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Dungeon Keep Save Files (*.dks)", "dks");
		fc.addChoosableFileFilter(filter);
		fc. setAcceptAllFileFilterUsed(false);

		JButton mntmSaveGame = new JButton("Save");
		mntmSaveGame.setBounds(159, 469, 97, 25);
		mntmSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDungeonKeep.requestFocusInWindow();
				int returnVal = fc.showSaveDialog(frmDungeonKeep);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();

					String file_name = file.toString();
					if (!file_name.endsWith(".dks"))
						file_name += ".dks";

					File file_2 = new File(file_name);


					if(file.exists() || file_2.exists())
					{
						if(JOptionPane.showConfirmDialog(frmDungeonKeep, "Overwrite file?") == 0)
							file.delete();
						else
							return;

					}


					try 
					{
						BufferedWriter writer = new BufferedWriter(new FileWriter(file_name));
						writer.write(nOgres + "\n");
						writer.write(playing + "\n");
						writer.write(custom + "\n");
						writer.write(op.map.length + "\n");
						writer.write(op.map[0].length + "\n");


						for(char[] line : op.map)
						{
							for (char c : line)
							{
								writer.write(c + "\n");
							}
						}

						writer.write(op.comboBox.getSelectedItem() + "\n");
						writer.write(op.comboBox_1.getSelectedItem() + "\n");

						writer.write(cmbPersonality.getSelectedItem() + "\n");

						writer.write(game.getLevel() + "\n");

						game.getHero().saveGame(writer);
						game.getGuard().saveGame(writer);

						writer.write(game.getMap().length + "\n");
						writer.write(game.getMap().length + "\n");

						for(char[] line : game.getMap())
						{
							for (char c : line)
							{
								writer.write(c + "\n");
							}
						}

						if (game.getLevel() == 2)
						{
							for(Ogre o : game.getOgre())
							{
								o.saveGame(writer);
							}
						}

						writer.close();

					}
					catch (Exception e1) {
						JOptionPane.showMessageDialog(frmDungeonKeep, "Can't save game state.");
						frmDungeonKeep.requestFocusInWindow();
					}
				}
			}
		});
		frmDungeonKeep.getContentPane().add(mntmSaveGame);

		JButton mntmLoadGame = new JButton("Load");
		mntmLoadGame.setBounds(263, 469, 97, 25);
		mntmLoadGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDungeonKeep.requestFocusInWindow();
				int returnVal = fc.showOpenDialog(frmDungeonKeep);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();


					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						nOgres = Integer.parseInt(reader.readLine());

						if(reader.readLine().equals("true"))
							playing = true;
						else
							playing = false;

						if(reader.readLine().equals("true"))
							custom = true;
						else
							custom = false;

						op.map = new char[Integer.parseInt(reader.readLine())][Integer.parseInt(reader.readLine())];

						for(int i = 0; i < op.map.length; i++)
						{
							for (int j = 0; j < op.map[i].length; j++)
							{
								op.map[i][j] = reader.readLine().charAt(0);
							}
						}

						op.comboBox.setSelectedItem(reader.readLine());
						op.comboBox_1.setSelectedItem(reader.readLine());
						cmbPersonality.setSelectedItem(reader.readLine());

						game = new Game((String) cmbPersonality.getSelectedItem());

						if(Integer.parseInt(reader.readLine()) == 2)
						{
							game.advanceLevel();
						}

						game.setHero(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), reader.readLine().charAt(0), reader.readLine().charAt(0));

						if(cmbPersonality.getSelectedItem().equals("Novice"))
						{
							game.setGuard(new Rookie(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())));
						}
						else if(cmbPersonality.getSelectedItem().equals("Intermediate"))
						{
							int x = Integer.parseInt(reader.readLine());
							int y = Integer.parseInt(reader.readLine());
							boolean direction;

							if(reader.readLine() == "true")
								direction = true;
							else
								direction = false;

							game.setGuard(new Drunken(x, y, direction, Float.parseFloat(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())));

						}
						else
						{
							game.setGuard(new Suspicious(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Boolean.parseBoolean(reader.readLine()), Float.parseFloat(reader.readLine()), Integer.parseInt(reader.readLine())));
						}

						char[][] m = game.getMap();
						m = new char[Integer.parseInt(reader.readLine())][Integer.parseInt(reader.readLine())];

						for(int i = 0; i < m.length; i++)
						{
							for (int j = 0; j < m[i].length; j++)
							{
								m[i][j] = reader.readLine().charAt(0);
							}
						}
						game.setMap(m);

						if (game.getLevel() == 2)
						{
							game.setOgre(new Ogre[nOgres]);
							for(int i = 0; i < game.getOgre().length; i++)
							{
								game.getOgre()[i] = new Ogre(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine()), reader.readLine().charAt(0), Integer.parseInt(reader.readLine()), m);
							}
						}

						reader.close();
						gameScreen.setMap(game.getMap());
						frmDungeonKeep.repaint();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(frmDungeonKeep, "Invalid file format.");
						frmDungeonKeep.requestFocusInWindow();
					}

				}
			}
		});
		frmDungeonKeep.getContentPane().add(mntmLoadGame);

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

















