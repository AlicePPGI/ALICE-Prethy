/**
 * 
 */
package algorithmType;

import java.util.ArrayList;
import java.util.List;

import repository.ODP;

/**
 * @author wsantos
 *
 */
class SearchUtil {

	private static final SearchUtil instance = new SearchUtil();
	
	private SearchUtil(){
		
	}
	
	public static final SearchUtil getInstance(){
		return instance;
	}

	public Boolean canAdd(ODP odp, List<ODP> patterns){
		if(odp == null){
			return Boolean.FALSE;
		}
		if(patterns.size() == 0){
			return Boolean.TRUE;
		}
		for(ODP pattern:patterns){
			for(String superSet:odp.getSuperSet()){
				if(pattern.getName().equals(superSet)){
					return Boolean.FALSE;
				}
			}
		}
		return Boolean.TRUE;
	}
	
	public List<ODP> removeODPs(ODP odp, List<ODP> patterns){
		List<ODP> removableODPs = new ArrayList<ODP>();
		for(ODP pattern:patterns){
			for(String superSet:pattern.getSuperSet()){
				if(odp.getName().equals(superSet)){
					removableODPs.add(pattern);
				}
			}
		}
		return removableODPs;
	}

}
