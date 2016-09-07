/**
 * 
 */
package parameters;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import validator.IValidate;

/**
 * @author wander
 *
 */
public class RepositoryParameters implements IValidate{

	private String repositoryDirectory = "";
	private String repositoryFile = "";
	
	public String getRepositoryDirectory() {
		return repositoryDirectory;
	}
	
	public void setRepositoryDirectory(String repositoryDirectory) {
		this.repositoryDirectory = repositoryDirectory;
	}
	
	public String getRepositoryFile() {
		return repositoryFile;
	}
	
	public void setRepositoryFile(String repositoryFile) {
		this.repositoryFile = repositoryFile;
	}

	@Override
	public List<String> validate() {
		List<String> messages = new ArrayList<String>();
		if (repositoryFile == null || repositoryFile.trim().equals("")) {
			messages.add("The repository file name was not informed.");
		}
		else {
			File file = new File(repositoryFile);
			if (!file.exists()) {
				messages.add("Repository not found.");
			}
		}
		return messages;
	}
}
