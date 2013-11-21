package org.MagicTetris.util;

import java.util.TimerTask;

import org.MagicTetris.Models.BoardPanelModel;

/**
 * This class is to control the dropping speed of blocks.
 * 
 * @author Da
 *
 */
public class playerTimerTask extends TimerTask{

	private BoardPanelModel paleyBoard;
	/**
	 * Whether the Timer is running.
	 */
	private boolean paused;

	/**
	 * Create a paused Timer with dropSpeed = 1.
	 */
	public playerTimerTask(BoardPanelModel B) {
		paused = true;
		paleyBoard = B;
	}
	
	/**
	 * Create a paused Timer with given dropSpeed.
	 * @param dropSpeed the drop speed of block.
	 */
	public playerTimerTask(Float dropSpeed){
		paused = true;
	}


	@Override
	public void run() {
		paleyBoard.moveCurrentPieceDown();	
		System.out.println("Tiemr Test");
	}
	

}
