package gui;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.OrganizeType;
import main.Utilities;
import javax.swing.JRadioButton;

public class MainFrame 
{

	private JFrame frame;
	
	private JLabel title;
	
	private JButton startAction;
	private JButton sourcePathBrowse;
	private JButton targetPathBrowse;
	
	private JRadioButton yearRadioButton;
	private JRadioButton monthRadioButton;
	private JRadioButton dayRadioButton;
	
	private JLabel sourcePathLabel;
	private JLabel targetPathLabel;
	
	private JTextField sourcePath; 
	private JTextField targetPath;
	
	
	private int srcWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int srcHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		int frameWidth = 581;
		int frameHeight = 387;

		frame = new JFrame();
		frame.setBounds((srcWidth/2) - (frameWidth/2), (srcHeight/2) - (frameHeight/2), frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		sourcePathLabel = new JLabel("Source Path:");
		sourcePathLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		sourcePathLabel.setBounds(26, 145, 91, 27);
		frame.getContentPane().add(sourcePathLabel);
		
		targetPathLabel = new JLabel("Target Path:");
		targetPathLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		targetPathLabel.setBounds(26, 190, 91, 27);
		frame.getContentPane().add(targetPathLabel);
		
		sourcePath = new JTextField();
		sourcePath.setBounds(115, 147, 334, 25);
		frame.getContentPane().add(sourcePath);
		sourcePath.setColumns(10);
		
		targetPath = new JTextField();
		targetPath.setBounds(115, 192, 334, 25);
		frame.getContentPane().add(targetPath);
		targetPath.setColumns(10);
		
		targetPathBrowse = new JButton("Browse");
		targetPathBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser(new File("C:\\"));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("Select Target Path");
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{ 
					targetPath.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
			}
		});
		targetPathBrowse.setBounds(459, 191, 89, 27);
		frame.getContentPane().add(targetPathBrowse);
		
		sourcePathBrowse = new JButton("Browse");
		sourcePathBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				JFileChooser fileChooser = new JFileChooser(new File("C:\\"));
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fileChooser.setDialogTitle("Select Source Path");
				int result = fileChooser.showSaveDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) 
				{ 
					sourcePath.setText(fileChooser.getSelectedFile().getAbsolutePath());
	            }
			}
		});
		sourcePathBrowse.setBounds(459, 146, 89, 27);
		frame.getContentPane().add(sourcePathBrowse);
		
		startAction = new JButton("Start");
		startAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(Utilities.isValidFolderPath(sourcePath.getText()) && Utilities.isValidFolderPath(targetPath.getText()))
				{	
					if(Utilities.hasImage(sourcePath.getText())) 
					{
						try 
						{
							if(Utilities.organize(sourcePath.getText(), targetPath.getText(), getOrgaType())) 
							{
								System.out.println("Valid Paths");	
							}
							else {
								System.out.println("Error");
							}		
						}
						catch(Exception exception)
						{
							//throws exception();
						}
					}
					else 
					{
						System.out.println("No image");
					}
				}
				else if(!Utilities.isValidFolderPath(sourcePath.getText())) 
				{
					System.out.println("Invalid Source Path");
				}
				else
				{
					System.out.println("Invalid Target Path");
				}
				
			}
		});
		startAction.setBounds(227, 273, 114, 41);
		frame.getContentPane().add(startAction);
		
		title = new JLabel("Image Organizer");
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(178, 32, 209, 61);
		frame.getContentPane().add(title);
		
		yearRadioButton = new JRadioButton("Year");
		monthRadioButton = new JRadioButton("Month");
		dayRadioButton = new JRadioButton("Day");

		yearRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(yearRadioButton.isSelected()) {
					monthRadioButton.setSelected(false);
					dayRadioButton.setSelected(false);
				}
			}
		});
		yearRadioButton.setBounds(168, 234, 69, 23);
		frame.getContentPane().add(yearRadioButton);
		
		monthRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(monthRadioButton.isSelected()) {
					yearRadioButton.setSelected(false);
					dayRadioButton.setSelected(false);
				}
			}
		});
		monthRadioButton.setBounds(261, 234, 69, 23);
		frame.getContentPane().add(monthRadioButton);
		
		
		dayRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dayRadioButton.isSelected()) {
					yearRadioButton.setSelected(false);
					monthRadioButton.setSelected(false);
				}
			}
		});
		dayRadioButton.setBounds(367, 234, 69, 23);
		frame.getContentPane().add(dayRadioButton);
		frame.setVisible(true);
	
		dayRadioButton.setSelected(true);
		
	}
	
	private OrganizeType getOrgaType() 
	{
		if(yearRadioButton.isSelected()) 
		{
			return OrganizeType.YEAR;
		}
		else if(monthRadioButton.isSelected()) 
		{
			return OrganizeType.MONTH;
		}
		else if(dayRadioButton.isSelected()) 
		{
			return OrganizeType.DAY;
		}
		else 
		{	
			System.out.println("Invalid orginize type!");
			return OrganizeType.YEAR;
		}
	}
}
