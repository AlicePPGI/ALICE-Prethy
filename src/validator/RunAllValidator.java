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
public class RunAllValidator implements IValidate {

	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;

	public RunAllValidator(RepositoryParameters repository, TheoryParameters theory, SearchAlgorithmParameters searchAlgorithm) {
		this.repository = repository;
		this.theory = theory;
		this.searchAlgorithm = searchAlgorithm;
	}
	
	@Override
	public List<String> validate() {
		List<String> messages = new ArrayList<String>();
		messages.addAll(theory.validate());
		messages.addAll(repository.validate());
		messages.addAll(searchAlgorithm.validate());
		return messages;
	}

}
