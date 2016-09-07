/**
 * 
 */
package presentation.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import presentation.SearchScreen;

/**
 * @author wander
 *
 */
public class ProtectModeChange implements ChangeListener {

	private SearchScreen searchScreen;

	public ProtectModeChange(SearchScreen searchScreen) {
		this.searchScreen = searchScreen;
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		searchScreen.updateProtectMode();
	}

}
