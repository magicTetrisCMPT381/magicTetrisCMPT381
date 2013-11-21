package org.MagicTetris.util;

import java.util.TimerTask;

/**
 * This class is to control the dropping speed of blocks.
 * 
 * @author Da
 *
 */
public class playerTimerTask extends TimerTask{
	/**
	 * The drop speed of blocks. Defined to x row/second.
	 */
	private float dropSpeed;
	/**
	 * Whether the Timer is running.
	 */
	private boolean paused;

	/**
	 * Create a paused Timer with dropSpeed = 1.
	 */
	public playerTimerTask() {
		dropSpeed = 1;
		paused = true;
	}
	
	/**
	 * Create a paused Timer with given dropSpeed.
	 * @param dropSpeed the drop speed of block.
	 */
	public playerTimerTask(Float dropSpeed){
		this.dropSpeed = dropSpeed;
		paused = true;
	}

	/**
	 * Return the drop speed associated with the Timer.
	 * @return the drop speed
	 */
	public float getDropSpeed() {
		return dropSpeed;
	}

	/**
	 * Set the drop speed of this Timer.
	 * @param dropSpeed
	 */
	public void setDropSpeed(float dropSpeed) {
		this.dropSpeed = dropSpeed;
	}

	/**
	 * Is the Timer paused.
	 * @return true if the timer is paused.
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * Pause or start the Timer.
	 * @param paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
