package org.MagicTetris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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

	private Player player1;
	private Player player2;
	
	public MagicTetris() {
		super("Magic Tetris");
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridBagLayout());
		
		createPlayer();
		addPanels();
		addDefaultControls();

		setLayout(new GridLayout(1,0));
		requestFocus();
		
		pack();
		setResizable(false);
		setVisible(true);

	}
	
	protected void createPlayer() {
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
	
	protected void addPanels(){
		add(player1.getBoardPanel());
		add(player1.getStatusPanel());
		add(player2.getBoardPanel());
		add(player2.getStatusPanel());
	}
	
	protected void addDefaultControls() {
		addKeyListener(player1.getPlayerController());
		addKeyListener(player2.getPlayerController());
		addKeyListener(new mainFrameController(this));
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
//		frame.startGame();
	}
	
	private class mainFrameController extends KeyAdapter{
		private final int DEFAULT_START_KEY = KeyEvent.VK_ENTER;
		private final int OPTION_KEY = KeyEvent.VK_ESCAPE;
		private MagicTetris frame;

		public mainFrameController(MagicTetris frame) {
			this.frame = frame;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == DEFAULT_START_KEY) {
				frame.startGame();
				return;
			}
			if (e.getKeyCode() == OPTION_KEY) {
				
			}
		}
		
	}

}
