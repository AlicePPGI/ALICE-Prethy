/**
 * 
 */
package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import presentation.RepositoryScreen;

/**
 * @author wander
 *
 */
public class FindRepository implements ActionListener {

	private RepositoryScreen repositoryScreen;
	
	public FindRepository(RepositoryScreen repositoryScreen){
		this.repositoryScreen = repositoryScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		int opcao;
		if(repositoryScreen.getMainScreen().getRepositoryParameters().getRepositoryDirectory() == null || repositoryScreen.getMainScreen().getRepositoryParameters().getRepositoryDirectory().trim().equalsIgnoreCase("")){
			chooser.setCurrentDirectory(new File("."));
		}
		else{
			chooser.setCurrentDirectory(new File(repositoryScreen.getMainScreen().getRepositoryParameters().getRepositoryDirectory()));
		}
		chooser.setDialogTitle("Select the Repository file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		opcao = chooser.showOpenDialog(chooser);
		if(opcao == JFileChooser.APPROVE_OPTION){
			String rpName = chooser.getSelectedFile().getAbsolutePath();
			repositoryScreen.updateRPName(rpName);
			repositoryScreen.getMainScreen().getRepositoryParameters().setRepositoryDirectory(chooser.getSelectedFile().getParent());
			repositoryScreen.getMainScreen().getRepositoryParameters().setRepositoryFile(rpName);
		}
	}

}
