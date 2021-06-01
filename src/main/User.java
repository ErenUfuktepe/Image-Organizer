package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {

	private FolderComponent userFolder;
	private OrganizeType type;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	private String folderCheck = "19000101"; //Default Value
	private FolderComponent newFolder = null;
	
	public User(String sourcePath, OrganizeType type) throws IOException 
	{		
		this.type = type;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		this.userFolder = new Folder(dtf.format(now).toString(), null);
		
		folderStructure(sourcePath);
	}
	
	
	private void folderStructure(String sourcePath) throws IOException 
	{	
		File folder = new File(sourcePath);
		File[] listOfFiles = mergeSort(folder.listFiles());
		
		for(File file : listOfFiles) 
		{
			String fileName = file.toString().toLowerCase();
			if(!file.isDirectory()) 
			{
				if(Utilities.fileTypes.contains(fileName.substring(fileName.lastIndexOf(".") + 1))) 
				{
					BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
					FileTime time = attrs.creationTime();
					//FileTime time = attrs.lastModifiedTime();
				    String formatted = this.dateFormat.format( new Date( time.toMillis() ) );
				    FolderComponent image = new Image(file.getName(),getOrganizeTypeFormat(formatted, this.type), file);
				
				    // The image 
			    	if(Integer.parseInt(this.folderCheck) != Integer.parseInt(getOrganizeTypeFormat(formatted, this.type))) 
			    	{
			    		if(this.newFolder != null) {
			    			this.userFolder.add(this.newFolder);	
			    		}
			    		this.newFolder = new Folder(getOrganizeTypeFormat(formatted, this.type), file);
			    		this.folderCheck = getOrganizeTypeFormat(formatted, this.type);
			    	}
			    	
			    	this.newFolder.add(image);
			    	
			    	if(listOfFiles[listOfFiles.length -1].equals(file)) 
			    	{
			    		if (!isExistingComponent(newFolder.getName()))
	    				{
			    			this.userFolder.add(newFolder);
	    				}
			    	}
				}
			}
			else 
			{
				folderStructure(file.getAbsolutePath());
			}
		}
	}
	
	
	private boolean isExistingComponent(String folderName) 
	{
		for(FolderComponent component : this.userFolder.getFolderComponent()) 
		{
			if(component.getName().equals(folderName)) 
			{
				return true;
			}
		}
		return false;
	}
	
	public FolderComponent getUserFolder() 
	{
		return this.userFolder;
	}
	
	private String getOrganizeTypeFormat(String date, OrganizeType type) 
	{
		if(type.equals(OrganizeType.YEAR)) 
		{
			return date.substring(0,4);
		}
		else if(type.equals(OrganizeType.MONTH)) 
		{
			return date.substring(0,6);
		}
		else if(type.equals(OrganizeType.DAY)) 
		{
			return date.substring(0,8);
		}
		else 
		{
			System.out.println("Invalid type!");
			return date.substring(0,3);
		}
	}
	
	public void display() 
	{
		for(FolderComponent component : this.userFolder.getFolderComponent()) {
			System.out.println("Folder:	" + component.getName());
			for(FolderComponent image : component.getFolderComponent()) {
				System.out.println("Image:	" +  image.getName());
			}
		}
	}
	
    private static File[] mergeSort(File[] list) throws IOException 
    {
        if (list.length <= 1) 
        {
            return list;
        }
         
        File[] first = new File[list.length / 2];
        File[] second = new File[list.length - first.length];
        System.arraycopy(list, 0, first, 0, first.length);
        System.arraycopy(list, first.length, second, 0, second.length);
         
        mergeSort(first);
        mergeSort(second);
         
        merge(first, second, list);
        return list;
    }
     
    private static void merge(File[] first, File[] second, File[] result) throws IOException 
    {
    	String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	    
    	int iFirst = 0;  
        int iSecond = 0; 
        int iMerged = 0;
         
        while (iFirst < first.length && iSecond < second.length) 
        {
        	FileTime time1 = Files.readAttributes(first[iFirst].toPath(), BasicFileAttributes.class).creationTime();
    		String format1 = simpleDateFormat.format( new Date( time1.toMillis() ) );
    		
    		FileTime time2 = Files.readAttributes(second[iSecond].toPath(), BasicFileAttributes.class).creationTime();
    		String format2 = simpleDateFormat.format( new Date( time2.toMillis() ) );
    	    
        	
        	if (Integer.parseInt(format1) < Integer.parseInt(format2)) 
            {
                result[iMerged] = first[iFirst];
                iFirst++;
            } 
            else
            {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }
	
}
