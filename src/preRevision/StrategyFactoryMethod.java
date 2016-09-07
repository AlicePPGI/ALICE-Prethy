/**
 * 
 */
package preRevision;

import presentation.SearchScreen;

/**
 * @author wander
 *
 */
public class StrategyFactoryMethod extends AbstractStrategyFactoryMethod {

	@Override
	public IStrategy factoryMethod(String key) {
		if (key == null || key.trim().equals("")) {
			return null;
		}
		if (key.equals(SearchScreen.AUTOMATIC_PROTECT_MODE)) {
			return new AutomaticProtectStrategy();
		}
		if (key.equals(SearchScreen.SEMIAUTOMATIC_PROTECT_MODE)) {
			return new SemiAutomaticStrategy();
		}
		return null;
	}

}
