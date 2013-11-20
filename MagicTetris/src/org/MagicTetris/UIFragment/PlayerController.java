package org.MagicTetris.UIFragment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.util.playerTimer;

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

	/**
	 * the {@link BoardPanelModel} this controller associated with.
	 */
	private BoardPanelModel boardModel;
	
	private playerTimer timer;
	/**
	 * the {@link StatusPanelModel} this controller associated with.
	 */
	private StatusPanelModel statusModel;
	
	public PlayerController(BoardPanelModel board,playerTimer timer) {
		rotate = KeyEvent.VK_W;
		down = KeyEvent.VK_S;
		left = KeyEvent.VK_A;
		right = KeyEvent.VK_D;
		useItem = KeyEvent.VK_Q;
		changeItem = KeyEvent.VK_E;
		this.boardModel = board;
		this.timer = timer;
		
	}	

	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}

	// TODO: replaced by actual code.
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == rotate){
			boardModel.getCurrentPieceRotate();
		}
		
		if(arg0.getKeyCode() == down){
			currentSpeed = timer.getDropSpeed();
			timer.setDropSpeed(2*currentSpeed);  //change speed to  2* current speed
		}
		
		if(arg0.getKeyCode() == left){
			boardModel.moveCurrentPieceLeft();
		}
		
		if(arg0.getKeyCode() == right){
			boardModel.moveCurrentPieceRight();
		}
		// if(arg0.getKeyCode() == useItem)
		// 	boardModel.getCurrentPieceRotate();
		
		// if(arg0.getKeyCode() == changeItem)
		// 	boardModel.getCurrentPieceRotate();
		
	}

	// TODO: replaced by actual code.
	@Override
	public void keyReleased(KeyEvent arg0) {
		//ToDo: BUG: When real speed change in the period, speed will set incorrectly
		if(arg0.getKeyCode() == down){
			timer.setDropSpeed(currentSpeed);
		}

		
		// if(arg0.getKeyCode() == useItem)
		// 	System.out.println("Released: useItem");
		
		// if(arg0.getKeyCode() == changeItem)
		// 	System.out.println("Released: changeItem");
		

	}



}
