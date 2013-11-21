package org.MagicTetris;

import java.awt.GridBagLayout;

import javax.swing.JFrame;

import org.MagicTetris.Models.Player;

/**
 * Entry point. Game starts here.
 * @author Da
 *
 */
public class MagicTetris extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicTetris frame = new MagicTetris();
		
		Player player1 = new Player();
		Player player2 = new Player();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLayout(new GridBagLayout());
		
		
		Integer[][] piece1 = player1.getBoardPanelModel().createNextPiece();
		Integer[][] piece2 = player2.getBoardPanelModel().createNextPiece();
		
		player1.getBoardPanelModel().setNextPiece(piece1);
		player1.getBoardPanelModel().spawnNextPiece();
		
		player2.getBoardPanelModel().setNextPiece(piece2);
		player2.getBoardPanelModel().spawnNextPiece();
		
		frame.add(player1.getBoardPanel());
		frame.add(player2.getBoardPanel());
		
		
		
		frame.addKeyListener(player1.getPlayerController());
		frame.addKeyListener(player2.getPlayerController());

		

		frame.setVisible(true);
		frame.setBounds(0, 0, 1600, 1000);
		player1.startGame();
//		player2.startGame();

	}

}
