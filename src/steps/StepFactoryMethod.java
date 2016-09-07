/**
 * 
 */
package steps;

/**
 * @author wander
 *
 */
public class StepFactoryMethod extends AbstractStepFactoryMethod {

	@Override
	public IStep factoryMethod(Integer key) {
		switch (key) {
			case 0:
				return new CompleteProcess();
			case 1:
				return new PreRevisionStep();
			case 2:
				return new RevisionStep();
		}
		return null;
	}

}
