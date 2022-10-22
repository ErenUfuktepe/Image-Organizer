package main.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import main.dtos.FileDTO;
import main.dtos.FolderComponentDTO;
import main.dtos.FolderDTO;
import main.types.ImageFileFormat;

public class FileUtilities {
	
	private final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	public FileUtilities() {
		
	}

	public static boolean isValidDirectory(String directory) 
	{
		if(directory.isBlank() || directory.isEmpty()) {
			return false;
		}
		
		Path path = new File(directory).toPath();
		
		if(Files.isDirectory(path)) {
			return true;
		}
		return false;
	}
	
	public static FolderComponentDTO toFolderComponentDTO(String sourcePath) throws IOException {
		File folder = new File(sourcePath);
		FolderComponentDTO folderComponentDTO = toFolderDTO(folder);

		for(File file : folder.listFiles()) {
			if(!file.isFile()) {
				folderComponentDTOConverter(file.getAbsolutePath(), folderComponentDTO);
			}
			else {
				if(toFileDTO(file) != null) {
					folderComponentDTO.add(toFileDTO(file));
				}
			}
		}
		return folderComponentDTO;
	}
	
	private static void folderComponentDTOConverter(String sourcePath, FolderComponentDTO folderComponentDTO) throws IOException {
		File folder = new File(sourcePath);
		
		FolderComponentDTO newFolderComponentDTO = toFolderDTO(folder);
		folderComponentDTO.add(newFolderComponentDTO);

		for(File file : folder.listFiles()) {
			if(!file.isFile()) {
				folderComponentDTOConverter(file.getAbsolutePath(), newFolderComponentDTO);
			}
			else {
				if(toFileDTO(file) != null) {
					newFolderComponentDTO.add(toFileDTO(file));
				}
			}
		}
	}
	
	private static FileDTO toFileDTO(File file) throws IOException {
		BasicFileAttributes fileAttribute = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
	    String creationDate = dateFormat.format( new Date( fileAttribute.creationTime().toMillis() ) );
	    String fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
		
	    if(isValidImageFile(fileType.toLowerCase())) {
	    	FileDTO fileDto = new FileDTO()
					.setAbsolutePath(file.getAbsolutePath())
			   	    .setFileName(file.getName())
			   	    .setFileType(fileType)
				    .setCreationDate(creationDate);
	    	return fileDto;
	    }
	    return null;
		
	}
	
	private static FolderDTO toFolderDTO(File file) {
		FolderDTO folderDto = new FolderDTO();
		folderDto.setAbsolutePath(file.getAbsolutePath());
		return folderDto;
	}
	
	private static boolean isValidImageFile(String fileType) {
		if(fileType.equals(ImageFileFormat.PNG.getType()) ||
				fileType.equals(ImageFileFormat.JPEG.getType()) ||
				fileType.equals(ImageFileFormat.JPG.getType()) ||
				fileType.equals(ImageFileFormat.HEIC.getType())) {
			return true;
		}
		return false;
	}
	
	public static String getAbsolutePathForTargetPath(FileDTO file, String targetPath) {
		String creationDate = file.getCreationDate();
		File newTargetPath = new File(targetPath);

		// Year
		if(creationDate.length() == 4) 
		{
			newTargetPath = new File(targetPath + "\\" + creationDate);
		}
		// Month
		else if(creationDate.length() == 6) 
		{
			creationDate = Month.of(Integer.parseInt(creationDate.substring(4,6)))
					.getDisplayName(TextStyle.FULL_STANDALONE ,Locale.ENGLISH) 
					+ "-" + creationDate.substring(0,4);
			newTargetPath = new File(targetPath + "\\" + creationDate);
		}
		// Day
		else {
			creationDate = creationDate.substring(6,8) 
					+ "-" + Month.of(Integer.parseInt(creationDate.substring(4,6)))
						.getDisplayName(TextStyle.FULL_STANDALONE ,Locale.ENGLISH) 
					+ "-" + creationDate.substring(0,4);
			newTargetPath = new File(targetPath + "\\" + creationDate);
		}

		if(newTargetPath.mkdir() || newTargetPath.isDirectory()) {
			return newTargetPath.getAbsolutePath() + "\\" + file.getFileName();
		}
		return targetPath + "\\" + file.getFileName();
	}

}
