package main.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import main.activity.UserActivity;
import main.dtos.FrameDTO;
import main.types.ExceptionHandler;

public class Button extends JButton {


	private static final long serialVersionUID = 1L;
	
	private final DialogBox diaglogBox = new DialogBox();
	
	public Button() {
		
	}
	
	public Button createBrowseButton(String buttonText, TextField textField) {
		this.setText(buttonText);
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser(new File("C:\\"));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("Select Target Path");
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{ 
					textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
			}
		});
		return this;
	}
	
	public Button getFrameData(Frame frame) {
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				TextField sourcePath = frame.getTextFields().stream()
					.filter(textField -> textField.getTextFieldLabel().getText() == "Source Path")
					.findAny().get();
				TextField targetPath = frame.getTextFields().stream()
						.filter(textField -> textField.getTextFieldLabel().getText() == "Target Path")
						.findAny().get();
				FrameDTO frameDto = new FrameDTO()
						.setOrganizeBy(frame.getSelectedRadioButton())
						.setSourcePath(sourcePath.getText())
						.setTargetPath(targetPath.getText());
				
				if(frameDto.isValid()) {
					UserActivity activity = new UserActivity();
					try {
						activity.startActivity(frameDto);
						diaglogBox.showInformationMessage(ExceptionHandler.ORGANIZE_IMAGE_COMPLETE);
						System.exit(0);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					diaglogBox.showWarningMessage(frameDto.getExceptionMessage());
				}
			}
		});
		return this;
	}
	
}
