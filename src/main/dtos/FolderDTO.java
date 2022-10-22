package main.dtos;

import java.util.ArrayList;
import java.util.List;

public class FolderDTO extends FolderComponentDTO {
	
	private String folderName;
	
	private String absolutePath;
	
	private List<FolderComponentDTO> components = new ArrayList<FolderComponentDTO>();

	@Override
	public String getAbsolutePath() {
		return this.absolutePath;
	}
	
	@Override
	public FolderDTO setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
		return this;
	}
	
	public String getFolderName() {
		return this.folderName;
	}
	
	public FolderDTO setFolderName(String folderName) {
		this.folderName = folderName;
		return this;
	}
	
	@Override
	public List<FolderComponentDTO> getFolderComponents() {
		return this.components;
	}
	
	public FolderDTO setFolderComponents(List<FolderComponentDTO> components) {
		this.components = components;
		return this;
	}
	
	@Override
	public void add(FolderComponentDTO newComponent) 
	{
		this.components.add(newComponent);
	}
	
	@Override
	public void remove(FolderComponentDTO component) 
	{
		this.components.remove(component);
	}
	
	@Override
	public boolean isFolder() {
		return true;
	}
	
	public FolderDTO() {
		
	}
}
