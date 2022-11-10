package main.gui;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {

	private static final long serialVersionUID = 1L;

	private String radioButtonLabel;
	
	private String key;
	
	public String getRadioButtonLabel() {
		return this.radioButtonLabel;
	}
	
	public RadioButton setRadioButtonLabel(String radioButtonLabel) {
		this.radioButtonLabel = radioButtonLabel;
		return this;
	}
	
	public String getKey() {
		return this.key;
	}
	
	public RadioButton setKey(String key) {
		this.key = key;
		return this;
	}
	
	public RadioButton(String radioButtonLabel) {
		this.radioButtonLabel = radioButtonLabel;
		this.setText(radioButtonLabel);
	}
}
