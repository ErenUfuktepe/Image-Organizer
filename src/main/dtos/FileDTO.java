package main.dtos;

public class FileDTO extends FolderComponentDTO {

	private String fileName;
	
	private String fileType;
	
	private String absolutePath;
	
	private String creationDate;
	
	public String getFileName() {
		return this.fileName;
	}
	
	public FileDTO setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	
	public String getFileType() {
		return this.fileType;
	}
	
	public FileDTO setFileType(String fileType) {
		this.fileType = fileType;
		return this;
	}
	
	@Override
	public String getAbsolutePath() {
		return this.absolutePath;
	}
	
	@Override
	public FileDTO setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
		return this;
	}
	
	public String getCreationDate() {
		return this.creationDate;
	}
	
	public FileDTO setCreationDate(String creationDate) {
		this.creationDate = creationDate;
		return this;
	}
	
	@Override
	public boolean isFolder() {
		return false;
	}
	
	public FileDTO() {

	}
}
