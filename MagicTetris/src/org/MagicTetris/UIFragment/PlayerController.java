package org.MagicTetris.UIFragment;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;

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
	 * the {@link BoardPanelModel} this controller associated with.
	 */
	private BoardPanelModel boardModel;
	/**
	 * the {@link StatusPanelModel} this controller associated with.
	 */
	private StatusPanelModel statusModel;
	
	public PlayerController() {
		rotate = KeyEvent.VK_W;
		down = KeyEvent.VK_S;
		left = KeyEvent.VK_A;
		right = KeyEvent.VK_D;
		useItem = KeyEvent.VK_Q;
		changeItem = KeyEvent.VK_E;
		
	}	

	@Override
	public void keyTyped(KeyEvent arg0) {
			
	}

	// TODO: replaced by actual code.
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == rotate)
			System.out.println("Pressed: rotate");
		
		if(arg0.getKeyCode() == down)
			System.out.println("Pressed: down");
		
		if(arg0.getKeyCode() == left)
			System.out.println("Pressed: left");
		
		if(arg0.getKeyCode() == right)
			System.out.println("Pressed: right");
		
		if(arg0.getKeyCode() == useItem)
			System.out.println("Pressed: useItem");
		
		if(arg0.getKeyCode() == changeItem)
			System.out.println("Pressed: changeItem");
		
	}

	// TODO: replaced by actual code.
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == rotate)
			System.out.println("Released: rotate");
		
		if(arg0.getKeyCode() == down)
			System.out.println("Released: down");
		
		if(arg0.getKeyCode() == left)
			System.out.println("Released: left");
		
		if(arg0.getKeyCode() == right)
			System.out.println("Released: right");
		
		if(arg0.getKeyCode() == useItem)
			System.out.println("Released: useItem");
		
		if(arg0.getKeyCode() == changeItem)
			System.out.println("Released: changeItem");
		

	}



}
