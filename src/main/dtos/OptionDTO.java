package main.dtos;

import main.types.OrganizeOption;


public class OptionDTO {

	private FolderComponentDTO component;
	
	private OrganizeOption organizeOption;
	
	public FolderComponentDTO getComponent() {
		return this.component;
	}
	
	public OptionDTO setComponent(FolderComponentDTO component) {
		this.component = component;
		return this;
	}
	
	public OrganizeOption getOption() {
		return this.organizeOption;
	}
	
	public OptionDTO setOption(OrganizeOption organizeOption) {
		this.organizeOption = organizeOption;
		return this;
	}
	
	public OptionDTO() {
		
	}
}
