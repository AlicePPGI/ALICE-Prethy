/**
 * 
 */
package presentation.controller;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import presentation.TheoryScreen;

/**
 * @author wsantos
 *
 */
public class TRChange implements CaretListener {

	private TheoryScreen theoryScreen;

	public TRChange(TheoryScreen theoryScreen) {
		this.theoryScreen = theoryScreen;
	}

	@Override
	public void caretUpdate(CaretEvent e) {
		this.theoryScreen.updateTR();
	}

}
