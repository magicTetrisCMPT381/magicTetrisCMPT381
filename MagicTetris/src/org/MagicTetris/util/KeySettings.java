package org.MagicTetris.util;

import java.awt.event.KeyEvent;

/**
 * This class represents the key settings.
 * Any -1 in the fields means that an error occurs and should be handled.
 * @author NewDa
 *
 */
public class KeySettings {
	
	private int KEY_ROTATE;
	private int KEY_LEFT;
	private int KEY_RIGHT;
	private int KEY_DOWN;
	private int KEY_CHANGE_ITEM;
	private int KEY_USE_ITEM;
	
	public KeySettings() {
		KEY_ROTATE = -1;
		KEY_LEFT = -1;
		KEY_RIGHT = -1;
		KEY_DOWN = -1;
		KEY_CHANGE_ITEM = -1;
		KEY_USE_ITEM = -1;
	}
	
	public KeySettings(DEFAULT_KEYS KeyGroup) {
		
		switch (KeyGroup) {
		case ONE:
			KEY_ROTATE = KeyEvent.VK_W;
			KEY_LEFT = KeyEvent.VK_A;
			KEY_RIGHT = KeyEvent.VK_D;
			KEY_DOWN = KeyEvent.VK_S;
			KEY_CHANGE_ITEM = KeyEvent.VK_Q;
			KEY_USE_ITEM = KeyEvent.VK_E;
			break;

		case TWO:
			KEY_ROTATE = KeyEvent.VK_I;
			KEY_LEFT = KeyEvent.VK_J;
			KEY_RIGHT = KeyEvent.VK_L;
			KEY_DOWN = KeyEvent.VK_K;
			KEY_CHANGE_ITEM = KeyEvent.VK_U;
			KEY_USE_ITEM = KeyEvent.VK_O;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public static enum DEFAULT_KEYS{
		ONE,
		TWO
	}

	public int getKEY_ROTATE() {
		return KEY_ROTATE;
	}

	public void setKEY_ROTATE(int rotate) {
		KEY_ROTATE = rotate;
	}

	public int getKEY_LEFT() {
		return KEY_LEFT;
	}

	public void setKEY_LEFT(int left) {
		KEY_LEFT = left;
	}

	public int getKEY_RIGHT() {
		return KEY_RIGHT;
	}

	public void setKEY_RIGHT(int right) {
		KEY_RIGHT = right;
	}

	public int getKEY_DOWN() {
		return KEY_DOWN;
	}

	public void setKEY_DOWN(int down) {
		KEY_DOWN = down;
	}

	public int getKEY_CHANGE_ITEM() {
		return KEY_CHANGE_ITEM;
	}

	public void setKEY_CHANGE_ITEM(int change_item) {
		KEY_CHANGE_ITEM = change_item;
	}

	public int getKEY_USE_ITEM() {
		return KEY_USE_ITEM;
	}

	public void setKEY_USE_ITEM(int use_item) {
		KEY_USE_ITEM = use_item;
	}
	
	
}
