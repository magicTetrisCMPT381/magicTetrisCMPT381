package org.MagicTetris.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;

import org.MagicTetris.UIFragment.BoardPanel;


public class TestBoardPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("TestBoardPanel");
		frame.setLayout(null);
		frame.setSize(800, 800);
		
		BoardPanel panel_1 = new BoardPanel();
//		BoardPanel panel_2 = new BoardPanel();
		
		Dimension d = panel_1.getPreferredSize();
//		panel_1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.RED, Color.CYAN));
//		panel_1.setBounds(5, 5, d.width, d.height);
		panel_1.setMinimumSize(d);
		panel_1.setPreferredSize(d);
		panel_1.setMaximumSize(d);
		panel_1.setBounds(10,10,d.width,d.height);
		
//		panel_2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED,Color.BLUE, Color.CYAN));
//		panel_2.setBounds(d.width + 10, 5, d.width, d.height);
//		panel_2.setMinimumSize(d);
//		panel_2.setPreferredSize(d);
//		panel_2.setMaximumSize(d);
		
//		GridBagConstraints c = new GridBagConstraints();
//		
//		c.anchor = GridBagConstraints.CENTER;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.ipadx = 20;
//		c.ipady = 20;
//		c.gridx = 0;
//		c.gridy = 0;
		
		frame.add(panel_1);
		
//		c.gridx = 1;
		
//		frame.add(panel_2);
		frame.setVisible(true);
	}

}
