/**
 * 
 */
package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.TheoryScreen;

/**
 * @author wander
 *
 */
public class TheoryExit implements ActionListener {

	private TheoryScreen theoryScreen;
	
	public TheoryExit(TheoryScreen theoryScreen){
		this.theoryScreen = theoryScreen;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		theoryScreen.closeFrame();
	}

}
