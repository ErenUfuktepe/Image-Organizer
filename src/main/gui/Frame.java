package main.gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 581;
	
	private static final int FRAME_HEIGHT = 387;
	
	private static final String APPLICATION_NAME = "Image Organizer";
	
	private JLabel frameTitle;
		
	private List<Button> buttons = new ArrayList<>();
	
	private List<RadioButton> radioButtons = new ArrayList<>();
	
	private List<TextField> textFields = new ArrayList<>();
	
	public JLabel getFrameTitle() {
		return this.frameTitle;
	}
	
	public Frame setFrameTitle(String title) {
		this.frameTitle = new JLabel(title);
		this.frameTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		this.frameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		this.frameTitle.setBounds(178, 32, 209, 61);
		this.getContentPane().add(this.frameTitle);
		return this;
	}
	
	public List<Button> getButtons(){
		return this.buttons;
	}
	
	public Frame setButtons(List<Button> buttons) {
		this.buttons = buttons;
		return this;
	}
	
	public Frame addButton(Button button) {
		this.buttons.add(button);
		this.getContentPane().add(button);
		return this;
	}
	
	public Frame removeLabel(Button button) {
		this.buttons.remove(button);
		this.getContentPane().remove(button);
		return this;
	}
	
	public List<RadioButton> getRadioButtons(){
		return this.radioButtons;
	}
	
	public Frame setRadioButtons(List<RadioButton> radioButtons) {
		this.radioButtons = radioButtons;
		return this;
	}
	
	public Frame addRadioButton(RadioButton radioButton) {
		this.radioButtons.add(radioButton);
		this.getContentPane().add(radioButton);
		return this;
	}
	
	public Frame removeRadioButton(RadioButton radioButton) {
		this.radioButtons.remove(radioButton);
		this.getContentPane().remove(radioButton);
		return this;
	}
	
	public List<TextField> getTextFields(){
		return this.textFields;
	}
	
	public Frame setTextFields(List<TextField> textFields) {
		this.textFields = textFields;
		return this;
	}
	
	public Frame addTextField(TextField textField) {
		this.textFields.add(textField);
		if(textField.getTextFieldLabel() != null) {
			this.getContentPane().add(textField.getTextFieldLabel());			
		}
		this.getContentPane().add(textField);
		return this;
	}
	
	public Frame removeTextField(TextField textField) {
		this.textFields.remove(textField);
		if(textField.getTextFieldLabel() != null) {
			this.getContentPane().remove(textField.getTextFieldLabel());			
		}
		this.getContentPane().remove(textField);
		return this;
	}
	
	public String getSelectedRadioButton() {
		for(RadioButton button : this.radioButtons) {
			if(button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	public Frame() {
		this.setTitle(APPLICATION_NAME);
		
		int srcWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int srcHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		this.setBounds((srcWidth/2) - (FRAME_WIDTH/2), (srcHeight/2) - (FRAME_HEIGHT/2), FRAME_WIDTH, FRAME_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
	}
	
	public Frame addSelectOnlyOneRule() {
		for (RadioButton radioButton : this.radioButtons) {
			radioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(radioButton.isSelected()) {
						removeCheckForOthers(radioButton);
					}
				}
			});
		}
		return this;
	}
	
	private void removeCheckForOthers(RadioButton radioButton) {
		this.radioButtons.stream()
						 .filter(button -> !button.equals(radioButton))
						 .forEach(button -> button.setSelected(false));
	}

}
