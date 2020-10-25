package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {

	
	public Utilities() 
	{
		
	}
	
	public static boolean isValidFolderPath(String newPath) 
	{
		
		Path path = new File(newPath).toPath();
		
		if(Files.isDirectory(path)) 
		{
			return true;
		} 
		return false;
	}
	
	public static boolean hasImage(String newPath) 
	{
		File folder = new File(newPath);
		File[] listOfFiles = folder.listFiles();
		for(File file : listOfFiles) {
			String fileName = file.toString().toLowerCase();
	        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) 
	        {
	        	if(fileName.substring(fileName.lastIndexOf(".")+1).equals("jpeg") || fileName.substring(fileName.lastIndexOf(".")+1).equals("jpg") 
	        			|| fileName.substring(fileName.lastIndexOf(".")+1).equals("png") || fileName.substring(fileName.lastIndexOf(".")+1).equals("mp4")
	        			|| fileName.substring(fileName.lastIndexOf(".")+1).equals("mov") || fileName.substring(fileName.lastIndexOf(".")+1).equals("wmv")
	        			|| fileName.substring(fileName.lastIndexOf(".")+1).equals("flv") || fileName.substring(fileName.lastIndexOf(".")+1).equals("avi")) 
	        	{
	        		return true;
	        	}
	        }
	        
		}
		return false;
	}
	
	public static boolean organize(String sourcePath, String targetPath, OrganizeType type) throws IOException 
	{
		User user = new User(sourcePath, type);
		FolderComponent newFolderComponent = user.getUserFolder();
		
		String userPath = targetPath + "\\"+ newFolderComponent.getName();
		File newTarget = new File(userPath);

		if(!newTarget.mkdir()) 
		{
			System.out.println("Error create folder");
			return false;
		}
		
		String destination = null;
		for(FolderComponent component : newFolderComponent.getFolderComponent()) 
		{
			if(component.isFolder()) {
				destination = userPath + "\\" + component.getName();
				File newFolder = new File(destination);
				if(!newFolder.mkdir()) 
				{
					System.out.println("Error create folder");
					return false;
				}
			}
			for(FolderComponent image : component.getFolderComponent()) {
				copyFile(new File(sourcePath + "\\" + image.getName()), new File(destination + "\\" + image.getName()));	
			}
		}
		return true;
	}
	
	
	private static void copyFile(File source, File dest) throws IOException {
	    InputStream inputStream = null;
	    OutputStream outputStream = null;
	    try {
	    	inputStream = new FileInputStream(source);
	    	outputStream = new FileOutputStream(dest);

	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = inputStream.read(buffer)) > 0) {
	        	outputStream.write(buffer, 0, length);
	        }
	    } finally {
	    	inputStream.close();
	    	outputStream.close();
	    }
	}
	
	
	
}
