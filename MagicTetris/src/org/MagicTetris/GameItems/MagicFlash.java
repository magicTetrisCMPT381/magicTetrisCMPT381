package org.MagicTetris.GameItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.BoardPanel;

public class MagicFlash extends MagicItem {

	private int alphaRatio;
	private Timer fadeTimer;
	private boolean isStartFading;
	public MagicFlash() {
		super("Flash bang",
				new ImageIcon("res/Flash_bang.png"),
				MagicItemType.DEBUFF);
		fadeTimer = new Timer(true);
		isStartFading = false;
		alphaRatio = 100;
		super.effectTime = 9000;
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {


	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		model.setDebuff(this);

	}

	@Override
	public void drawEffect(Graphics g) {
		if (!isStartFading) {
			fadeTimer.schedule(new fadeTask(), 4000, 50);
			isStartFading = true;
		}
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(5, 5);
		int alpha = (int) (255 * (alphaRatio / 100.0f)) < 0 ? 0 : (int) (255 * (alphaRatio / 100.0f));
		Color c = new Color(255,255,255, alpha);
//		System.out.println((int) (255 * (alphaRatio / 100.0f)));
		g2.setColor(c);
		g2.fillRect(0, 0, BoardPanelModel.COLUMN_COUNT * BoardPanel.BLOCK_SIZE+1, BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE+1);

	}
	
	private class fadeTask extends TimerTask{

		@Override
		public void run() {
			alphaRatio -=1;
			
		}
		
	}

}
