package presentation;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

import presentation.controller.ProtectModeChange;
import presentation.controller.SearchAlgorithmChange;
import presentation.controller.SearchExit;

/**
 * @author wander
 *
 */
public class SearchScreen extends AbstractContainer {

	public final static String EXACT_SEARCH_ALGORITHM = "Exact Exhaustive Search      ";
	public final static String APPROXIMATE_SEARCH_ALGORITHM = "Approximate Exhaustive Search";
	public final static String AUTOMATIC_PROTECT_MODE = "Automatic";
	public final static String SEMIAUTOMATIC_PROTECT_MODE = "Semiautomatic";
	
	private JSpinner   searchAlgorithm;
	private String     algorithmValue;
	private JSpinner   protectMode;
	private String     protectModeValue;
	
	public SearchScreen(MainScreen mainScreen) {
		super(mainScreen, 700, 170, "Configure Search Algorithm");
	}

	@Override
	public void configureContainer() {
		container.setLayout(null);
		this.configurePanel();
		container.add(panel);
	}
	
	@Override
	public void configurePanel() {
		super.panel.setLayout(null);
		super.panel.setOpaque(false);
		super.panel.setBounds(super.container.getInsets().left, super.container.getInsets().top, super.container.getInsets().left+700, super.container.getInsets().top+350);
		this.mountAlgorithmTypePanel();
		this.mountProtectModePanel();
		this.mountExitFramePanel();
	}
	
	public void mountAlgorithmTypePanel() {
		this.algorithmValue = EXACT_SEARCH_ALGORITHM;
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		String[] algorithms = new String[2];
		algorithms[0] = EXACT_SEARCH_ALGORITHM;
		algorithms[1] = APPROXIMATE_SEARCH_ALGORITHM;
		super.addLabel("Select the Algorithm Search: ", panel);
		SpinnerListModel algorithmTypeList = new SpinnerListModel(algorithms);
		if(super.mainScreen.getSearchAlgorithmParameters().getAlgorithmType() == null || super.mainScreen.getSearchAlgorithmParameters().getAlgorithmType().trim().equals("")) {
			algorithmTypeList.setValue(this.algorithmValue);
		}
		else {
			algorithmTypeList.setValue(super.mainScreen.getSearchAlgorithmParameters().getAlgorithmType());
		}
		this.searchAlgorithm = new JSpinner(algorithmTypeList);
		Dimension dimension = new Dimension(this.searchAlgorithm.getPreferredSize().width+5, this.searchAlgorithm.getPreferredSize().height);
		this.searchAlgorithm.getEditor().setPreferredSize(dimension);
		JComponent comp = this.searchAlgorithm.getEditor();
		JFormattedTextField algorithmField = (JFormattedTextField) comp.getComponent(0);
		algorithmField.setEnabled(false);
		this.searchAlgorithm.addChangeListener(new SearchAlgorithmChange(this));
		panel.add(this.searchAlgorithm);
		this.updateSearchAlgorithm();
		super.addPanel(panel, 10, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountProtectModePanel() {
		this.protectModeValue = AUTOMATIC_PROTECT_MODE;
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		String[] modes = new String[2];
		modes[0] = AUTOMATIC_PROTECT_MODE;
		modes[1] = SEMIAUTOMATIC_PROTECT_MODE;
		super.addLabel("Select the Protection Mode of the Pre-Revision Step: ", panel);
		SpinnerListModel protectTypeList = new SpinnerListModel(modes);
		if(super.mainScreen.getSearchAlgorithmParameters().getPreRevisionType() == null || super.mainScreen.getSearchAlgorithmParameters().getPreRevisionType().trim().equals("")) {
			protectTypeList.setValue(this.protectModeValue);
		} else {
			protectTypeList.setValue(super.mainScreen.getSearchAlgorithmParameters().getPreRevisionType());
		}
		this.protectMode = new JSpinner(protectTypeList);
		Dimension dimension = new Dimension(this.protectMode.getPreferredSize().width+3, this.protectMode.getPreferredSize().height);
		this.protectMode.getEditor().setPreferredSize(dimension);
		JComponent comp = this.protectMode.getEditor();
		JFormattedTextField protectModeField = (JFormattedTextField) comp.getComponent(0);
		protectModeField.setEnabled(false);
		this.protectMode.addChangeListener(new ProtectModeChange(this));
		panel.add(this.protectMode);
		this.updateProtectMode();
		super.addPanel(panel, 50, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}

	@Override
	public void mountExitFramePanel() {
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.CENTER));
		super.addButton("Close Window", "Return to Main Menu", new SearchExit(this), panel);
		super.addPanel(panel, 100, 700, panel.getPreferredSize().height);
	}

	public void updateSearchAlgorithm() {
		this.algorithmValue = this.searchAlgorithm.getValue().toString();
		super.mainScreen.getSearchAlgorithmParameters().setAlgorithmType(this.algorithmValue);
	}

	public void updateProtectMode() {
		this.protectModeValue = this.protectMode.getValue().toString();
		super.mainScreen.getSearchAlgorithmParameters().setPreRevisionType(this.protectModeValue);
	}
	
}
