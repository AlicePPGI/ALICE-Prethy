package repository;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class testeXML {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Repository.class);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		File xml = new File("c:/Users/wsantos/repository.xml");
		Repository repository = (Repository) unmarshaller.unmarshal(xml);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(repository, System.out);
	}

}
