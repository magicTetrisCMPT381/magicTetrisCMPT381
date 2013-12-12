package org.MagicTetris;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import joystick.JInputJoystick;
import net.java.games.input.Component;
import net.java.games.input.Controller;

import org.MagicTetris.GameItems.*;
import org.MagicTetris.Models.*;
import org.MagicTetris.UIFragment.*;
import org.MagicTetris.util.ControllerPoller;
import org.MagicTetris.util.KeySettings.DEFAULT_KEYS;

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
	private boolean isControllerExist;
	private static ControllerPoller poller;
	private Thread controllerThread;

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
		
		
		
		
		isPaused = true;
		
		Tester();
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
		
		playerOne.getPlayerController().setDefaultControlKeys(DEFAULT_KEYS.ONE);
		playerTwo.getPlayerController().setDefaultControlKeys(DEFAULT_KEYS.TWO);
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
	
	private void Tester() {
		playerOne.getStatusPanelModel().addItem(new MagicFreezer());
		playerOne.getStatusPanelModel().addItem(new MagicCleaner());
		playerOne.getStatusPanelModel().addItem(new MagicShield());		
		playerOne.getStatusPanelModel().addItem(new MagicOdd());
		playerOne.getStatusPanelModel().addItem(new MagicFlash());
		
		
		playerTwo.getStatusPanelModel().addItem(new MagicFreezer());
		playerTwo.getStatusPanelModel().addItem(new MagicCleaner());
		playerTwo.getStatusPanelModel().addItem(new MagicShield());
		playerTwo.getStatusPanelModel().addItem(new MagicOdd());
		playerTwo.getStatusPanelModel().addItem(new MagicFlash());		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JInputJoystick stick = new JInputJoystick(Controller.Type.GAMEPAD);
		if (stick.isControllerConnected()) {
			poller = new ControllerPoller(stick);
		}
		MagicTetris frame = new MagicTetris();
		frame.isControllerExist = stick.isControllerConnected();
		
//		if (poller != null) {
//			frame.playerOne.getPlayerController().setKeyRotate(Component.POV.UP);
//			frame.playerOne.getPlayerController().setKeyLeft(Component.POV.LEFT);
//			frame.playerOne.getPlayerController().setKeyRight(Component.POV.RIGHT);
//			frame.playerOne.getPlayerController().setKeyDown(Component.POV.DOWN);
//			poller.setControlListener(frame.playerOne.getPlayerController());
//			frame.controllerThread = new Thread(poller);
//			frame.controllerThread.start();
//		}
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		
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
