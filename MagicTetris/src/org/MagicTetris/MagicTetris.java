package org.MagicTetris;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.LayerUI;

import org.MagicTetris.Models.Player;
import org.MagicTetris.UIFragment.EffectLayer;
import org.MagicTetris.UIFragment.OptionPanel;

/**
 * Entry point. Game starts here.
 * @author Da
 *
 */
@SuppressWarnings("serial")
public class MagicTetris extends JFrame {

	private Player playerOne;
	private Player playerTwo;
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
		System.out.println(playerOne);
		System.out.println(playerTwo);
	}
	
	protected void createPlayer() {
		playerOne = new Player();
		playerTwo = new Player();
		
		playerOne.setOpponent(playerTwo);
		playerTwo.setOpponent(playerOne);
		
		Integer[][] piece1 = playerOne.getBoardPanelModel().createNextPiece();
		Integer[][] piece2 = playerTwo.getBoardPanelModel().createNextPiece();
		
		playerOne.getStatusPanelModel().setSpeed(1);
		playerOne.getBoardPanelModel().setPlayer(playerOne);
		playerOne.getBoardPanelModel().setNextPiece(piece1);
		playerOne.getBoardPanelModel().spawnNextPiece();
		
		playerTwo.getStatusPanelModel().setSpeed(1);
		playerTwo.getBoardPanelModel().setPlayer(playerTwo);
		playerTwo.getBoardPanelModel().setNextPiece(piece2);
		playerTwo.getBoardPanelModel().spawnNextPiece();
		
		playerOne.getPlayerController().setDefaultControlKeys(1);
		playerTwo.getPlayerController().setDefaultControlKeys(2);
	}
	
	protected void addPanels(){
		add(playerOne.playerBoard);
		add(playerOne.getStatusPanel());
		
		add(playerTwo.playerBoard);
		add(playerTwo.getStatusPanel());
	}
	
	protected void addDefaultControls() {
		addKeyListener(new mainFrameController(this));
	}
	
	protected void addPlayerControls(){
		addKeyListener(playerOne.getPlayerController());
		addKeyListener(playerTwo.getPlayerController());
	}
	
	protected void removePlayerControls() {
		removeKeyListener(playerOne.getPlayerController());
		removeKeyListener(playerTwo.getPlayerController());
	}
	
	public void startGame() {
		playerOne.startGame();
		playerTwo.startGame();
	}
	
	public void pauseGame(){
		playerOne.pauseGame();
		playerTwo.pauseGame();
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
