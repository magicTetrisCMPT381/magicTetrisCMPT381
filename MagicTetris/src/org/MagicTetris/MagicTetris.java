package org.MagicTetris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

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
@SuppressWarnings("serial")
public class MagicTetris extends JFrame {

	Player player1;
	Player player2;
	
	public MagicTetris() {
		super("Magic Tetris");
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		createPlayer();
		
		add(player1.getBoardPanel());
		add(player1.getStatusPanel());
		add(player2.getBoardPanel());
		add(player2.getStatusPanel());
		setLayout(new GridLayout(1,0));
		requestFocus();
		
		addKeyListener(player1.getPlayerController());
		addKeyListener(player2.getPlayerController());
		pack();
		setResizable(false);
		setVisible(true);

	}
	
	public void createPlayer() {
		player1 = new Player();
		player2 = new Player();
		Integer[][] piece1 = player1.getBoardPanelModel().createNextPiece();
		Integer[][] piece2 = player2.getBoardPanelModel().createNextPiece();
		
		player1.getStatusPanelModel().setSpeed(1);
		player1.getBoardPanelModel().setPlayer(player1);
		player1.getBoardPanelModel().setNextPiece(piece1);
		player1.getBoardPanelModel().spawnNextPiece();
		
		player2.getStatusPanelModel().setSpeed(1);
		player2.getBoardPanelModel().setPlayer(player2);
		player2.getBoardPanelModel().setNextPiece(piece2);
		player2.getBoardPanelModel().spawnNextPiece();
		
		player1.getPlayerController().setDefaultControlKeys(1);
		player2.getPlayerController().setDefaultControlKeys(2);
	}
	
	public void startGame() {
		player1.startGame();
		player2.startGame();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicTetris frame = new MagicTetris();
		frame.startGame();
		
		
		

		

		

		
		

		


	}

}
