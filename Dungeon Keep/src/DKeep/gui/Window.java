package DKeep.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DKeep.logic.Game;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Window {

	private JFrame frmDungeonKeep;
	private JTextField fldOgre;
	private Game game;
	private int nOgres;
	
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
		frmDungeonKeep = new JFrame();
		frmDungeonKeep.setResizable(false);
		frmDungeonKeep.setTitle("Dungeon Keep");
		frmDungeonKeep.setBounds(100, 100, 763, 548);
		frmDungeonKeep.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDungeonKeep.getContentPane().setLayout(null);
		
		
		JLabel lblMessage = new JLabel("Start a new game");
		lblMessage.setBounds(44, 473, 116, 16);
		frmDungeonKeep.getContentPane().add(lblMessage);
		
		JLabel lblNumberOfOgres = new JLabel("Number of Ogres");
		lblNumberOfOgres.setBounds(44, 40, 116, 28);
		frmDungeonKeep.getContentPane().add(lblNumberOfOgres);
		
		fldOgre = new JTextField();
		fldOgre.setBounds(183, 43, 59, 22);
		frmDungeonKeep.getContentPane().add(fldOgre);
		fldOgre.setColumns(10);
		
		JLabel lblGuardPersonality = new JLabel("Guard personality");
		lblGuardPersonality.setBounds(44, 81, 116, 16);
		frmDungeonKeep.getContentPane().add(lblGuardPersonality);
		
		String[] difficultLevels = {"Novice", "Intermediate", "Advanced"};
		
		JComboBox cmbPersonality = new JComboBox(difficultLevels);
		cmbPersonality.setBounds(182, 78, 100, 22);
		frmDungeonKeep.getContentPane().add(cmbPersonality);
		
		JTextArea gameScreen = new JTextArea();
		gameScreen.setFont(new Font("Courier New", Font.PLAIN, 27));
		gameScreen.setEditable(false);
		gameScreen.setBounds(45, 124, 505, 323);
		frmDungeonKeep.getContentPane().add(gameScreen);
		
		JButton btnUp = new JButton("Up");
		JButton btnLeft = new JButton("Left");
		JButton btnDown = new JButton("Down");
		JButton btnRight = new JButton("Right");
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.heroMove('w');
				int status = game.evalStatus();
				
				if(status == 2)
				{
					lblMessage.setText("GAME OVER!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}
				else if(status == 1)
				{
					if(game.getLevel() == 1)
					{
						game.advanceLevel(nOgres);
					}
					else
					{
						lblMessage.setText("VICTORY!");
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnLeft.setEnabled(false);
						btnRight.setEnabled(false);
					}					
				}
				gameScreen.setText(printMap(game.getMap()));
				frmDungeonKeep.repaint();
			}
		});
		btnUp.setEnabled(false);
		btnUp.setBounds(622, 238, 67, 25);
		frmDungeonKeep.getContentPane().add(btnUp);
		
		
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.heroMove('a');
				int status = game.evalStatus();
				
				if(status == 2)
				{
					lblMessage.setText("GAME OVER!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}
				else if(status == 1)
				{
					if(game.getLevel() == 1)
					{
						game.advanceLevel(nOgres);
					}
					else
					{
						lblMessage.setText("VICTORY!");
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnLeft.setEnabled(false);
						btnRight.setEnabled(false);
					}					
				}
				gameScreen.setText(printMap(game.getMap()));
				frmDungeonKeep.repaint();
			}
		});
		btnLeft.setEnabled(false);
		btnLeft.setBounds(579, 276, 67, 25);
		frmDungeonKeep.getContentPane().add(btnLeft);
		
		
		btnDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.heroMove('s');
				int status = game.evalStatus();
				
				if(status == 2)
				{
					lblMessage.setText("GAME OVER!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}
				else if(status == 1)
				{
					if(game.getLevel() == 1)
					{
						game.advanceLevel(nOgres);
					}
					else
					{
						lblMessage.setText("VICTORY!");
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnLeft.setEnabled(false);
						btnRight.setEnabled(false);
					}					
				}
				gameScreen.setText(printMap(game.getMap()));
				frmDungeonKeep.repaint();
			}
		});
		btnDown.setEnabled(false);		
		btnDown.setBounds(622, 314, 67, 25);
		frmDungeonKeep.getContentPane().add(btnDown);
		
		
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.heroMove('d');
				int status = game.evalStatus();
				
				if(status == 2)
				{
					lblMessage.setText("GAME OVER!");
					btnUp.setEnabled(false);
					btnDown.setEnabled(false);
					btnLeft.setEnabled(false);
					btnRight.setEnabled(false);
				}
				else if(status == 1)
				{
					if(game.getLevel() == 1)
					{
						game.advanceLevel(nOgres);
					}
					else
					{
						lblMessage.setText("VICTORY!");
						btnUp.setEnabled(false);
						btnDown.setEnabled(false);
						btnLeft.setEnabled(false);
						btnRight.setEnabled(false);
					}					
				}
				gameScreen.setText(printMap(game.getMap()));
				frmDungeonKeep.repaint();
			}
		});
		btnRight.setEnabled(false);
		btnRight.setBounds(664, 276, 67, 25);
		frmDungeonKeep.getContentPane().add(btnRight);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnExit.setBounds(607, 422, 97, 25);
		frmDungeonKeep.getContentPane().add(btnExit);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {
					nOgres = Integer.parseInt(fldOgre.getText());
				}
				catch (NumberFormatException ex){
					JOptionPane.showMessageDialog(frmDungeonKeep, "Invalid format: Number of Ogres!");
					return;
				}
				
				if(nOgres < 1 || nOgres > 4) {
					JOptionPane.showMessageDialog(frmDungeonKeep, "Out of interval: Number of Ogres! (1 to 4)");
					return;
				}
				
				game = new Game((String) cmbPersonality.getSelectedItem());
				
				btnUp.setEnabled(true);
				btnDown.setEnabled(true);
				btnLeft.setEnabled(true);
				btnRight.setEnabled(true);
				
				gameScreen.setText(printMap(game.getMap()));
				frmDungeonKeep.repaint();
			}
		});
		btnNewGame.setBounds(607, 123, 97, 25);
		frmDungeonKeep.getContentPane().add(btnNewGame);
		
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

















