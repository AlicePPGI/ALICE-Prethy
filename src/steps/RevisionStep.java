/**
 * 
 */
package steps;

import parameters.TheoryParameters;
import validator.IValidate;

/**
 * @author wander
 *
 */
public class RevisionStep implements IStep {

	private TheoryParameters theory;
	
	@Override
	public void execute() throws Exception{
		try {
			String dataSetName = theory.stripExtension(theory.getDataSetFile());
			String thyName = theory.stripExtension(theory.getThyFile());
			String fdtName = theory.stripExtension(theory.getFdtFile());
			// TODO You need to compile FORTE System.
			Runtime.getRuntime().exec(this.theory.getRevisionSystem() + "(" + dataSetName + "," + thyName + "," + fdtName + "," + theory.getLogFile() + "," + theory.getLogLevel() + "," + theory.getSeed() + "," + theory.getTrainingAndRuns() + ")");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public void updateParameter(String identifier, IValidate parameter) {
		if (identifier.equals(IStep.THEORY_PARAMETER_IDENTIFIER) && (parameter instanceof TheoryParameters)) {
			theory = (TheoryParameters) parameter;
		}
	}
	
}
