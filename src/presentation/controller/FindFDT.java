/**
 * 
 */
package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import presentation.TheoryScreen;

/**
 * @author wander
 *
 */
public class FindFDT implements ActionListener {

	private TheoryScreen theoryScreen;
	
	public FindFDT(TheoryScreen theoryScreen){
		this.theoryScreen = theoryScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		int opcao;
		if(this.theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory() == null || this.theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory().trim().equals("")){
			chooser.setCurrentDirectory(new File("."));
		}
		else{
			chooser.setCurrentDirectory(new File(this.theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory()));
		}
		chooser.setDialogTitle("Select the FDT file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		opcao = chooser.showOpenDialog(chooser);
		if(opcao == JFileChooser.APPROVE_OPTION){
			String fdtName = chooser.getSelectedFile().getAbsolutePath();
			this.theoryScreen.updateFDTName(fdtName);
			this.theoryScreen.getMainScreen().getTheoryParameters().setFdtFile(fdtName);
		} 				
	}

}
