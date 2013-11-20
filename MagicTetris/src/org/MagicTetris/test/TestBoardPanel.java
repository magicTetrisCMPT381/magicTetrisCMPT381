package org.MagicTetris.test;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.patternModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
import org.MagicTetris.UIFragment.BoardPanel;


public class TestBoardPanel {

	public static void main(String[] args) {
		// Have a window...
		JFrame frame = new JFrame("TestBoardPanel");
		frame.setLayout(null);
		frame.setSize(800, 800);
		
		// Have a board...
		BoardPanel panel = new BoardPanel();
		
		// Have a board model...
		BoardPanelModel panelModel = new BoardPanelModel();
		
		// Get board model.
		SingleBlock[][] board = panelModel.getBoard();
		
		// Froze row 5 column 10. A blue block is expected. 
		board[5][10].setFrozen(true);
		
		// Set current piece as red I. 
		panelModel.setCurrentPiece(patternModel.patternI);
		panelModel.setCurrentPieceColor(Color.RED);
		
		// Set current piece to row 1 column 7.
		panelModel.setCurrentPieceCol(7);
		panelModel.setCurrentPieceRow(1);
		
		// Link model and view/control pair
		panel.setModel(panelModel);
		Dimension d = panel.getPreferredSize();
		panel.setMinimumSize(d);
		panel.setPreferredSize(d);
		panel.setMaximumSize(d);
		panel.setBounds(10,10,d.width,d.height);
		
		
		frame.add(panel);
		panel.repaint();
		
		// Show it.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
