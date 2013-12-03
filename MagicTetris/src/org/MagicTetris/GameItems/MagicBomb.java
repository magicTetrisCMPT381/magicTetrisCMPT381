package org.MagicTetris.GameItems;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.UIFragment.BoardPanel;

public class MagicBomb extends MagicItem {

	public MagicBomb() {
		super("Small Bomb",
				new ImageIcon("res/bomb.png"),
				MagicItemType.BOMB);
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {
		SingleBlock[][] board = model.getBoard();
		// Check each column and row. Reset the first occupied block to unoccupied. 
		for (int col = 0; col < BoardPanelModel.COLUMN_COUNT; col++) {
			for (int row = 0; row < BoardPanelModel.TOTAL_ROW_COUNT; row++) {
				if (board[row][col].isOccupied()) {
					board[row][col].reset();
					break;
				}
			}
		}

	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		return;

	}

	@Override
	public synchronized void drawEffect(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.translate(5, 5);
		Color c = new Color(Color.RED.getRed(),Color.RED.getGreen(),Color.RED.getBlue(),50);
		g2.setColor(c);
		
		g2.fillRect(0, 0, BoardPanelModel.COLUMN_COUNT * BoardPanel.BLOCK_SIZE+1, BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE+1);

	}

}
