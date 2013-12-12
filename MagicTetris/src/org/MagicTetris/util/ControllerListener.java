package org.MagicTetris.util;

public interface ControllerListener {
	
	/**
	 * Fired when HatSwitch changed.
	 * @see JInput
	 * @param HatPosition 
	 */
	public void HatSwitchChanged(float HatPosition);
	
	/**
	 * Fired when a button is pressed.
	 */
	public void ButtonPressed(int ButtonNum);
	
	/**
	 * Fired when a button is released.
	 */
	public void ButtonReleased(int ButtonNum);
	
}
