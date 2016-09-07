/**
 * 
 */
package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.SelectionScreen;

/**
 * @author wsantos
 *
 */
public class SelectionExit implements ActionListener {

	private SelectionScreen selectionScreen;

	public SelectionExit(SelectionScreen selectionScreen){
		this.selectionScreen = selectionScreen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.selectionScreen.closeFrame();
	}

}
