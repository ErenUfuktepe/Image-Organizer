package main.gui;

import javax.swing.JOptionPane;

import main.types.ExceptionHandler;

public class DialogBox {

	private final String ERROR = "Error";

	private final String WARNING = "Warning";
	
	private final String INFORMATION = "Information";

	
	public DialogBox() {
		
	}

	public DialogBox showErrorMessage(ExceptionHandler exception) {
		JOptionPane.showMessageDialog(null, exception.getExceptionMessage(), this.ERROR, JOptionPane.ERROR_MESSAGE);
		return this;
	}
	
	public DialogBox showWarningMessage(ExceptionHandler exception) {
		JOptionPane.showMessageDialog(null, exception.getExceptionMessage(), this.WARNING, JOptionPane.WARNING_MESSAGE);
		return this;
	}
	
	public DialogBox showInformationMessage(ExceptionHandler exception) {
		JOptionPane.showMessageDialog(null, exception.getExceptionMessage(), this.INFORMATION, JOptionPane.INFORMATION_MESSAGE);
		return this;
	}
}
