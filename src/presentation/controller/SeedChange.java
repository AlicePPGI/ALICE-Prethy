/**
 * 
 */
package presentation.controller;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import presentation.TheoryScreen;

/**
 * @author wander
 *
 */
public class SeedChange implements CaretListener {

	private TheoryScreen theoryScreen;
	
	public SeedChange(TheoryScreen theoryScreen) {
		this.theoryScreen = theoryScreen;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		this.theoryScreen.updateSeed();
	}	

}
