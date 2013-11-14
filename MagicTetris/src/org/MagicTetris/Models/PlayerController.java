package org.MagicTetris.Models;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *  Key listener for receiving player action.
 *  
 *
 */
public class PlayerController extends KeyAdapter {
	KeyEvent rotate;
	KeyEvent down;
	KeyEvent left;
	KeyEvent right;
	KeyEvent useItem;
	KeyEvent changeItem;
	
	
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
