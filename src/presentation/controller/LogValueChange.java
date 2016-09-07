/**
 * 
 */
package presentation.controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import presentation.TheoryScreen;

/**
 * @author wander
 *
 */
public class LogValueChange implements ChangeListener {

	private TheoryScreen theoryScreen;
	
	public LogValueChange(TheoryScreen theoryScreen){
		this.theoryScreen = theoryScreen;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		this.theoryScreen.updateLogLevel();
	}

}
