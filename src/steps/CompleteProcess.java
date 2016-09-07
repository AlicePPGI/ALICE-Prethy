/**
 * 
 */
package steps;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;
import validator.IValidate;

/**
 * @author wander
 *
 */
public class CompleteProcess implements IStep {

	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;

	@Override
	public void execute() throws Exception{
		// TODO Auto-generated method stub
		PreRevisionStep preRevision = new PreRevisionStep();
		preRevision.updateParameter(REPOSITORY_PARAMETER_IDENTIFIER, repository);
		preRevision.updateParameter(THEORY_PARAMETER_IDENTIFIER, theory);
		preRevision.updateParameter(SEARCH_ALGORITHM_PARAMETER_IDENTIFIER, searchAlgorithm);
		preRevision.execute();
		RevisionStep revision = new RevisionStep();
		revision.updateParameter(THEORY_PARAMETER_IDENTIFIER, theory);
		revision.execute();
	}

	@Override
	public void updateParameter(String identifier, IValidate parameter) {
		if (identifier.equals(IStep.REPOSITORY_PARAMETER_IDENTIFIER) && (parameter instanceof RepositoryParameters)) {
			repository = (RepositoryParameters) parameter;
		}
		else {
			if (identifier.equals(IStep.THEORY_PARAMETER_IDENTIFIER) && (parameter instanceof TheoryParameters)) {
				theory = (TheoryParameters) parameter;
			}
			else {
				if (identifier.equals(IStep.SEARCH_ALGORITHM_PARAMETER_IDENTIFIER) && (parameter instanceof SearchAlgorithmParameters)) {
					searchAlgorithm = (SearchAlgorithmParameters) parameter;
				}
			}
		}
	}

}
