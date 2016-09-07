/**
 * 
 */
package presentation;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.DefaultFormatter;

import presentation.controller.FindDataSet;
import presentation.controller.FindFDT;
import presentation.controller.FindRevisionSystem;
import presentation.controller.FindTHY;
import presentation.controller.LogValueChange;
import presentation.controller.SeedChange;
import presentation.controller.TRChange;
import presentation.controller.TheoryExit;


/**
 * @author wander
 *
 */
public class TheoryScreen extends AbstractContainer {

	public final static String CRITICAL_ERRORS_LOG_DESCRIPTION = "Critical errors messages, test parameters, summary of runs for each training set size.";
	public final static String RESULTS_LOG_DESCRIPTION = "Results of each test run, including revised theory.";
	public final static String INITIAL_TRAINING_LOG_DESCRIPTION = "Initial training and test set accuracies, each individual revision made to the theory.";
	public final static String POTENTIAL_REVISION_POINTS_LOG_DESCRIPTION = "Potential of all revision points, best revision found for each revision point.";
	public final static String REVISIONS_PROPOSED_LOG_DESCRIPTION = "Revisions proposed by each operator.";
	public final static String INTERNAL_WORK_LOG_DESCRIPTION = "Internal work done by each operator as it tries to construct a revision.";
	public final static String MORE_INFO_LOG_DESCRIPTION = "More information on search done by he operators, meta-interpreter errors (e.g., looping).";
	public final static String EVEN_MORE_INFO_LOG_DESCRIPTION = "Even more information on the antecedents considered by add-antecedent.";
	public final static String INVALID_LEVEL_LOG_DESCRIPTION = "Invalid level selected";

	private JTextField   rsName;
	private JTextField   dsName;
	private JTextField   thyName;
	private JTextField   fdtName;
	private JSpinner     logValue;
	private String       logLevel;
	private JTextField   levelDescription;
	private JTextField   seed;
	private String       seedValue;
	private JTextField   tr;
	private String       trValue;
	
	public TheoryScreen(MainScreen mainScreen) {
		super(mainScreen, 800, 450, "Configure Logical Theory");
	}
	
	@Override
	public void configureContainer(){
		super.container.setLayout(null);
		this.configurePanel();
		super.container.add(super.panel);
	}

	@Override
	public void configurePanel(){
		super.panel.setOpaque(false);
		super.panel.setLayout(null);
		super.panel.setBounds(super.container.getInsets().left, super.container.getInsets().top, super.container.getInsets().left+800, super.container.getInsets().top+500);
		this.mountRevisionSystemPanel();
		this.mountDataSetPanel();
		this.mountTHYPanel();
		this.mountFDTPanel();
		this.mountLogLevelPanel();
		this.mountSeedPanel();
		this.mountTrainingAndRunsPanel();
		this.mountExitFramePanel();
	}
	
