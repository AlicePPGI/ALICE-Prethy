/**
 * 
 */
package preRevision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import parameters.RepositoryParameters;
import parameters.TheoryParameters;
import repository.ODP;
import repository.Repository;

/**
 * @author wsantos
 *
 */
class FileUtil {

	private static final FileUtil instance = new FileUtil();
	
	private FileUtil(){
		
	}
	
	public static final FileUtil getInstance(){
		return instance;
	}
	
	public Repository openRepository(RepositoryParameters repositoryParameters) throws Exception{
		JAXBContext jc = JAXBContext.newInstance(Repository.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File(repositoryParameters.getRepositoryFile());
		Repository repository = (Repository) unmarshaller.unmarshal(xml);
		return repository;
	}

	public BufferedReader openTHY(TheoryParameters theoryParameters) throws Exception {
		return this.openFile(theoryParameters.getThyFile());
	}

	private BufferedReader openFile(String fileName) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		return br;
	}
	
	public void closeTHY(BufferedReader thy) throws Exception {
		if(thy == null){
			return;
		}
		thy.close();
	}

	public List<String> getTheoryRules(BufferedReader thy) throws Exception{
		String line = thy.readLine();
		List<String> theoryRules = new ArrayList<String>();
		while(line != null){
			theoryRules.add(line);
			line = thy.readLine();
		}
		return theoryRules;
	}

	public void saveTHY(TheoryParameters theoryParameters, List<String> theoryRules) throws Exception{
		this.renameFile(theoryParameters.getThyFile(), theoryParameters.stripExtension(theoryParameters.getThyFile())+"_old.thy");
		this.writeFile(theoryParameters.getThyFile(), theoryRules, false);
	}
	
	public void protectODPs(TheoryParameters theoryParameters, List<ODP> odpsFound) throws Exception {
		String backupFileName = theoryParameters.stripExtension(theoryParameters.getFdtFile())+"_old.fdt";
		this.renameFile(theoryParameters.getFdtFile(), backupFileName);
		this.copyFile(backupFileName, theoryParameters.getFdtFile());
		for(ODP odp:odpsFound){
			List<String> rules = Arrays.asList(odp.getRule());
			this.writeFile(theoryParameters.getFdtFile(), rules, true);
		}
	}

	private void renameFile(String oldName, String newName) throws Exception{
		File oldFile = new File(oldName);
		File renamedFile = new File(newName);
		oldFile.renameTo(renamedFile);
	}

	private void copyFile(String sourceFileName, String targetFileName) throws Exception {
		File sourceFile = new File(sourceFileName);
		File targetFile = new File(targetFileName);
		Files.copy(sourceFile.toPath(), targetFile.toPath());
	}

	private void writeFile(String fileName, List<String> rules, boolean append) throws Exception {
		File thyFile = new File(fileName);
		if(!thyFile.exists()){
			thyFile.createNewFile();
		}
		FileWriter fw = new FileWriter(thyFile.getAbsoluteFile(), append);
		BufferedWriter bw = new BufferedWriter(fw);
		for(String rule:rules){
			bw.write(rule+System.lineSeparator());
		}
		bw.close();
		fw.close();
	}
	
}
