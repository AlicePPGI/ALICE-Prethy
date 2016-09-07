/**
 * 
 */
package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.MainScreen;
import presentation.TheoryScreen;

/**
 * @author wander
 *
 */
public class ConfigureTheory implements ActionListener {

	private MainScreen mainScreen;
	
	public ConfigureTheory(MainScreen mainScreen){
		this.mainScreen = mainScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TheoryScreen ts = new TheoryScreen(mainScreen);
	}

}
