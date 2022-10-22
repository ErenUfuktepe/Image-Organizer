package main.dtos;

import main.types.ExceptionHandler;
import main.types.OrganizeOption;
import main.utilities.FileUtilities;

public class FrameDTO {
	
	private String sourcePath;
	
	private String targetPath;
	
	private OrganizeOption organizeBy;
	
	public String getSourcePath() {
		return this.sourcePath;
	}
	
	public FrameDTO setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
		return this;
	}
	
	public String getTargetPath() {
		return this.targetPath;
	}
	
	public FrameDTO setTargetPath(String targetPath) {
		this.targetPath = targetPath;
		return this;
	}
	
	public OrganizeOption getOrganizeBy() {
		return this.organizeBy;
	}
	
	public FrameDTO setOrganizeBy(String organizeBy) {
		if(organizeBy == "Year") {
			this.organizeBy = OrganizeOption.YEAR;
		}
		else if(organizeBy == "Month") {
			this.organizeBy = OrganizeOption.MONTH;

		}
		else {
			this.organizeBy = OrganizeOption.DAY;
		}
		return this;
	}
	
	public FrameDTO() {
		
	}
	
	public boolean isValid() {
		if(this.organizeBy == null) {
			return false;
		}
		if(!FileUtilities.isValidDirectory(this.sourcePath) 
				|| !FileUtilities.isValidDirectory(this.targetPath)) {
			return false;
		}
		return true;
	}
	
	public ExceptionHandler getExceptionMessage() {
		if(this.organizeBy == null) {
			return ExceptionHandler.EMPTY_ORGANIZE_OPTION;
		}
		if(!FileUtilities.isValidDirectory(this.sourcePath)) {
			return ExceptionHandler.INVALID_SOURCE_PATH;
		}
		if(!FileUtilities.isValidDirectory(this.targetPath)) {
			return ExceptionHandler.INVALID_TARGET_PATH;
		}
		return null;
	}

}
