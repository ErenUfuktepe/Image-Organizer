package main.types;

public enum ImageFileFormat {
	JPEG("jpeg"),
	JPG("jpg"),
	PNG("png"),
	HEIC("heic");
	
	public final String type;

    private ImageFileFormat(String type) {
        this.type = type;
    }
    
    public String getType() {
    	return this.type;
    }
}
