/**
 * 
 */
package presentation;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.controller.FindRepository;
import presentation.controller.RepositoryExit;

/**
 * @author wander
 *
 */
public class RepositoryScreen extends AbstractContainer {

	private JTextField rpName;
	
	public RepositoryScreen(MainScreen mainScreen){
		super(mainScreen, 700, 120, "Configure ODPs Repository");
	}
	
	@Override
	public void configureContainer(){
		super.container.setLayout(null);
		this.configurePanel();
		super.container.add(super.panel);
	}
	
	@Override
	public void configurePanel(){
		super.panel.setLayout(null);
		super.panel.setBounds(super.container.getInsets().left, super.container.getInsets().top,super.container.getInsets().left+700,super.container.getInsets().top+120);
		this.mountRepositoryFileNamePanel();
		this.mountExitFramePanel();
	}

	public void mountRepositoryFileNamePanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.LEFT));
		super.addLabel("Repository File: ", panel);
		if(super.mainScreen.getRepositoryParameters().getRepositoryFile() != null && !super.mainScreen.getRepositoryParameters().getRepositoryFile().trim().equals("")){
			this.rpName = this.addTextField(58, Color.white, false, super.mainScreen.getRepositoryParameters().getRepositoryFile(), panel);
		} else {
			this.rpName = this.addTextField(58, Color.white, false, null, panel);			
		}
		super.addButton("Find Repository File", "Starts searching the repository file.", new FindRepository(this), panel);
		super.addPanel(panel, 10, panel.getPreferredSize().width, panel.getPreferredSize().height);
	}

	@Override
	public void mountExitFramePanel(){
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.CENTER));
		super.addButton("Close Window", "Return to Main Window.", new RepositoryExit(this), panel);
		super.addPanel(panel, 50, 700, panel.getPreferredSize().height);
	}

	public void updateRPName(String rpName){
		this.rpName.setText(rpName);
	}
	
}
