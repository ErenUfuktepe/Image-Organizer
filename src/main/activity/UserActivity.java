package main.activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import main.actions.FileAction;
import main.client.User;
import main.dtos.FileDTO;
import main.dtos.FolderComponentDTO;
import main.dtos.FrameDTO;
import main.types.FileActionStatus;
import main.types.FileActionType;
import main.utilities.FileUtilities;

public class UserActivity {

	private static final int THREADS = 10;
	
    private static final int MAX_THREAD = 5;  
    
    private static final ExecutorService POOL = Executors.newFixedThreadPool(MAX_THREAD);   

	private User user;

	private List<FileAction> fileActionList = new ArrayList<>();

	private FileActionType fileActionType;
	
	public UserActivity() {
		setUpWorkers(THREADS);
	}
	
	public User getUser() {
		return this.user;
	}
	
	public UserActivity setUser(User user) {
		this.user = user;
		return this;
	}
	

	public List<FileAction> getFileActionList() {
		return fileActionList;
	}

	public UserActivity setFileActionList(List<FileAction> fileActionList) {
		this.fileActionList = fileActionList;
		return this;
	}
	
	public FileActionType getFileActionType() {
		return fileActionType;
	}

	public UserActivity setFileActionType(FileActionType fileActionType) {
		this.fileActionType = fileActionType;
		return this;
	}
	
	private List<FileAction> setUpWorkers(int numberOfWorker){
		for(int index = 0; index < numberOfWorker; index++) {
			FileAction fileAction = new FileAction(null, null, index +1);
			this.fileActionList.add(fileAction);
		}
		return this.fileActionList;
	}
	
	public void startActivity(FrameDTO frameDTO) throws IOException {
		this.user = new User();
		this.user.setComponent(FileUtilities.toFolderComponentDTO(frameDTO.getSourcePath()));
		this.user.setTargetPath(frameDTO.getTargetPath());
		this.fileActionType = frameDTO.getFileActionType();
		
		for(FolderComponentDTO folderComponentDTO : this.user.getComponent().getFolderComponents()) {
			if(folderComponentDTO.isFolder()) {
				innerActivity(folderComponentDTO);
			}
			else {
				String absoluteTargetPath = FileUtilities.getAbsolutePathForTargetPath((FileDTO) folderComponentDTO, this.user.getTargetPath());
				FileAction worker = getFileActionWithReadyStatus();
				worker.setFile((FileDTO) folderComponentDTO);
				worker.setTargetPath(absoluteTargetPath);
				worker.setType(this.fileActionType);
				worker.setStatus(FileActionStatus.EXECUTING);
				POOL.execute(worker);
			}
		}
		POOL.shutdown();
	}

	private void innerActivity(FolderComponentDTO folderComponentDTO) {
		for(FolderComponentDTO component : folderComponentDTO.getFolderComponents()) {
			if(component.isFolder()) {
				innerActivity(component);
			}
			else {
				String absoluteTargetPath = FileUtilities.getAbsolutePathForTargetPath((FileDTO) component, this.user.getTargetPath());
				FileAction worker = getFileActionWithReadyStatus();
				worker.setFile((FileDTO) component);
				worker.setTargetPath(absoluteTargetPath);
				worker.setType(this.fileActionType);
				worker.setStatus(FileActionStatus.EXECUTING);
				POOL.execute(worker);
			}
		}
	}
	
	private FileAction getFileActionWithReadyStatus() {
		while(true) {
			Optional<FileAction> worker = fileActionList.stream()
					.filter(fileAction -> fileAction.getStatus().equals(FileActionStatus.READY))
					.findAny();
			if(worker.isPresent()) {
				return worker.get();
			}
		}
	}
	
}
