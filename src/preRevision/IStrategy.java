/**
 * 
 */
package preRevision;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;

/**
 * @author wander
 *
 */
public interface IStrategy {

	public void execute(RepositoryParameters repositoryParameters, TheoryParameters theoryParameters, SearchAlgorithmParameters searchAlgorithmParameters) throws Exception;
	
}
