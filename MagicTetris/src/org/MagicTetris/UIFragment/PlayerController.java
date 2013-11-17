package org.MagicTetris.UIFragment;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;

/**
 *  Key listener for receiving player action.
 *  
 *
 */
public class PlayerController extends KeyAdapter {
	/**
	 * the key to rotate block
	 */
	private KeyEvent rotate;
	/**
	 * the key to move block down
	 */
	private KeyEvent down;
	/**
	 * the key to move block left
	 */
	private KeyEvent left;
	/**
	 * the key to move block right
	 */
	private KeyEvent right;
	/**
	 * the key to use current item
	 */
	private KeyEvent useItem;
	/**
	 * the key to change current item.
	 */
	private KeyEvent changeItem;
	/**
	 * the {@link BoardPanelModel} this controller associated with.
	 */
	private BoardPanelModel boardModel;
	/**
	 * the {@link StatusPanelModel} this controller associated with.
	 */
	private StatusPanelModel statusModel;
	
	@SuppressWarnings("unused")
	private PlayerController() {
		// Block default constructor.
	}

	public PlayerController(KeyEvent rotateKey,
							KeyEvent downKey,
							KeyEvent leftKey,
							KeyEvent rightKey,
							KeyEvent useItemKey,
							KeyEvent changeItemKey){
		rotate = rotateKey;
		down = downKey;
		left = leftKey;
		right = rightKey;
		useItem = useItemKey;
		changeItem = changeItemKey;
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		super.keyTyped(arg0);
	}



}
