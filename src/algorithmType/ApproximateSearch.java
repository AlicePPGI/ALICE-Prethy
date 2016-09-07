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
public class ApproximateSearch implements IAlgorithmType {

	@Override
	public List<ODP> execute(Repository repository, List<String> theoryRules) throws Exception{
		List<ODP> patterns = new ArrayList<ODP>();
		List<String> rules = new ArrayList<String>();
		SearchUtil searchUtil = SearchUtil.getInstance();
		for(int i = 0; i < repository.getOdp().size(); i++){
			ODP odp = repository.getOdp().get(i);
			int numberOfLines = 0;
			for(int j = 0; j < theoryRules.size(); j++){
				if(odp.isContainedIn(theoryRules.get(j))){
					numberOfLines++;
					rules.add(theoryRules.get(j));
					if(numberOfLines == odp.getNumberOfLines()){
						ODP fakeODP = new ODP();
						fakeODP.setName(odp.getName());
						fakeODP.setDescription(odp.getDescription());
						fakeODP.setEntryPointClause(odp.getEntryPointClause());
						fakeODP.setSuperSet(odp.getSuperSet());
						fakeODP.setRule(rules.toArray(new String[rules.size()]));
						if(!patterns.contains(fakeODP) && searchUtil.canAdd(fakeODP, patterns)){
							patterns.removeAll(searchUtil.removeODPs(fakeODP, patterns));
							patterns.add(fakeODP);
						}
						break;
					}
				}
			}
			rules.clear();
		}
		return patterns;
	}

}
