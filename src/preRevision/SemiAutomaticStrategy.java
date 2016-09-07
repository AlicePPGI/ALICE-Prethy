package preRevision;

import java.io.BufferedReader;
import java.util.List;

import algorithmType.AbstractAlgorithmTypeFactoryMethod;
import algorithmType.AlgorithmTypeFactoryMethod;
import algorithmType.IAlgorithmType;
import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;
import presentation.SelectionScreen;
import repository.ODP;
import repository.Repository;

public class SemiAutomaticStrategy implements IStrategy {

	@Override
	public void execute(RepositoryParameters repositoryParameters, TheoryParameters theoryParameters, SearchAlgorithmParameters searchAlgorithmParameters) throws Exception{
		AbstractAlgorithmTypeFactoryMethod factoryMethod = new AlgorithmTypeFactoryMethod();
		IAlgorithmType search = factoryMethod.factoryMethod(searchAlgorithmParameters.getAlgorithmType());
		FileUtil fileUtil = FileUtil.getInstance();
		Repository repository = fileUtil.openRepository(repositoryParameters);
		BufferedReader thy = fileUtil.openTHY(theoryParameters);
		List<String> theoryRules = fileUtil.getTheoryRules(thy);
		fileUtil.closeTHY(thy);
		List<ODP> odpsFound = search.execute(repository, theoryRules);
		SelectionScreen selectionScreen = new SelectionScreen(odpsFound);
	}

}
