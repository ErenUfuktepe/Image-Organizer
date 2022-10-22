package main.gui;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;

	private String radioButtonLabel;
	
	public String getRadioButtonLabel() {
		return this.radioButtonLabel;
	}
	
	public RadioButton setRadioButtonLabel(String radioButtonLabel) {
		this.radioButtonLabel = radioButtonLabel;
		return this;
	}
	
	public RadioButton(String radioButtonLabel) {
		this.radioButtonLabel = radioButtonLabel;
		this.setText(radioButtonLabel);
	}
}
