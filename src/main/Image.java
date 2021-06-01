package main;

import java.io.File;

public class Image extends FolderComponent{

	private String imageName;
	private String createdDate;
	private File file;
	
	public Image(String newImageName, String createdDate, File file) 
	{
		this.imageName = newImageName;
		this.createdDate = createdDate;
		this.file = file;
	}
	
	@Override
	public String getName() 
	{
		return this.imageName;
	}
	
	@Override
	public String getCreatedDate() 
	{
		return this.createdDate;
	}
	
	@Override
	public boolean isFolder() {
		return false;
	}
	
	@Override
	public File getClassInfo() {
		return this.file;
	}
	
}