	public void mountRevisionSystemPanel() {
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("Revision System Program: ", panel);
		if (super.mainScreen.getTheoryParameters().getRevisionSystem() != null && !super.mainScreen.getTheoryParameters().getRevisionSystem().trim().equals("")) {
			this.rsName = super.addTextField(60, Color.white, false, super.mainScreen.getTheoryParameters().getRevisionSystem(), panel);
		} else {
			this.rsName = super.addTextField(60, Color.white, false, null, panel);
		}
		super.addButton("Find Revision System", "Starts searching the revision system program.", new FindRevisionSystem(this), panel);
		super.addPanel(panel, 0, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountDataSetPanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("DataSet File: ", panel);
		if (super.mainScreen.getTheoryParameters().getDataSetFile() != null && !super.mainScreen.getTheoryParameters().getDataSetFile().trim().equals("")) {
			this.dsName = super.addTextField(70, Color.white, false, super.mainScreen.getTheoryParameters().getDataSetFile(), panel);
		} else {
			this.dsName = super.addTextField(70, Color.white, false, null, panel);			
		}
		super.addButton("Find DataSet File", "Starts searching the data set file.", new FindDataSet(this), panel);
		super.addPanel(panel, 50, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountTHYPanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("THY File:       ", panel);
		if(super.mainScreen.getTheoryParameters().getThyFile() != null && !super.mainScreen.getTheoryParameters().getThyFile().trim().equals("")){
			this.thyName = super.addTextField(70, Color.white, false, super.mainScreen.getTheoryParameters().getThyFile(), panel);
		}else{
			this.thyName = super.addTextField(70, Color.white, false, null, panel);			
		}
		super.addButton("Find THY File", "Starts searching the thy file.", new FindTHY(this), panel);
		super.addPanel(panel, 100, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountFDTPanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("FDT File:       ", panel);
		if(super.mainScreen.getTheoryParameters().getFdtFile() != null && !super.mainScreen.getTheoryParameters().getFdtFile().trim().equals("")){
			this.fdtName = super.addTextField(70, Color.white, false, super.mainScreen.getTheoryParameters().getFdtFile(), panel);
		}else{
			this.fdtName = super.addTextField(70, Color.white, false, null, panel);
		}
		super.addButton("Find FDT File", "Starts searching the fdt file.", new FindFDT(this), panel);
		super.addPanel(panel, 150, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountLogLevelPanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("Select Log Level Information: ", panel);
		this.logLevel = "1";
		SpinnerNumberModel modelo;
		if(super.mainScreen.getTheoryParameters().getLogLevel() == null || super.mainScreen.getTheoryParameters().getLogLevel().trim().equals("")){
			modelo = new SpinnerNumberModel(Integer.parseInt(this.logLevel), 1, 8, 1);
		}
		else{
			modelo = new SpinnerNumberModel(Integer.parseInt(super.mainScreen.getTheoryParameters().getLogLevel()), 1, 8, 1);
		}
		this.logValue = new JSpinner(modelo);
		JComponent comp = this.logValue.getEditor();
		JFormattedTextField logField = (JFormattedTextField) comp.getComponent(0);
		logField.setEnabled(false);
		DefaultFormatter formatter = (DefaultFormatter) logField.getFormatter();
		formatter.setCommitsOnValidEdit(true);
		this.logValue.addChangeListener(new LogValueChange(this));
		panel.add(this.logValue);
		this.levelDescription = super.addTextField(60, Color.white, false, null, panel);
		this.updateLogLevel();
		super.addPanel(panel, 200, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountSeedPanel() {
		this.seedValue = "random";
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("Seed: ", panel);
		if (super.mainScreen.getTheoryParameters().getSeed() != null && !super.mainScreen.getTheoryParameters().getSeed().trim().equals("")) {
			this.seed = super.addTextField(10, Color.white, Boolean.TRUE, super.mainScreen.getTheoryParameters().getSeed(), panel);
		}
		else {
			this.seed = super.addTextField(10, Color.white, Boolean.TRUE, this.seedValue, panel);
			super.mainScreen.getTheoryParameters().setSeed(this.seedValue);
		}
		this.seed.addCaretListener(new SeedChange(this));
		super.addPanel(panel, 250, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	public void mountTrainingAndRunsPanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("Entry Size of Training Set and Runs: ", panel);
		if(super.mainScreen.getTheoryParameters().getTrainingAndRuns() != null && !super.mainScreen.getTheoryParameters().getTrainingAndRuns().trim().equals("")){
			this.tr = super.addTextField(30, Color.white, Boolean.TRUE, super.mainScreen.getTheoryParameters().getTrainingAndRuns(), panel);
		}
		else {
			this.tr = super.addTextField(30, Color.white, Boolean.TRUE, this.trValue, panel);			
		}
		this.tr.addCaretListener(new TRChange(this));
		super.addPanel(panel, 300, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}
	
	@Override
	public void mountExitFramePanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.CENTER));
		super.addButton("Close Window", "Return to Main Window.", new TheoryExit(this), panel);
		super.addPanel(panel, 350, 700, panel.getPreferredSize().height);
	}

	public void updateDSName(String dsNameText){
		this.dsName.setText(dsNameText);
	}

	public void updateTHYName(String thyNameText){
		this.thyName.setText(thyNameText);
	}
	
	public void updateFDTName(String fdtNameText){
		this.fdtName.setText(fdtNameText);
	}
	
	public void updateRSName(String rsNameText){
		this.rsName.setText(rsNameText);
	}
	
	public void updateLogLevel(){
		this.logLevel = this.logValue.getValue().toString();
		this.getMainScreen().getTheoryParameters().setLogLevel(this.logLevel);
		switch (Integer.parseInt(this.logLevel)){
		case 1:
			this.levelDescription.setText(CRITICAL_ERRORS_LOG_DESCRIPTION);
			break;
		case 2:
			this.levelDescription.setText(RESULTS_LOG_DESCRIPTION);
			break;
		case 3:
			this.levelDescription.setText(INITIAL_TRAINING_LOG_DESCRIPTION);
			break;
		case 4:
			this.levelDescription.setText(POTENTIAL_REVISION_POINTS_LOG_DESCRIPTION);
			break;
		case 5:
			this.levelDescription.setText(REVISIONS_PROPOSED_LOG_DESCRIPTION);
			break;
		case 6:
			this.levelDescription.setText(INTERNAL_WORK_LOG_DESCRIPTION);
			break;
		case 7:
			this.levelDescription.setText(MORE_INFO_LOG_DESCRIPTION);
			break;
		case 8:
			this.levelDescription.setText(EVEN_MORE_INFO_LOG_DESCRIPTION);
			break;
		default:
			this.levelDescription.setText(INVALID_LEVEL_LOG_DESCRIPTION);
		}
	}
	
	public void updateSeed() {
		this.seedValue = this.seed.getText();
		super.mainScreen.getTheoryParameters().setSeed(this.seedValue);
	}

	public void updateTR() {
		this.trValue = this.tr.getText();
		super.mainScreen.getTheoryParameters().setTrainingAndRuns(this.trValue);
	}
}
