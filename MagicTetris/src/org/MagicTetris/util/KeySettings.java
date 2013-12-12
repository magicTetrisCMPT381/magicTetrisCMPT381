package org.MagicTetris.util;

import java.awt.event.KeyEvent;

/**
 * This class represents the key settings.
 * Any -1 in the fields means that an error occurs and should be handled.
 * @author NewDa
 *
 */
public class KeySettings {
	
	private float KEY_ROTATE;
	private float KEY_LEFT;
	private float KEY_RIGHT;
	private float KEY_DOWN;
	private float KEY_CHANGE_ITEM;
	private float KEY_USE_ITEM;
	private boolean isXboxController;
	
	public KeySettings() {
		KEY_ROTATE = -1;
		KEY_LEFT = -1;
		KEY_RIGHT = -1;
		KEY_DOWN = -1;
		KEY_CHANGE_ITEM = -1;
		KEY_USE_ITEM = -1;
		isXboxController = false;
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
			isXboxController = false;
			break;

		case TWO:
			KEY_ROTATE = KeyEvent.VK_I;
			KEY_LEFT = KeyEvent.VK_J;
			KEY_RIGHT = KeyEvent.VK_L;
			KEY_DOWN = KeyEvent.VK_K;
			KEY_CHANGE_ITEM = KeyEvent.VK_U;
			KEY_USE_ITEM = KeyEvent.VK_O;
			isXboxController = false;
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	public static enum DEFAULT_KEYS{
		ONE,
		TWO
	}

	public float getKEY_ROTATE() {
		return KEY_ROTATE;
	}

	public void setKEY_ROTATE(float rotate) {
		KEY_ROTATE = rotate;
	}

	public float getKEY_LEFT() {
		return KEY_LEFT;
	}

	public void setKEY_LEFT(float left) {
		KEY_LEFT = left;
	}

	public float getKEY_RIGHT() {
		return KEY_RIGHT;
	}

	public void setKEY_RIGHT(float right) {
		KEY_RIGHT = right;
	}

	public float getKEY_DOWN() {
		return KEY_DOWN;
	}

	public void setKEY_DOWN(float down) {
		KEY_DOWN = down;
	}

	public float getKEY_CHANGE_ITEM() {
		return KEY_CHANGE_ITEM;
	}

	public void setKEY_CHANGE_ITEM(float change_item) {
		KEY_CHANGE_ITEM = change_item;
	}

	public float getKEY_USE_ITEM() {
		return KEY_USE_ITEM;
	}

	public void setKEY_USE_ITEM(float use_item) {
		KEY_USE_ITEM = use_item;
	}

	public boolean isXboxController() {
		return isXboxController;
	}

	public void setXboxController(boolean isXboxController) {
		this.isXboxController = isXboxController;
	}
	
	
}
