/**
 * 
 */
package presentation;

import java.awt.Color;
import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import presentation.util.GradientPanel;

/**
 * @author wander
 *
 */
public abstract class AbstractContainer {

	MainScreen mainScreen;
	JFrame frame = new JFrame();
	Container container = frame.getContentPane();
	JPanel panel = new GradientPanel(Color.GRAY, Color.LIGHT_GRAY, GradientPanel.DIAGONAL_LEFT);

	public AbstractContainer() {
		
	}
	
	public AbstractContainer(MainScreen mainScreen, int width, int height, String title) {
		this.mainScreen = mainScreen;
		frame.setSize(width, height);
		frame.setTitle(title);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setResizable(false);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getSize().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getSize().height)/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.configureContainer();
		frame.setVisible(true);
	}

	public AbstractContainer(int width, int height, String title) {
		frame.setSize(width, height);
		frame.setTitle(title);
		frame.setExtendedState(JFrame.NORMAL);
		frame.setResizable(false);
		frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - frame.getSize().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - frame.getSize().height)/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.configureContainer();
		frame.setVisible(true);
	}

	public abstract void configureContainer();
	public abstract void configurePanel();
	public abstract void mountExitFramePanel();
	
	public void closeFrame() {
		frame.dispose();
	}
	
	public MainScreen getMainScreen() {
		return mainScreen;
	}
	
	public void setMainScreen(MainScreen mainScreen) {
		this.mainScreen = mainScreen;
	}

	void addLabel(String label, JPanel panel) {
		if (label == null || panel == null || label.trim().equals("")) {
			return;
		}
		JLabel lbl = new JLabel(label);
		panel.add(lbl);
	}
	
	JTextField addTextField(Integer columns, Color color, Boolean editable, String text, JPanel panel) {
		if (panel == null) {
			return null;
		}
		JTextField textField = new JTextField();
		if (columns != null) {
			textField.setColumns(columns);
		}
		if (color != null) {
			textField.setBackground(color);
		}
		if (editable != null) {
			textField.setEditable(editable);
		}
		if (text != null) {
			textField.setText(text);
		}
		panel.add(textField);
		return textField;
	}

	void addButton(String label, String tipText, ActionListener action, JPanel panel) {
		if (label == null || action == null || panel == null || label.trim().equals("")) {
			return;
		}
		JButton button = new JButton(label);
		if (tipText != null && !tipText.trim().equals("")) {
			button.setToolTipText(tipText);
		}
		button.addActionListener(action);
		panel.add(button);
	}

	void addPanel(JPanel panel, Integer topDistance, Integer width, Integer height) {
		if (panel == null || width == null || height == null) {
			return;
		}
		panel.setBounds(this.panel.getInsets().left, this.panel.getInsets().top+topDistance, this.panel.getInsets().left+width, this.panel.getInsets().top+height);
		this.panel.add(panel);		
	}

	JPanel addPanel(Boolean opaque, LayoutManager layout) {
		JPanel panel = new JPanel();
		panel.setOpaque(opaque);
		panel.setLayout(layout);
		return panel;
	}

}
