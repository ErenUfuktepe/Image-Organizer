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
	
	public User(String sourcePath, OrganizeType type) throws IOException 
	{		
		String pattern = "yyyyMMdd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		File folder = new File(sourcePath);
		File[] listOfFiles = mergeSort(folder.listFiles());
		
		String check = "";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime now = LocalDateTime.now();
		userFolder = new Folder(dtf.format(now).toString());
		
		FolderComponent newFolder = null;
		
		for(File file : listOfFiles) 
		{
			
			BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime time = attrs.creationTime();
			//FileTime time = attrs.lastModifiedTime();
		    String formatted = simpleDateFormat.format( new Date( time.toMillis() ) );
		    FolderComponent image = new Image(file.getName(),getOrganizeTypeFormat(formatted, type));
		    
		    if(check.equals("")) 
		    {
		    	newFolder = new Folder(getOrganizeTypeFormat(formatted, type));
		    	check = getOrganizeTypeFormat(formatted, type);
		    	newFolder.add(image);
		    }
		    else 
		    {
		    	if(Integer.parseInt(check) != Integer.parseInt(getOrganizeTypeFormat(formatted, type))) 
		    	{
		    		this.userFolder.add(newFolder);
		    		newFolder = new Folder(getOrganizeTypeFormat(formatted, type));
		    		check = getOrganizeTypeFormat(formatted, type);
		    	}
		    	if(listOfFiles[listOfFiles.length -1].equals(file)) 
		    	{
		    		newFolder.add(image);
		    		this.userFolder.add(newFolder);
		    		break;
		    	}
		    	newFolder.add(image);
		    }
		}
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
