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
import validator.RunAllValidator;

/**
 * @author wander
 *
 */
public class RunAll implements ActionListener {

	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;

	public RunAll(RepositoryParameters repository, TheoryParameters theory, SearchAlgorithmParameters searchAlgorithm) {
		this.repository = repository;
		this.theory = theory;
		this.searchAlgorithm = searchAlgorithm;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		RunAllValidator validator = new RunAllValidator(repository, theory, searchAlgorithm);
		List<String> messages;
		messages = validator.validate();
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
				this.executeAll();
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	public void executeAll() throws Exception {
		AbstractStepFactoryMethod step = new StepFactoryMethod();
		IStep completeProcess = step.factoryMethod(AbstractStepFactoryMethod.RUN_ALL_KEY);
		completeProcess.updateParameter(IStep.REPOSITORY_PARAMETER_IDENTIFIER, repository);
		completeProcess.updateParameter(IStep.THEORY_PARAMETER_IDENTIFIER, theory);
		completeProcess.updateParameter(IStep.SEARCH_ALGORITHM_PARAMETER_IDENTIFIER, searchAlgorithm);
		completeProcess.execute();
	}
	
}
