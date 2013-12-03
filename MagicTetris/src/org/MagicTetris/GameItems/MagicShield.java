package org.MagicTetris.GameItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.BoardPanel;

public class MagicShield extends MagicItem {

	public MagicShield() {
		super("Divine Shield",
				new ImageIcon("res/Shield.png"),
				MagicItemType.BUFF);
		super.effectTime = -1;
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {
		return;

	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		model.setBuff(this);

	}

	@Override
	public void drawEffect(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(5, 5);
		Color c = new Color(255,224,27,125);
		g2.setColor(c);
		g2.fillRect(0, 0, BoardPanelModel.COLUMN_COUNT * BoardPanel.BLOCK_SIZE+1, BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE+1);

	}

}
