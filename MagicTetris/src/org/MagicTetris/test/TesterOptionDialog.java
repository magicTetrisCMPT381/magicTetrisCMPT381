package org.MagicTetris.test;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.MagicTetris.UIFragment.OptionPanel;

public class TesterOptionDialog {

	public static void main(String[] args) {
		OptionPanel test = new OptionPanel();
		int selection = JOptionPane.showConfirmDialog(null, 
				test, 
				"Options", 
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		String[][] result = test.getOptions();
		for (String[] strings : result) {
			for (String s : strings) {
				System.out.println(s);
			}
		}
		int[][] keys = test.getKeys();
		for (int[] is : keys) {
			for (int i : is) {
				System.out.println(i);
			}
		}
		System.out.println(selection);
		
	}

}
