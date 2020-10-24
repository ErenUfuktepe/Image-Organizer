package main;

public class Image extends FolderComponent{

	private String imageName;
	private String createdDate;
	
	public Image(String newImageName, String createdDate) 
	{
		this.imageName = newImageName;
		this.createdDate = createdDate;
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
	
}
