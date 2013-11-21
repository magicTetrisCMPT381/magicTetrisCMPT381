package org.MagicTetris;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.BoardPanel;

/**
 * Entry point. Game starts here.
 * @author Da
 *
 */
public class MagicTetris extends JFrame {

	public MagicTetris(String string) {
		super(string);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicTetris frame = new MagicTetris("Magic Tetris");
		frame.getContentPane().setBackground(Color.BLACK);
		Player player1 = new Player();
//		Player player2 = new Player();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new GridBagLayout());
		
		
		Integer[][] piece1 = player1.getBoardPanelModel().createNextPiece();
//		Integer[][] piece2 = player2.getBoardPanelModel().createNextPiece();
		
		player1.getBoardPanelModel().setPlayer(player1);
		player1.getBoardPanelModel().setNextPiece(piece1);
		player1.getBoardPanelModel().spawnNextPiece();
		
//		player2.getBoardPanelModel().setNextPiece(piece2);
//		player2.getBoardPanelModel().spawnNextPiece();
		
		frame.add(player1.getBoardPanel());
		frame.add(player1.getStatusPanel());
//		frame.add(player2.getBoardPanel());
//		frame.add(player2.getStatusPanel());
		
		
		
		frame.addKeyListener(player1.getPlayerController());
//		frame.addKeyListener(player2.getPlayerController());

		
		frame.setSize(1440,900);
		frame.setVisible(true);
		
		player1.startGame();
//		player2.startGame();

	}

}
