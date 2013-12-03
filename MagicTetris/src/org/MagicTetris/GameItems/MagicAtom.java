package org.MagicTetris.GameItems;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
import org.MagicTetris.UIFragment.BoardPanel;

public class MagicAtom extends MagicItem {

	public MagicAtom() {
		super("Nuclear Bomb",
				new ImageIcon("res/nuclear.png"),
				MagicItemType.BOMB);
		super.effectTime = 3000;
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {
		SingleBlock[][] board = model.getBoard();
		for (SingleBlock[] line : board) {
			for (SingleBlock block : line) {
				block.reset();
			}
		}
		
	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		return;
		
	}

	@Override
	public void drawEffect(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(5, 5);
		Color c = new Color(87,27,27,175);
		g2.setColor(c);
		g2.fillRect(0, 0, BoardPanelModel.COLUMN_COUNT * BoardPanel.BLOCK_SIZE+1, BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE+1);
		
	}

}
