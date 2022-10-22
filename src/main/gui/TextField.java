package main.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class TextField extends JTextField {

	private static final long serialVersionUID = 1L;

	private JLabel textFieldLabel;
	
	public JLabel getTextFieldLabel() {
		return this.textFieldLabel;
	}
	
	public TextField setTextFieldLabel(String textFieldLabel) {
		this.textFieldLabel = new JLabel(textFieldLabel);
		this.textFieldLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		return this;
	}
	
	public TextField() {
		
	}
	
}
