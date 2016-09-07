/**
 * 
 */
package steps;

import validator.IValidate;

/**
 * @author wander
 *
 */
public interface IStep {

	public final static String REPOSITORY_PARAMETER_IDENTIFIER = "repository";
	public final static String THEORY_PARAMETER_IDENTIFIER = "theory";
	public final static String SEARCH_ALGORITHM_PARAMETER_IDENTIFIER = "algorithm";
	
	public void execute() throws Exception;
	public void updateParameter(String identifier, IValidate parameter);
	
}
