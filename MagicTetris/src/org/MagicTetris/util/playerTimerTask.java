package org.MagicTetris.util;

import java.util.Random;
import java.util.TimerTask;

import org.MagicTetris.GameItems.MagicAtom;
import org.MagicTetris.GameItems.MagicCleaner;
import org.MagicTetris.GameItems.MagicFlash;
import org.MagicTetris.GameItems.MagicFreezer;
import org.MagicTetris.GameItems.MagicOdd;
import org.MagicTetris.GameItems.MagicShield;
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
			int speedModifier = (int) ((player.getSpeed() - 1.0f) / 0.2f);
			if (clearedLine == 0) {
				speedModifier = 0;
			}
			int oldScore = spm.getScore();
			spm.setScore(oldScore + 100 * clearedLine + 100 * speedModifier);
			int currentScore = spm.getScore();
			if (clearedLine > 0) {
				player.setSpeed(1+currentScore/1000*0.2f);
			}
			
			if (clearedLine >= 4) {
				int randomItem = new Random().nextInt(7);
				switch (randomItem) {
				case 0:
					player.addItem(new MagicAtom());
					break;
				case 1:
					player.addItem(new MagicCleaner());
					break;
				case 2:
					player.addItem(new MagicFlash());
					break;
				case 3:
					player.addItem(new MagicFreezer());
					break;
				case 4:
					player.addItem(new MagicOdd());
					break;
				case 5:
					player.addItem(new MagicShield());
					break;
				default:
					break;
				}
			}
			if (bpm.getNextPiece() != null && bpm.getNextPieceColor() != null) {
				spm.setNextPiece(bpm.getNextPiece());
				spm.setNextPieceColor(bpm.getNextPieceColor());
			}
			sp.update();
			bp.repaint();
		}

		
	}


}
