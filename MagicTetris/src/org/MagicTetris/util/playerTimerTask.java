package org.MagicTetris.util;

import java.util.TimerTask;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.Player;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.BoardPanel;
import org.MagicTetris.UIFragment.StatusPanel;

/**
 * This class is to control the dropping speed of blocks.
 * 
 * @author Da
 *
 */
public class playerTimerTask extends TimerTask{
	private BoardPanelModel bpm;
	private BoardPanel bp;
	private StatusPanelModel spm;
	private StatusPanel sp;
	private Player player;

	
	public playerTimerTask(Player p) {
		player = p;
		bp = p.getBoardPanel();
		sp = p.getStatusPanel();
		bpm = p.getBoardPanelModel();
		spm = p.getStatusPanelModel();
	}

	@Override
	public void run() {
		bpm.moveCurrentPieceDown();
		if (!bpm.isGameOver()) {
			int clearedLine = bpm.clearLines();
			int currentScore = spm.getScore();
			spm.setScore(currentScore + 100 * clearedLine);
			sp.update();
			bp.repaint();
		}

		
	}


}
