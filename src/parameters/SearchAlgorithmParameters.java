/**
 * 
 */
package parameters;

import java.util.ArrayList;
import java.util.List;

import presentation.SearchScreen;
import validator.IValidate;

/**
 * @author wander
 *
 */
public class SearchAlgorithmParameters implements IValidate {

	private String algorithmType = "";
	private String preRevisionType = "";
	
	public String getAlgorithmType() {
		return algorithmType;
	}
	
	public void setAlgorithmType(String algorithmType) {
		this.algorithmType = algorithmType;
	}
	
	public String getPreRevisionType() {
		return preRevisionType;
	}
	
	public void setPreRevisionType(String preRevisionType) {
		this.preRevisionType = preRevisionType;
	}

	@Override
	public List<String> validate() {
		List<String> messages = new ArrayList<String>();
		if (algorithmType == null || algorithmType.trim().equals("") || 
			(!algorithmType.equals(SearchScreen.EXACT_SEARCH_ALGORITHM) && 
			!algorithmType.equals(SearchScreen.APPROXIMATE_SEARCH_ALGORITHM))) {
			messages.add("Invalid search algorithm.");
		}
		if (preRevisionType == null || preRevisionType.trim().equals("") ||
			(!preRevisionType.equals(SearchScreen.AUTOMATIC_PROTECT_MODE) &&
			!preRevisionType.equals(SearchScreen.SEMIAUTOMATIC_PROTECT_MODE))) {
			messages.add("Invalid protect mode.");
		}
		return messages;
	}

}
