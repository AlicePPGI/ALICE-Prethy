/**
 * 
 */
package validator;

import java.util.ArrayList;
import java.util.List;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;


/**
 * @author wander
 *
 */
public class RunPreRevisionValidator implements IValidate {

	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;

	public RunPreRevisionValidator(RepositoryParameters repository, TheoryParameters theory, SearchAlgorithmParameters searchAlgorithm) {
		this.repository = repository;
		this.theory = theory;
		this.searchAlgorithm = searchAlgorithm;
	}
	
	@Override
	public List<String> validate() {
		List<String> messages = new ArrayList<String>();
		messages.addAll(this.theory.validateTheoryFiles());
		messages.addAll(this.repository.validate());
		messages.addAll(this.searchAlgorithm.validate());
		return messages;
	}

}
