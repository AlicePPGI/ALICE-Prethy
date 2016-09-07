/**
 * 
 */
package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import parameters.TheoryParameters;

import steps.AbstractStepFactoryMethod;
import steps.IStep;
import steps.StepFactoryMethod;
import validator.RunRevisionValidator;

/**
 * @author wander
 *
 */
public class RunRevision implements ActionListener {

	private TheoryParameters theory;
	
	public RunRevision(TheoryParameters theory) {
		this.theory = theory;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		RunRevisionValidator validator = new RunRevisionValidator(theory);
		List<String> messages = validator.validate();
		if (messages.size() > 0) {
			JScrollPane panel = new JScrollPane();
			panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			String message = String.format("Error List:%n%n");
			for (String msg:messages) {
				message = message + String.format(msg+"%n");
			}
			JOptionPane.showMessageDialog(panel, message, "Error(s)", JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				this.executeRevision();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		
	}

	public void executeRevision() throws Exception {
		AbstractStepFactoryMethod step = new StepFactoryMethod();
		IStep revisionStep = step.factoryMethod(AbstractStepFactoryMethod.RUN_REVISION_KEY);
		revisionStep.updateParameter(IStep.THEORY_PARAMETER_IDENTIFIER, theory);
		revisionStep.execute();
	}
}
