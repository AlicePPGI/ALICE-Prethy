/**
 * 
 */
package presentation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentation.controller.SelectionExit;
import repository.ODP;

/**
 * @author wsantos
 *
 */
public class SelectionScreen extends AbstractContainer{

	List<ODP> odpsFound;

	public SelectionScreen(List<ODP> odpsFound){
		this.odpsFound = odpsFound;
		super.frame.setSize(800, 450);
		super.frame.setTitle("Selection of Patterns Found");
		super.frame.setExtendedState(JFrame.NORMAL);
		super.frame.setResizable(false);
		super.frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - super.frame.getSize().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - super.frame.getSize().height)/2);
		super.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.configureContainer();
		super.frame.setVisible(true);
	}

	@Override
	public void configureContainer() {
		super.container.setLayout(null);
		this.configurePanel();
		super.container.add(super.panel);
	}

	@Override
	public void configurePanel() {
		super.panel.setOpaque(false);
		super.panel.setLayout(null);
		super.panel.setBounds(super.container.getInsets().left, super.container.getInsets().top, super.container.getInsets().left+800, super.container.getInsets().top+500);
		this.mountTable();
		this.mountExitFramePanel();
	}

	public void mountTable(){
		JPanel panel = super.addPanel(Boolean.TRUE, new GridLayout(1,1));
		JTable table = new JTable(new ODPTable(this.odpsFound));
		JScrollPane scroll = new JScrollPane(table);
		panel.add(scroll);
		super.addPanel(panel, 0, 800, 300);
		super.panel.add(scroll);
	}

	@Override
	public void mountExitFramePanel() {
		JPanel panel = super.addPanel(Boolean.FALSE, new FlowLayout(FlowLayout.CENTER));
		super.addButton("Close Window", "Return to Main Menu", new SelectionExit(this), panel);
		super.addPanel(panel, 350, 800, panel.getPreferredSize().height);
	}

}
