package org.MagicTetris.test;

import javax.swing.JOptionPane;

import org.MagicTetris.UIFragment.OptionPanel2;

public class TesterOptionDialog2 {

	public static void main(String[] args) {
		OptionPanel2 test = new OptionPanel2();
		int result = JOptionPane.showConfirmDialog(null, 
				test, 
				"Options", 
				JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		test.findSettingPanel();
		System.out.println(test.isUseKeyboardForPlayerOne());
		System.out.println(test.isUseKeyboardForPlayerTwo());

	}

}
