package main;

import java.util.ArrayList;
import java.util.List;

public class Folder extends FolderComponent {

	private List<FolderComponent> folderComponent = new ArrayList<FolderComponent>();
	private String folderName;
	
	public Folder(String newFolderName) 
	{
		this.folderName = newFolderName;
	}

	@Override
	public String getName() 
	{
		return this.folderName;
	}
	
	@Override
	public List<FolderComponent> getFolderComponent()
	{
		return this.folderComponent;
	}
	
	@Override
	public void add(FolderComponent newComponent) 
	{
		this.folderComponent.add(newComponent);
	}
	
	@Override
	public void remove(FolderComponent component) 
	{
		this.folderComponent.remove(component);
	}
	
	
	@Override
	public boolean isFolder() {
		return true;
	}
	
}
