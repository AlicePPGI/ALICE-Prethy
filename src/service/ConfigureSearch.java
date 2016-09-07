package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.MainScreen;
import presentation.SearchScreen;

/**
 * @author wander
 *
 */
public class ConfigureSearch implements ActionListener {

	private MainScreen mainScreen;
	
	public ConfigureSearch(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		SearchScreen ss = new SearchScreen(mainScreen);
	}

}
