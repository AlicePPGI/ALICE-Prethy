/**
 * 
 */
package presentation;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import parameters.RepositoryParameters;
import parameters.SearchAlgorithmParameters;
import parameters.TheoryParameters;
import presentation.util.CurvedGradientPanel;

/**
 * @author wander
 *
 */
public class MainScreen {

	private final static Integer MENU_CONFIGURE = 1;
	private final static Integer MENU_RUN = 2;
	private final static Integer MENU_HELP = 3;
	private final static String[] CONFIGURE_ITENS_LABELS = new String[]{"Theory", "ODPs Repository", "Pre-Revision", "Exit"};
	private final static String[] CONFIGURE_ITENS_ACTIONS = new String[]{"service.ConfigureTheory", "service.ConfigureRepository", "service.ConfigureSearch", "service.ConfigureExit"};
	private final static String[] RUN_ITENS_LABELS = new String[]{"Pre-Revision", "Revision", "All"};
	private final static String[] RUN_ITENS_ACTIONS = new String[]{"service.RunPreRevision", "service.RunRevision", "service.RunAll"};
	private final static String[] HELP_ITENS_LABELS = new String[]{"Configure Menu", "Run Menu", "About PRETHY System"};
	private final static String[] HELP_ITENS_ACTIONS = new String[]{"service.HelpConfigure", "service.HelpRun", "service.HelpAbout"};
	private JFrame     jFrame = new JFrame();
	private TheoryParameters theoryParameters = new TheoryParameters();
	private RepositoryParameters repositoryParameters = new RepositoryParameters();
	private SearchAlgorithmParameters searchAlgorithmParameters = new SearchAlgorithmParameters();
	
	public MainScreen(){
		jFrame.setSize(800, 600);
		jFrame.setTitle("Pre-Revision Theory System");
		jFrame.setExtendedState(JFrame.NORMAL);
		jFrame.setResizable(false);
		jFrame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - jFrame.getSize().width)/2, (Toolkit.getDefaultToolkit().getScreenSize().height - jFrame.getSize().height)/2);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.add(new CurvedGradientPanel(Color.DARK_GRAY, Color.white));
		jFrame.setJMenuBar(this.constructMenu());
		jFrame.setVisible(true);
	}
	
	private JMenuBar constructMenu() {
		JMenuBar   jMenuBar = new JMenuBar();
		try {
			jMenuBar.add(this.constructMenu(MENU_CONFIGURE));
			jMenuBar.add(this.constructMenu(MENU_RUN));
			jMenuBar.add(this.constructMenu(MENU_HELP));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		return jMenuBar;
	}

	private JMenu constructMenu(Integer menuIdentifier) throws Exception{
		JMenu jMenu;
		List<JMenuItem> itens;
		Integer separatorPos;
		switch (menuIdentifier) {
			case 1:
				jMenu = new JMenu("Configure");
				itens = this.getItens(CONFIGURE_ITENS_LABELS, CONFIGURE_ITENS_ACTIONS, menuIdentifier);
				separatorPos = 3;
				break;
			case 2:
				jMenu = new JMenu("Run");
				itens = this.getItens(RUN_ITENS_LABELS, RUN_ITENS_ACTIONS, menuIdentifier);
				separatorPos = null;
				break;
			case 3:
				jMenu = new JMenu("Help");
				itens = this.getItens(HELP_ITENS_LABELS, HELP_ITENS_ACTIONS, menuIdentifier);
				separatorPos = 2;
				break;
			default:
				jMenu = null;
				itens = null;
				separatorPos = null;
		}
		this.addMenuItens(jMenu, itens, separatorPos);
		return jMenu;
	}

	private List<JMenuItem> getItens(String[] labels, String[] actions, Integer menuIdentifier) throws Exception{
		List<JMenuItem> itens = new ArrayList<JMenuItem>();
		for (int i=0; i < labels.length; i++) {
			JMenuItem jMenuItem = new JMenuItem(labels[i]);
			Class<?> clazz = null;
			Constructor<?> constructor = null;
			Object instance = null;
			switch (menuIdentifier) {
				case 1:
					if (i == 3) {
						instance = Class.forName(actions[i]).newInstance();
					}
					else {
						clazz = Class.forName(actions[i]);
						constructor = clazz.getConstructor(MainScreen.class);
						instance = constructor.newInstance(this);
					}
					break;
				case 2:
					clazz = Class.forName(actions[i]);
					if (i == 0) {
						constructor = clazz.getConstructor(RepositoryParameters.class, TheoryParameters.class, SearchAlgorithmParameters.class);
						instance = constructor.newInstance(this.getRepositoryParameters(), this.getTheoryParameters(), this.getSearchAlgorithmParameters());
					}
					else {
						if (i == 1) {
							constructor = clazz.getConstructor(TheoryParameters.class);
							instance = constructor.newInstance(this.getTheoryParameters());
						}
						else {
							constructor = clazz.getConstructor(RepositoryParameters.class, TheoryParameters.class, SearchAlgorithmParameters.class);
							instance = constructor.newInstance(this.getRepositoryParameters(), this.getTheoryParameters(), this.getSearchAlgorithmParameters());
						}
					}
					break;
				case 3:
					instance = Class.forName(actions[i]).newInstance();
					break;
			}
			jMenuItem.addActionListener((ActionListener) instance);
			itens.add(jMenuItem);
		}
		return itens;
	}
	
	private void addMenuItens(JMenu jMenu, List<JMenuItem> itens, Integer separatorPos) {
		for (int i=0; i < itens.size(); i++) {
			if (separatorPos != null && i == separatorPos) {
				jMenu.addSeparator();
			}
			jMenu.add(itens.get(i));
		}
	}
	
	public TheoryParameters getTheoryParameters() {
		return theoryParameters;
	}

	public void setParametersTheory(TheoryParameters theoryParameters) {
		this.theoryParameters = theoryParameters;
	}

	public RepositoryParameters getRepositoryParameters() {
		return repositoryParameters;
	}

	public void setRepositoryParameters(RepositoryParameters repositoryParameters) {
		this.repositoryParameters = repositoryParameters;
	}

	public SearchAlgorithmParameters getSearchAlgorithmParameters() {
		return searchAlgorithmParameters;
	}

	public void setSearchAlgorithmParameters(SearchAlgorithmParameters searchAlgorithmParameters) {
		this.searchAlgorithmParameters = searchAlgorithmParameters;
	}

}
