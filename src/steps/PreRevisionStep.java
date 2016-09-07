/**
 * 
 */
package steps;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;
import preRevision.AbstractStrategyFactoryMethod;
import preRevision.IStrategy;
import preRevision.StrategyFactoryMethod;
import validator.IValidate;

/**
 * @author wander
 *
 */
public class PreRevisionStep implements IStep {

	private RepositoryParameters repository;
	private TheoryParameters theory;
	private SearchAlgorithmParameters searchAlgorithm;
	
	@Override
	public void execute() throws Exception{
		AbstractStrategyFactoryMethod factoryMethod = new StrategyFactoryMethod();
		IStrategy preRevision = factoryMethod.factoryMethod(searchAlgorithm.getPreRevisionType());
		preRevision.execute(repository, theory, searchAlgorithm);
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
