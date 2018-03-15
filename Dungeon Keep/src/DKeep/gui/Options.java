package DKeep.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Options extends JDialog {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;

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
		
		
		JLabel lblDimensions = new JLabel("Dimensions:                           by");
		lblDimensions.setBounds(134, 23, 156, 14);
		getContentPane().add(lblDimensions);
		
		
		String[] vals = {"5", "6", "7", "8", "9", "10"};
		JComboBox<Object> comboBox = new JComboBox<Object>(vals);
		comboBox.setEnabled(false);
		comboBox.setBounds(224, 19, 44, 20);
		getContentPane().add(comboBox);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>(vals);
		comboBox_1.setEnabled(false);
		comboBox_1.setBounds(288, 19, 44, 20);
		getContentPane().add(comboBox_1);
		
		JLabel lblAddComponent = new JLabel("Add Component");
		lblAddComponent.setBounds(135, 47, 156, 14);
		getContentPane().add(lblAddComponent);
		
		
		String[] components = {"Hero", "Key", "Ogre", "Wall"};
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
		
		Map gameScreen = new Map();
		gameScreen.setBounds(20, 98, 336, 323);
		getContentPane().add(gameScreen);
		
		JRadioButton rdbtnCustom = new JRadioButton("Custom");
		rdbtnCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(true);
				comboBox_1.setEnabled(true);
				comboBox_2.setEnabled(true);
				textField.setEnabled(true);
				textField_1.setEnabled(true);
			}
		});
		
		
		JRadioButton rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				comboBox_1.setEnabled(false);
				comboBox_2.setEnabled(false);
				textField.setEnabled(false);
				textField_1.setEnabled(false);
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
