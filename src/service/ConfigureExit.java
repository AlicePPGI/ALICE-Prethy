/**
 * 
 */
package service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * @author wander
 *
 */
public class ConfigureExit implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int opcao;
		Object[] opcoes = {"Yes", "No"};
		opcao = JOptionPane.showOptionDialog(null, "Do you really want to exit the System?", "Finish application", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcoes, opcoes[0]);
		if(opcao == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}

}
