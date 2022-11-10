package main.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import main.dtos.FileDTO;
import main.types.FileActionStatus;
import main.types.FileActionType;


public class FileAction implements Runnable{

	private int id;
	
	private FileDTO file;
	
	private FileActionType type;
	
	private String targetPath;
	
	private FileActionStatus status;
	
	public FileAction(FileDTO fileDto, FileActionType fileActionType, int id) {
		this.id = id;
		this.file = fileDto;
		this.type = fileActionType;
		this.status = FileActionStatus.READY;
	}
	
	@Override
	public void run() {
		try {
			this.status = FileActionStatus.EXECUTING;
			if(type.equals(FileActionType.COPY)) {
				System.out.println("Thread " + this.id + " Coping: " + this.file.getAbsolutePath());
				copyFile(new File(this.file.getAbsolutePath()), new File(this.targetPath));
			}
			else {
				System.out.println("Thread " + this.id + " Moving: " + this.file.getAbsolutePath());
				Files.move(Paths.get(this.file.getAbsolutePath()), 
						Paths.get(this.targetPath), 
						StandardCopyOption.ATOMIC_MOVE);
			}
			
			this.status = FileActionStatus.READY;
		} catch (FileAlreadyExistsException e) {
			System.out.println("File Already Exists : Thread " + this.id + " Moving: " + this.file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}


	public FileDTO getFile() {
		return file;
	}


	public FileAction setFile(FileDTO file) {
		this.file = file;
		return this;
	}


	public FileActionType getType() {
		return type;
	}


	public FileAction setType(FileActionType type) {
		this.type = type;
		return this;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public FileAction setTargetPath(String targetPath) {
		this.targetPath = targetPath;
		return this;
	}
	

	public FileActionStatus getStatus() {
		return status;
	}

	public FileAction setStatus(FileActionStatus status) {
		this.status = status;
		return this;
	}
	
	public int getId() {
		return id;
	}

	public FileAction setId(int id) {
		this.id = id;
		return this;
	}

	private void copyFile(File source, File dest) throws IOException {
	    InputStream inputStream = null;
	    OutputStream outputStream = null;
	    try {
	    	inputStream = new FileInputStream(source);
	    	outputStream = new FileOutputStream(dest);

	    	int copy;
    	 
            while ((copy = inputStream.read()) != -1) {
            	outputStream.write(copy);
            }
    	
	    } finally {
	    	if (inputStream != null) {
	    		inputStream.close();
            }
            if (outputStream != null) {
            	outputStream.close();
            }
	    }
	}

}
