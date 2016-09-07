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
public class TheoryParameters implements IValidate {

	private String     directoryTheory = "";
	private String     dataSetFile = "";
	private String     thyFile = "";
	private String     fdtFile = "";
    private String     logFile = "";	
	private String     logLevel = "";
	private String     seed = "";
	private String     trainingAndRuns = "";
	private String     revisionSystem = "";

	public String getDirectoryTheory() {
		return directoryTheory;
	}

	public void setDirectoryTheory(String directoryTheory) {
		this.directoryTheory = directoryTheory;
	}

	public String getDataSetFile() {
		return dataSetFile;
	}

	public void setDataSetFile(String dataSetFile) {
		this.dataSetFile = dataSetFile;
	}

	public String getThyFile() {
		return thyFile;
	}

	public void setThyFile(String thyFile) {
		this.thyFile = thyFile;
	}

	public String getFdtFile() {
		return fdtFile;
	}

	public void setFdtFile(String fdtFile) {
		this.fdtFile = fdtFile;
	}

	public String getLogFile() {
		return logFile;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getSeed() {
		return seed;
	}

	public void setSeed(String seed) {
		this.seed = seed;
	}

	public String getTrainingAndRuns() {
		return trainingAndRuns;
	}

	public void setTrainingAndRuns(String trainingAndRuns) {
		this.trainingAndRuns = trainingAndRuns;
	}

	public String getRevisionSystem() {
		return revisionSystem;
	}

	public void setRevisionSystem(String revisionSystem) {
		this.revisionSystem = revisionSystem;
	}

	@Override
	public List<String> validate() {
		List<String> messages = this.validateTheoryFiles();
		return messages;
	}
	
	public List<String> validateTheoryFiles() {
		List<String> messages = new ArrayList<String>();
		if (dataSetFile == null || dataSetFile.trim().equals("")) {
			messages.add("The dataset file name was not informed.");
		}
		else {
			File file = new File(dataSetFile);
			if (!file.exists()) {
				messages.add("DataSet file not found.");
			}
		}
		if (thyFile == null || thyFile.trim().equals("")) {
			messages.add("The THY file name was not informed.");
		}
		else {
			File file = new File(thyFile);
			if (!file.exists()) {
				messages.add("THY file not found.");
			}
		}
		if (fdtFile == null || fdtFile.trim().equals("")) {
			messages.add("The FDT file name was not informed.");
		}
		else {
			File file = new File(fdtFile);
			if (!file.exists()) {
				messages.add("FDT file not found.");
			}
		}
		if (logLevel == null || logLevel.trim().equals("")) {
			messages.add("The Log Level was not informed.");
		}
		else {
			try {
				int level = Integer.parseInt(logLevel);
				if (level < 1 || level > 8) {
					messages.add("Invalid log level value. Its value mst be a number from 1 to 8.");
				}
			}
			catch(Exception e) {
				messages.add("Invalid log level value. Its value must be numeric.");
			}
		}
		if (seed == null || seed.trim().equals("")) {
			messages.add("The seed was not informed.");
		}
		if (trainingAndRuns == null || trainingAndRuns.trim().equals("")) {
			messages.add("The training and run information was not informed.");
		}
		if (revisionSystem == null || revisionSystem.trim().equals("")) {
			messages.add("The revision system program was not informed.");
		}
		else {
			File file = new File(revisionSystem);
			if (!file.exists()) {
				messages.add("The revision system program not found.");
			}
		}
		return messages;
	}
	
	public String stripExtension(String fileName) {
		int pos = fileName.lastIndexOf(".");
		if (pos == -1) {
			return fileName;
		}
		else {
			return fileName.substring(0, pos);
		}
	}

}
