/**
 * 
 */
package validator;

import java.util.List;

import parameters.TheoryParameters;


/**
 * @author wander
 *
 */
public class RunRevisionValidator implements IValidate {

	private TheoryParameters theory;

	public RunRevisionValidator(TheoryParameters theory) {
		this.theory = theory;
	}
	
	@Override
	public List<String> validate() {
		List<String> messages;
		messages = theory.validate();
		return messages;
	}

}
