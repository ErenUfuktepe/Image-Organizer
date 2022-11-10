package main;

import main.gui.Button;
import main.gui.Frame;
import main.gui.RadioButton;
import main.gui.TextField;

public class Main {

	public static void main(String[] args) {

		Frame frame = new Frame();
		frame.setFrameTitle("Image Organizer");
		
		
		TextField sourceTextField = new TextField();
		sourceTextField.setTextFieldLabel("Source Path");
		sourceTextField.setBounds(115, 100, 334, 25);
		sourceTextField.getTextFieldLabel().setBounds(26, 100, 91, 27);
		sourceTextField.setColumns(10);
		

		TextField targetTextField = new TextField();
		targetTextField.setTextFieldLabel("Target Path");
		targetTextField.setBounds(115, 150, 334, 25);
		targetTextField.getTextFieldLabel().setBounds(26, 150, 91, 27);
		targetTextField.setColumns(10);


		Button sourceBrowse = new Button().createBrowseButton("Browse", sourceTextField);
		sourceBrowse.setBounds(459, 100, 89, 27);
		
		
		Button targetBrowse = new Button().createBrowseButton("Browse", targetTextField);
		targetBrowse.setBounds(459, 150, 89, 27);

		
		RadioButton dayRadioButton = new RadioButton("Day");
		dayRadioButton.setBounds(367, 200, 69, 23);
		dayRadioButton.setKey("Organize Option");

		RadioButton monthRadioButton = new RadioButton("Month");
		monthRadioButton.setBounds(261, 200, 69, 23);
		monthRadioButton.setKey("Organize Option");

		RadioButton yearRadioButton = new RadioButton("Year");
		yearRadioButton.setBounds(168, 200, 69, 23);
		yearRadioButton.setKey("Organize Option");

		RadioButton copyRadioButton = new RadioButton("Copy");
		copyRadioButton.setBounds(168, 230, 69, 23);
		copyRadioButton.setKey("Action Option");

		RadioButton moveRadioButton = new RadioButton("Move");
		moveRadioButton.setBounds(261, 230, 69, 23);
		moveRadioButton.setKey("Action Option");

		Button startButton = new Button();
		startButton.setText("Start");
		startButton.setBounds(227, 273, 114, 41);
		startButton.getFrameData(frame);
		
		frame.addTextField(sourceTextField)
			 .addTextField(targetTextField)
			 .addButton(sourceBrowse)
			 .addButton(targetBrowse)
			 .addButton(startButton)
			 .addRadioButton(yearRadioButton)
			 .addRadioButton(monthRadioButton)
			 .addRadioButton(dayRadioButton)
			 .addRadioButton(copyRadioButton)
			 .addRadioButton(moveRadioButton);
		
		frame.addSelectOnlyOneRule();
		
		frame.setVisible(true);
	}

}
