package org.MagicTetris.util;

import java.util.TimerTask;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.UIFragment.BoardPanel;

/**
 * This class is to control the dropping speed of blocks.
 * 
 * @author Da
 *
 */
public class playerTimerTask extends TimerTask{
	private BoardPanelModel model;
	private BoardPanel panel;

	
	public playerTimerTask(BoardPanel p, BoardPanelModel m) {
		panel = p;
		model = m;
	}

	@Override
	public void run() {
		model.moveCurrentPieceDown();
		model.clearLines();
		panel.repaint();

		
	}


}
