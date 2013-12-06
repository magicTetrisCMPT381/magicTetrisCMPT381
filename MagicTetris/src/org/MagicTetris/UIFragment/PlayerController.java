package org.MagicTetris.UIFragment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.util.KeySettings;
import org.MagicTetris.util.KeySettings.DEFAULT_KEYS;
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

	public void LoadControlKeys(KeySettings keys) {
		if (keys != null) {
			this.rotate = keys.getKEY_ROTATE();
			this.down = keys.getKEY_DOWN();
			this.left = keys.getKEY_LEFT();
			this.right = keys.getKEY_RIGHT();
			this.useItem = keys.getKEY_USE_ITEM();
			this.changeItem = keys.getKEY_CHANGE_ITEM();
			return;
		}
		
		throw new IllegalArgumentException("Null is not permitted");
	}
	
	public void setDefaultControlKeys(DEFAULT_KEYS key_group){
		switch (key_group) {
		case ONE:
			LoadControlKeys(new KeySettings(DEFAULT_KEYS.ONE));
			break;
			
		case TWO:
			LoadControlKeys(new KeySettings(DEFAULT_KEYS.TWO));
			break;
			
		default:
			throw new IllegalArgumentException();
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
		if(e.getKeyCode() == useItem){
			 player.useItem();
		}
		if(e.getKeyCode() == changeItem){
			player.changeItem();
		}
				 	
		movePiece();
		
		

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

	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	public void setDown(int down) {
		this.down = down;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public void setUseItem(int useItem) {
		this.useItem = useItem;
	}

	public void setChangeItem(int changeItem) {
		this.changeItem = changeItem;
	}



}
