/**
 * 
 */
package algorithmType;

import java.util.ArrayList;
import java.util.List;

import repository.ODP;
import repository.Repository;

/**
 * @author wsantos
 *
 */
public class ExactSearch implements IAlgorithmType {

	@Override
	public List<ODP> execute(Repository repository, List<String> theoryRules) throws Exception{
		List<ODP> patterns = new ArrayList<ODP>();
		SearchUtil searchUtil = SearchUtil.getInstance();
		for(int i = 0; i < repository.getOdp().size(); i++){
			ODP odp = repository.getOdp().get(i);
			int numberOfLines = 0;
			for(int j = 0; j < theoryRules.size(); j++){
				if(odp.belongs(theoryRules.get(j))){
					numberOfLines++;
					if(numberOfLines == odp.getNumberOfLines()){
						if(!patterns.contains(odp) && searchUtil.canAdd(odp, patterns)){
							patterns.removeAll(searchUtil.removeODPs(odp, patterns));
							patterns.add(odp);
						}
						break;
					}
				}
			}
		}
		return patterns;
	}
	
}
