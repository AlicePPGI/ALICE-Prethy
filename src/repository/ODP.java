/**
 * 
 */
package repository;

import javax.xml.bind.annotation.XmlType;

/**
 * @author wander
 *
 */
@XmlType(propOrder={"name","description","entryPointClause","rule","superSet"})
public class ODP {

	private String name;
	private String description;
	private String[] entryPointClause;
	private String[] rule;
	private String[] superSet;

	public int getNumberOfLines() {
		if(this.rule == null) {
			return 0;
		}
		return this.rule.length;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getEntryPointClause() {
		return this.entryPointClause;
	}
	
	public void setEntryPointClause(String[] entryPointClause) {
		this.entryPointClause = entryPointClause;
	}

	public String[] getRule() {
		return this.rule;
	}
	
	public void setRule(String[] line) {
		this.rule = line;
	}

	public String[] getSuperSet() {
		return this.superSet;
	}

	public void setSuperSet(String[] superSet) {
		this.superSet = superSet;
	}

	public Boolean belongs(String rule) {
		if(rule == null || rule.trim().equals("") || !rule.contains(":-")) {
			return Boolean.FALSE;
		}
		String[] predicates = this.separateConsequentsAndAntecedents(rule);
		String consequent = predicates[0];
		String body = predicates[1];
		for(String odpRule:this.rule) {
			String[] odpPredicates = this.separateConsequentsAndAntecedents(odpRule);
			String odpConsequent = odpPredicates[0];
			String odpBody = odpPredicates[1];
			if(consequent.equals(odpConsequent) && body.equals(odpBody)){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	private String[] separateConsequentsAndAntecedents(String rule) {
		rule = rule.replaceAll("\\s+", "");
		String[] predicates = rule.split(":-",2);
		return predicates;
	}
	
	private String[] generateAntecedents(String body) {
		String[] antecedents = null;
		if(body.replaceAll("\\s+", "").contains("),")) {
			antecedents = body.split("\\),");
			for(int i=0; i<antecedents.length-1;i++) {
				antecedents[i] = antecedents[i]+")";
			}
			if(antecedents[antecedents.length-1].charAt(antecedents[antecedents.length-1].length()-1) == '.'){
				antecedents[antecedents.length-1] = antecedents[antecedents.length-1].substring(0, antecedents[antecedents.length-1].length()-1);
			}
		}
		else{
			antecedents = new String[1];
			if (body.charAt(body.length()-1) == '.'){
				antecedents[0] = body.substring(0, body.length()-1);
			}
			else{
				antecedents[0] = body;
			}
		}
		return antecedents;
	}

	public Boolean isEntryPointClause(String rule) {
		if(rule == null || rule.trim().equals("") || !rule.contains(":-")) {
			return Boolean.FALSE;
		}
		for(String odpClause:this.entryPointClause){
			if(rule.replaceAll("\\s+", "").equals(odpClause.replaceAll("\\s+", ""))){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public Boolean usesODP(String rule) {
		if(rule == null || rule.trim().equals("") || !rule.contains(":-")) {
			return Boolean.FALSE;
		}
		String[] rulePredicates = this.separateConsequentsAndAntecedents(rule);
		String body = rulePredicates[1];
		for(String odpEntryClause:this.entryPointClause) {
			String[] odpPredicates = this.separateConsequentsAndAntecedents(odpEntryClause);
			String odpConsequent = odpPredicates[0];
			if(body.indexOf(odpConsequent)>= 0){
					return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	public Boolean isContainedIn(String rule) {
		if(rule == null || rule.trim().equals("") || !rule.contains(":-")) {
			return Boolean.FALSE;
		}
		if(this.belongs(rule)) {
			return Boolean.TRUE;
		}
		String[] rulePredicates = this.separateConsequentsAndAntecedents(rule);
		String consequent = rulePredicates[0];
		String body = rulePredicates[1];
		int achou;
		for(String odpRule:this.rule) {
			String[] odpPredicates = this.separateConsequentsAndAntecedents(odpRule);
			String[] odpBody = this.generateAntecedents(odpPredicates[1]);
			achou = 0;
			if(consequent.equals(odpPredicates[0])) {
				for(String odpAntecedent:odpBody){
					if(body.indexOf(odpAntecedent) >= 0){
						achou++;
						continue;
					}
				}
			}
			if(achou == odpBody.length){
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	@Override
	public boolean equals(Object o){
		if(!(o instanceof ODP)){
			return false;
		}
		ODP other = (ODP) o;
		if(other.getName() == null || this.name == null){
			return false;
		}
		if(this.name.equals(other.getName())){
			return true;
		}
		return false;
	}

}
