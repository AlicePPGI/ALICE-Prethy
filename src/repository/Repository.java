/**
 * 
 */
package repository;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author wander
 *
 */
@XmlRootElement
public class Repository {

	List<ODP> odp;

	@XmlElement(name="odp")
	public List<ODP> getOdp() {
		return odp;
	}

	public void setOdp(List<ODP> odp) {
		this.odp = odp;
	}
	
}
