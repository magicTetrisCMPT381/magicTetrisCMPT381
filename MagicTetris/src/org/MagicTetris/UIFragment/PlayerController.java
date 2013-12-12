package org.MagicTetris.UIFragment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.java.games.input.Component;
import net.java.games.input.Component.POV;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.util.ControllerListener;
import org.MagicTetris.util.KeySettings;
import org.MagicTetris.util.KeySettings.DEFAULT_KEYS;
import org.MagicTetris.util.playerTimerTask;

/**
 *  Key listener for receiving player action.
 *  
 *
 */
public class PlayerController implements KeyListener, ControllerListener {
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

	private float keyRotate = -1.0f;
	private float keyLeft = -1.0f;
	private float keyRight = -1.0f;
	private float keyDown = -1.0f;
	
	
	private KeySettings keys;
	
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

	public void setControlKeys(KeySettings keys) {
		if (keys != null) {
			this.rotate = keys.getKEY_ROTATE();
			this.down = keys.getKEY_DOWN();
			this.left = keys.getKEY_LEFT();
			this.right = keys.getKEY_RIGHT();
			this.useItem = keys.getKEY_USE_ITEM();
			this.changeItem = keys.getKEY_CHANGE_ITEM();
			this.keys = keys;
			return;
		}
		
		throw new IllegalArgumentException("Null is not permitted");
	}
	
	public void setDefaultControlKeys(DEFAULT_KEYS key_group){
		switch (key_group) {
		case ONE:
			this.keys = new KeySettings(DEFAULT_KEYS.ONE);
			setControlKeys(keys);
			break;
			
		case TWO:
			this.keys = new KeySettings(DEFAULT_KEYS.TWO);
			setControlKeys(keys);
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
	
	@Override
	public void HatSwitchChanged(float HatPosition){
		if(HatPosition == keyRotate){
			moveRotate = true;
		}
		else if(HatPosition == keyDown){
			moveDown = true;
		}
		else if(HatPosition == keyLeft){
			moveLeft = true;
		}
		else if(HatPosition == keyRight){
			moveRight = true;
		}
		else {
			moveRotate = false;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
		}
		
		movePiece();
	}

	@Override
	public void ButtonPressed(int ButtonNum) {
		if(ButtonNum == rotate){
			moveRotate = true;
		}
		if(ButtonNum == down){
			moveDown = true;
		}
		
		if(ButtonNum == left){
			moveLeft = true;
		}
		
		if(ButtonNum == right){
			moveRight = true;
		}
		
		movePiece();
		
	}

	@Override
	public void ButtonReleased(int ButtonNum) {
		if(ButtonNum == rotate){
			moveRotate = false;
		}
		if(ButtonNum == down){
			moveDown = false;
		}
		if(ButtonNum == left){
			moveLeft = false;
		}
		if(ButtonNum == right){
			moveRight = false;
		}
		if(ButtonNum == useItem){
			 player.useItem();
		}
		if(ButtonNum == changeItem){
			player.changeItem();
		}
				 	
		movePiece();
		
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

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = currentSpeed;
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

	public void setKeyRotate(float keyRotate) {
		this.keyRotate = keyRotate;
	}

	public void setKeyLeft(float keyLeft) {
		this.keyLeft = keyLeft;
	}

	public void setKeyRight(float keyRight) {
		this.keyRight = keyRight;
	}

	public void setKeyDown(float keyDown) {
		this.keyDown = keyDown;
	}





}
