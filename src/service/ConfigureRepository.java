/**
 * 
 */
package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.MainScreen;
import presentation.RepositoryScreen;

/**
 * @author wander
 *
 */
public class ConfigureRepository implements ActionListener {

	private MainScreen mainScreen;
	
	public ConfigureRepository(MainScreen mainScreen){
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		RepositoryScreen rs = new RepositoryScreen(mainScreen);
	}

}
