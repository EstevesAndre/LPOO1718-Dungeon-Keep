package DKeep.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class Options extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;
	public JRadioButton rdbtnRandom;
	public char[][] map;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public Options() {
		setTitle("Keep Level Options");
		setBounds(100, 100, 393, 471);
		getContentPane().setLayout(null);

		char[][] m5 = { {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
				{'I', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', ' ', ' ', ' ', ' ', ' ', 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X', 'X'}};

		map = m5;
		Map gameScreen = new Map();

		JLabel lblDimensions = new JLabel("Dimensions:                      by");
		lblDimensions.setBounds(134, 23, 156, 14);
		getContentPane().add(lblDimensions);


		String[] vals = {"5", "6", "7", "8"};
		JComboBox<Object> comboBox = new JComboBox<Object>(vals);
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(vals);
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt((String)comboBox.getSelectedItem()) + 2;
				int y = Integer.parseInt((String)comboBox_1.getSelectedItem()) + 2;
				char[][] m_prov = new char[y][x];
				
				for(int j = 0; j < y; j++)
				{
					for(int i = 0; i < x; i++)
					{
						if(j == 0 || j == (y-1) || i == 0 || i == (x-1))
							m_prov[j][i] = 'X';
						else
							m_prov[j][i] = ' ';
					}
				}
				m_prov[1][0] = 'I';
				map = m_prov;
				gameScreen.setMap(map);
				gameScreen.repaint();
			}
		});
		comboBox.setEnabled(false);
		comboBox.setBounds(224, 19, 44, 20);
		getContentPane().add(comboBox);

		
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = Integer.parseInt((String)comboBox.getSelectedItem()) + 2;
				int y = Integer.parseInt((String)comboBox_1.getSelectedItem()) + 2;
				char[][] m_prov = new char[y][x];
				
				for(int j = 0; j < y; j++)
				{
					for(int i = 0; i < x; i++)
					{
						if(j == 0 || j == (y-1) || i == 0 || i == (x-1))
							m_prov[j][i] = 'X';
						else
							m_prov[j][i] = ' ';
					}
				}
				m_prov[1][0] = 'I';
				map = m_prov;
				gameScreen.setMap(map);
				gameScreen.repaint();
			}
		});
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(288, 19, 44, 20);
		getContentPane().add(comboBox_1);

		JLabel lblAddComponent = new JLabel("Add");
		lblAddComponent.setBounds(135, 47, 156, 14);
		getContentPane().add(lblAddComponent);


		String[] components = {"Hero", "Key", "Ogre", "Wall", "Blank Space"};
		JComboBox<Object> comboBox_2 = new JComboBox<Object>(components);
		comboBox_2.setEnabled(false);
		comboBox_2.setBounds(224, 44, 108, 20);
		getContentPane().add(comboBox_2);

		JLabel lblAtPosition = new JLabel("at position");
		lblAtPosition.setBounds(134, 71, 80, 14);
		getContentPane().add(lblAtPosition);

		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(224, 68, 44, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setColumns(10);
		textField_1.setBounds(288, 68, 44, 20);
		getContentPane().add(textField_1);

		
		gameScreen.setBounds(20, 98, 336, 323);
		getContentPane().add(gameScreen);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = 0, y = 0;
				try {
					x = Integer.parseInt((String)textField.getText());
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(btnAdd, "Invalid format: X position", "Error", 0);
					return;
				}
				try {
					y = Integer.parseInt((String)textField_1.getText());
				}
				catch (Exception ex){
					JOptionPane.showMessageDialog(btnAdd, "Invalid format: Y position", "Error", 0);
					return;
				}

				if (x < 1 || x > Integer.parseInt((String)comboBox.getSelectedItem()))
				{
					JOptionPane.showMessageDialog(btnAdd, "Out of Bounds: X position", "Error", 0);
					return;
				}
				
				if (y < 1 || y > Integer.parseInt((String)comboBox.getSelectedItem()))
				{
					JOptionPane.showMessageDialog(btnAdd, "Out of Bounds: Y position", "Error", 0);
					return;
				}
				
				if(comboBox_2.getSelectedItem() == "Hero")
				{
					map[y][x] = 'A';
				}
				else if(comboBox_2.getSelectedItem() == "Key")
				{
					map[y][x] = 'k';
				}
				else if(comboBox_2.getSelectedItem() == "Ogre")
				{
					map[y][x] = '0';
				}
				else if(comboBox_2.getSelectedItem() == "Wall")
				{
					map[y][x] = 'X';
				}
				else
				{
					map[y][x] = ' ';
				}
				
				gameScreen.setMap(map);
				gameScreen.repaint();
			}
		});
		btnAdd.setBounds(20, 67, 89, 23);
		getContentPane().add(btnAdd);

		JRadioButton rdbtnCustom = new JRadioButton("Custom");
		rdbtnCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(true);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
				btnAdd.setEnabled(true);
				gameScreen.setMap(map);
				gameScreen.repaint();
			}
		});


		rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
				btnAdd.setEnabled(false);

			}
		});

		rdbtnRandom.setSelected(true);
		buttonGroup.add(rdbtnRandom);
		rdbtnRandom.setBounds(20, 19, 109, 23);
		getContentPane().add(rdbtnRandom);
		buttonGroup.add(rdbtnCustom);
		rdbtnCustom.setBounds(20, 41, 109, 23);
		getContentPane().add(rdbtnCustom);

		

	}
}
