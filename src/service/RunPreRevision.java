/**
 * 
 */
package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;
import steps.AbstractStepFactoryMethod;
import steps.IStep;
import steps.StepFactoryMethod;
import validator.RunPreRevisionValidator;

/**
 * @author wander
 *
 */
public class RunPreRevision implements ActionListener {
	
	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;
	
	public RunPreRevision(RepositoryParameters repository, TheoryParameters theory, SearchAlgorithmParameters searchAlgorithm) {
		this.repository = repository;
		this.theory = theory;
		this.searchAlgorithm = searchAlgorithm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		RunPreRevisionValidator validator = new RunPreRevisionValidator(repository, theory, searchAlgorithm);
		List<String> messages;
		messages = validator.validate();
		JScrollPane panel = new JScrollPane();
		if (messages.size() > 0) {
			panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			String message = String.format("Error List:%n%n");
			for (String msg:messages) {
				message = message + String.format(msg+"%n");
			}
			JOptionPane.showMessageDialog(panel, message, "Error(s)", JOptionPane.ERROR_MESSAGE);
		}
		else {
			panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
			try {
				this.executePreRevision();
				JOptionPane.showMessageDialog(panel, "Pre-Revision step was sucessfully executed.", "Execution's status", JOptionPane.INFORMATION_MESSAGE);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(panel, "Pre-Revision step wasn't executed.", "Execution's status", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void executePreRevision() throws Exception{
		AbstractStepFactoryMethod step = new StepFactoryMethod();
		IStep preRevisionStep = step.factoryMethod(AbstractStepFactoryMethod.RUN_PRE_REVISION_KEY);
		preRevisionStep.updateParameter(IStep.REPOSITORY_PARAMETER_IDENTIFIER, repository);
		preRevisionStep.updateParameter(IStep.THEORY_PARAMETER_IDENTIFIER, theory);
		preRevisionStep.updateParameter(IStep.SEARCH_ALGORITHM_PARAMETER_IDENTIFIER, searchAlgorithm);
		preRevisionStep.execute();
	}
	
}
