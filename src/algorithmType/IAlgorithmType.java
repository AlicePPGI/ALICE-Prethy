/**
 * 
 */
package algorithmType;

import java.util.List;

import repository.ODP;
import repository.Repository;

/**
 * @author wsantos
 *
 */
public interface IAlgorithmType {

	public List<ODP> execute(Repository repository, List<String> theoryRules) throws Exception;

}
