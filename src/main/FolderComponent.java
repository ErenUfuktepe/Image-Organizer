package main;

import java.util.List;

public abstract class FolderComponent {

	public void  add(FolderComponent newFolderComponent) {
		throw new UnsupportedOperationException();
	}
	
	public void  remove(FolderComponent newFolderComponent) {
		throw new UnsupportedOperationException();
	}
	
	public String getName() {
		throw new UnsupportedOperationException();
	}
	
	public String getCreatedDate() {
		throw new UnsupportedOperationException();
	}
	
	public List<FolderComponent> getFolderComponent(){
		throw new UnsupportedOperationException();
	}
	
	public boolean isFolder() {
		throw new UnsupportedOperationException();
	}
	
	public void display() {
		throw new UnsupportedOperationException();
	}
	
}
