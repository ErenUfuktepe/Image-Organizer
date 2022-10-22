package main.types;

public enum ExceptionHandler {

	ORGANIZE_IMAGE_COMPLETE("Organizing images completed."),
	INVALID_SOURCE_PATH("Invalid source path, please enter or select valid path."),
	INVALID_TARGET_PATH("Invalid target path, please enter or select valid path."),
	EMPTY_ORGANIZE_OPTION("Please select organize type.");
	
	public final String message;

    private ExceptionHandler(String message) {
        this.message = message;
    }
    
    public String getExceptionMessage() {
    	return this.message;
    }
}
