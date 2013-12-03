package org.MagicTetris.util;

import java.util.TimerTask;

import org.MagicTetris.GameItems.MagicBomb;
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
			switch (clearedLine) {
			case 3:
				clearedLine = 4;
				break;
			case 4:
				clearedLine = 8;
				break;
			default:
				break;
			}
			player.getStatusPanelModel().addItem(new MagicBomb());
			if (clearedLine >= 4) {
				
				System.out.println("Get bomb");
			}
			int currentScore = spm.getScore();
			spm.setScore(currentScore + 100 * clearedLine);
			if (bpm.getNextPiece() != null && bpm.getNextPieceColor() != null) {
				spm.setNextPiece(bpm.getNextPiece());
				spm.setNextPieceColor(bpm.getNextPieceColor());
			}
			sp.update();
			bp.repaint();
		}

		
	}


}
