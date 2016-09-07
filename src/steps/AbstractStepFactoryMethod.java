/**
 * 
 */
package steps;

/**
 * @author wander
 *
 */
public abstract class AbstractStepFactoryMethod {

	public final static Integer RUN_ALL_KEY = 0;
	public final static Integer RUN_PRE_REVISION_KEY = 1;
	public final static Integer RUN_REVISION_KEY = 2;

	public abstract IStep factoryMethod(Integer key);
	
}
