package main;

import java.io.File;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Folder extends FolderComponent {

	private List<FolderComponent> folderComponent = new ArrayList<FolderComponent>();
	private String folderName;
	private File folder;
	
	public Folder(String newFolderName, File folder) 
	{
		this.folder = folder;
		//Year
		if(newFolderName.length() == 4) 
		{
			this.folderName = newFolderName;
		}
		//Month
		else if(newFolderName.length() == 6) 
		{
			this.folderName = Month.of(Integer.parseInt(newFolderName.substring(4,6)))
					.getDisplayName(TextStyle.FULL_STANDALONE ,Locale.ENGLISH) 
					+ "-" + newFolderName.substring(0,4);
		}
		//Day
		else {
			this.folderName = newFolderName.substring(6,8) 
					+ "-" + Month.of(Integer.parseInt(newFolderName.substring(4,6)))
						.getDisplayName(TextStyle.FULL_STANDALONE ,Locale.ENGLISH) 
					+ "-" + newFolderName.substring(0,4);
		}
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

	@Override
	public File getClassInfo() {
		return this.folder;
	}
}
