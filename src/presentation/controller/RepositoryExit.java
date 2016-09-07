/**
 * 
 */
package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.RepositoryScreen;

/**
 * @author wander
 *
 */
public class RepositoryExit implements ActionListener {

	private RepositoryScreen repositoryScreen;
	
	public RepositoryExit(RepositoryScreen repositoryScreen){
		this.repositoryScreen = repositoryScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repositoryScreen.closeFrame();
	}

}
