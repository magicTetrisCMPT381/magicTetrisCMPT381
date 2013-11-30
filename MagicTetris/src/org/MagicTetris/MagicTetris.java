package org.MagicTetris;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.MagicTetris.Models.Player;
import org.MagicTetris.UIFragment.OptionPanel;

/**
 * Entry point. Game starts here.
 * @author Da
 *
 */
@SuppressWarnings("serial")
public class MagicTetris extends JFrame {

	private Player player1;
	private Player player2;
	private boolean isPaused;
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
		
		
		isPaused = true;
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
		addKeyListener(new mainFrameController(this));
	}
	
	protected void addPlayerControls(){
		addKeyListener(player1.getPlayerController());
		addKeyListener(player2.getPlayerController());
	}
	
	protected void removePlayerControls() {
		removeKeyListener(player1.getPlayerController());
		removeKeyListener(player2.getPlayerController());
	}
	
	public void startGame() {
		player1.startGame();
		player2.startGame();
	}
	
	public void pauseGame(){
		player1.pauseGame();
		player2.pauseGame();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MagicTetris frame = new MagicTetris();
	}
	
	private class mainFrameController extends KeyAdapter{
		private final int START_KEY = KeyEvent.VK_ENTER;
		private final int OPTION_KEY = KeyEvent.VK_ESCAPE;
		private MagicTetris frame;
		public mainFrameController(MagicTetris frame) {
			this.frame = frame;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == START_KEY) {
				if (isPaused) {
					frame.startGame();
					frame.addPlayerControls();
					isPaused = false;
				}
				else {
					frame.pauseGame();
					frame.removePlayerControls();
					isPaused = true;
				}
				
			}
			if (e.getKeyCode() == OPTION_KEY) {
				frame.pauseGame();
				OptionPanel test = new OptionPanel();
				int selection = JOptionPane.showConfirmDialog(null, 
						test, 
						"Options", 
						JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE);
				if (!isPaused) {
					frame.startGame();
				}
			}
		}
		
	}

}
