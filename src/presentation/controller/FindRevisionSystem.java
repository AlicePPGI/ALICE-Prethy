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
public class FindRevisionSystem implements ActionListener {

	private TheoryScreen theoryScreen;
	
	public FindRevisionSystem(TheoryScreen theoryScreen) {
		this.theoryScreen = theoryScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser chooser = new JFileChooser();
		int opcao;
		if (theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory() == null || theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory().trim().equals("")) {
			chooser.setCurrentDirectory(new File("."));
		}
		else{
			chooser.setCurrentDirectory(new File(theoryScreen.getMainScreen().getTheoryParameters().getDirectoryTheory()));	
		}
		chooser.setDialogTitle("Select Revision System Program");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
		opcao = chooser.showOpenDialog(chooser);
		if (opcao == JFileChooser.APPROVE_OPTION) {
			String rsName = chooser.getSelectedFile().getAbsolutePath();
			theoryScreen.updateRSName(rsName);
			theoryScreen.getMainScreen().getTheoryParameters().setRevisionSystem(rsName);
		}
	}

}
