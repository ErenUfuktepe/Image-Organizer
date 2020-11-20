package gui;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LoadingDialog 
{

	private JFrame frame;
	private ImageIcon loading = new ImageIcon("img\\ajax-loader.gif");

	
	private int srcWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int srcHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();


	public LoadingDialog() {

	}

	public void initialize() 
	{
		int frameWidth = 500;
		int frameHeight = 200;
		
		frame = new JFrame("Please wait...");
		frame.add(new JLabel("Organizing... ", loading, JLabel.CENTER));
		frame.setBounds((srcWidth/2) - (frameWidth/2), (srcHeight/2) - (frameHeight/2), frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	public void kill() 
	{
		frame.dispose();
	}
}



