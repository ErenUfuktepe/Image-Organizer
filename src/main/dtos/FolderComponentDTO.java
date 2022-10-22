package main.dtos;

import java.util.List;

public abstract class FolderComponentDTO {

	public List<FolderComponentDTO> getFolderComponents() {
		throw new UnsupportedOperationException();
	}
	
	public String getAbsolutePath() {
		throw new UnsupportedOperationException();
	}
	
	public FolderComponentDTO setAbsolutePath(String path) {
		throw new UnsupportedOperationException();
	}
	
	public void  add(FolderComponentDTO newFolderComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void  remove(FolderComponentDTO newFolderComponent) {
		throw new UnsupportedOperationException();
	}

	public boolean isFolder() {
		throw new UnsupportedOperationException();
	}

}
