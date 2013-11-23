package org.MagicTetris.UIFragment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.util.playerTimerTask;

/**
 *  Key listener for receiving player action.
 *  
 *
 */
public class PlayerController implements KeyListener {
	/**
	 * the key to rotate block
	 */
	private int rotate;
	/**
	 * the key to move block down
	 */
	private int down;
	/**
	 * the key to move block left
	 */
	private int left;
	/**
	 * the key to move block right
	 */
	private int right;
	/**
	 * the key to use current item
	 */
	private int useItem;
	/**
	 * the key to change current item.
	 */
	private int changeItem;
	
	/**
	 * store current speed
	 */
	private float currentSpeed;
	private Player player;
	
	private final float MAX_SPEED = 75; 
	/**
	 * the {@link BoardPanelModel} this controller associated with.
	 */
	private BoardPanelModel boardModel;
	
	private playerTimerTask timer;
	/**
	 * the {@link StatusPanelModel} this controller associated with.
	 */
	private StatusPanelModel statusModel;
	
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveRotate;
	
	public PlayerController(BoardPanelModel board,playerTimerTask timer,Player player) {
		this.boardModel = board;
		this.timer = timer;
		currentSpeed = 1;
		this.player = player;
	}	

	public void setDefaultControlKeys(int DefaultKeyGroup){
		if (DefaultKeyGroup == 1) {
			this.rotate = KeyEvent.VK_W;
			this.down = KeyEvent.VK_S;
			this.left = KeyEvent.VK_A;
			this.right = KeyEvent.VK_D;
			this.useItem = KeyEvent.VK_Q;
			this.changeItem = KeyEvent.VK_E;
		}
		
		else {
			this.rotate = KeyEvent.VK_I;
			this.down = KeyEvent.VK_K;
			this.left = KeyEvent.VK_J;
			this.right = KeyEvent.VK_L;
			this.useItem = KeyEvent.VK_ALT;
			this.changeItem = KeyEvent.VK_CONTROL;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}


	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == rotate){
			moveRotate = true;
		}
		if(e.getKeyCode() == down){
			moveDown = true;
		}
		
		if(e.getKeyCode() == left){
			moveLeft = true;
		}
		
		if(e.getKeyCode() == right){
			moveRight = true;
		}
		
		movePiece();
		// if(arg0.getKeyCode() == useItem)
		// 	boardModel.getCurrentPieceRotate();
		
		// if(arg0.getKeyCode() == changeItem)
		// 	boardModel.getCurrentPieceRotate();
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		//ToDo: BUG: When real speed change in the period, speed will set incorrectly
		if(e.getKeyCode() == rotate){
			moveRotate = false;
		}
		if(e.getKeyCode() == down){
			moveDown = false;
		}
		if(e.getKeyCode() == left){
			moveLeft = false;
		}
		if(e.getKeyCode() == right){
			moveRight = false;
		}
		movePiece();
		// if(arg0.getKeyCode() == useItem)
		// 	System.out.println("Released: useItem");
		
		// if(arg0.getKeyCode() == changeItem)
		// 	System.out.println("Released: changeItem");
		

	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	
	private void movePiece(){
		if (moveRotate) {
			boardModel.rotateCurrentPiece();
		}
		
		if (moveDown) {
			currentSpeed = MAX_SPEED;
			player.setTimer();
		}
		else {
			if (currentSpeed != player.getSpeed()) {
				currentSpeed = player.getSpeed();
				player.setTimer();
			}			
		}
		
		
		if (moveLeft) {
			boardModel.moveCurrentPieceLeft();
		}
		
		if (moveRight) {
			boardModel.moveCurrentPieceRight();
		}
		
		
		player.getBoardPanel().repaint();
	}



}
