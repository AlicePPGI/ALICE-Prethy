package presentation.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentation.SearchScreen;

public class SearchExit implements ActionListener {

	private SearchScreen searchScreen;
	
	public SearchExit(SearchScreen searchScreen) {
		this.searchScreen =searchScreen;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.searchScreen.closeFrame();
	}
	
}
