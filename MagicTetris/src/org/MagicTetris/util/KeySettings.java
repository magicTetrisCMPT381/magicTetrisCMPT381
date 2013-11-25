package org.MagicTetris.util;

/**
 * This class represents the key settings.
 * Any -1 in the fields means that an error occurs and should be handled.
 * @author NewDa
 *
 */
public class KeySettings {
	
	public final int KEY_ROTATE;
	public final int KEY_LEFT;
	public final int KEY_RIGHT;
	public final int KEY_DOWN;
	public final int KEY_CHANGE_ITEM;
	public final int KEY_USE_ITEM;
	public KeySettings(int rotate, int left, int right,
			int down, int change_item, int use_item) {
		KEY_ROTATE = rotate;
		KEY_LEFT = left;
		KEY_RIGHT = right;
		KEY_DOWN = down;
		KEY_CHANGE_ITEM = change_item;
		KEY_USE_ITEM = use_item;
	}
	
	
}
