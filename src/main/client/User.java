package main.client;

import main.dtos.FolderComponentDTO;

public class User {

	private FolderComponentDTO component;
	
	private String targetPath;
	
	public FolderComponentDTO getComponent() {
		return this.component;
	}
	
	public User setComponent(FolderComponentDTO component) {
		this.component = component;
		return this;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public User setTargetPath(String targetPath) {
		this.targetPath = targetPath;
		return this;
	}
	
	public User() {
		
	}
	
}
