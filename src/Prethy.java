import java.awt.Color;

import javax.swing.UIManager;

import presentation.MainScreen;

/**
 * 
 */

/**
 * @author wander
 *
 */
public class Prethy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("OptionPane.background", new Color(229, 255, 204));
			UIManager.put("Panel.background", new Color(229, 255, 204));
			MainScreen mScreen = new MainScreen();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
