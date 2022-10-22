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
		sourceTextField.setBounds(115, 147, 334, 25);
		sourceTextField.getTextFieldLabel().setBounds(26, 145, 91, 27);
		sourceTextField.setColumns(10);
		

		TextField targetTextField = new TextField();
		targetTextField.setTextFieldLabel("Target Path");
		targetTextField.setBounds(115, 192, 334, 25);
		targetTextField.getTextFieldLabel().setBounds(26, 190, 91, 27);
		targetTextField.setColumns(10);

		
		Button targetBrowse = new Button().createBrowseButton("Browse", targetTextField);
		targetBrowse.setBounds(459, 191, 89, 27);

		
		Button sourceBrowse = new Button().createBrowseButton("Browse", sourceTextField);
		sourceBrowse.setBounds(459, 146, 89, 27);
		
		RadioButton dayRadioButton = new RadioButton("Day");
		dayRadioButton.setBounds(367, 234, 69, 23);

		RadioButton monthRadioButton = new RadioButton("Month");
		monthRadioButton.setBounds(261, 234, 69, 23);
		
		RadioButton yearRadioButton = new RadioButton("Year");
		yearRadioButton.setBounds(168, 234, 69, 23);

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
			 .addRadioButton(dayRadioButton);
		
		frame.addSelectOnlyOneRule();
		
		frame.setVisible(true);
	}

}
