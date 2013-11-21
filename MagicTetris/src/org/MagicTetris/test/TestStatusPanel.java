package org.MagicTetris.test;

import java.awt.Dimension;

import javax.swing.JFrame;

import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.StatusPanel;

public class TestStatusPanel extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestStatusPanel frame = new TestStatusPanel();
		StatusPanelModel spm = new StatusPanelModel();
		StatusPanel panel = new StatusPanel();
		frame.setLayout(null);
		Dimension d = panel.getPreferredSize();
		frame.add(panel);
		panel.setBounds(10,10,d.width,d.height);
		
		frame.setSize(800, 800);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
