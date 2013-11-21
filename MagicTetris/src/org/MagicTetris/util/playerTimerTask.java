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
	
	public playerTimerTask(BoardPanelModel B) {

		paleyBoard = B;
	}

	@Override
	public void run() {
		paleyBoard.moveCurrentPieceDown();	
		System.out.println("Tiemr Test");

		
	}


}
