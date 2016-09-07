/**
 * 
 */
package algorithmType;

import presentation.SearchScreen;

/**
 * @author wsantos
 *
 */
public class AlgorithmTypeFactoryMethod extends AbstractAlgorithmTypeFactoryMethod {

	@Override
	public IAlgorithmType factoryMethod(String key) {
		if (key == null || key.equals("")){
			return null;
		}
		if (key.equals(SearchScreen.EXACT_SEARCH_ALGORITHM)) {
			return new ExactSearch();
		}
		if (key.equals(SearchScreen.APPROXIMATE_SEARCH_ALGORITHM)) {
			return new ApproximateSearch();
		}
		return null;
	}

}
