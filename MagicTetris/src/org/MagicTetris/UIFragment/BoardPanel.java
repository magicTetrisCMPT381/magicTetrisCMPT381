package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
import org.MagicTetris.Models.patternModel;
/**
 * UI fragment. Showing a player's board.
 * Graphic effect from item use will be show on this.
 * @author Da
 *
 */
public class BoardPanel extends JPanel {
	/**
	 * The model associated with this board.
	 */
	private BoardPanelModel model;
	/**
	 * The size of a block.
	 */
	private final int BLOCK_BASE = 24;
	/**
	 * The shadow width of a block
	 */
	private final int BLOCK_SHADOW = 4;
	
	private final int BLOCK_SIZE = BLOCK_SHADOW + BLOCK_BASE;
	
	public BoardPanel() {
		model = new BoardPanelModel();
		this.setBackground(Color.BLACK);
		Dimension d = new Dimension(5 + model.COLUMN_COUNT * BLOCK_SIZE + 5, 
									5 + model.VISIBLE_ROW_COUNT * BLOCK_SIZE +5);
		this.setPreferredSize(d);
	}
	
	/**
	 * Draw grid on board.
	 * @param g
	 */
	protected void drawGrid(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x <= model.COLUMN_COUNT; x++) {
			for(int y = 0; y <= model.VISIBLE_ROW_COUNT; y++) {
				g.drawLine(0, y * BLOCK_SIZE, model.COLUMN_COUNT * BLOCK_SIZE, y * BLOCK_SIZE);
				g.drawLine(x * BLOCK_SIZE, 0, x * BLOCK_SIZE, model.VISIBLE_ROW_COUNT * BLOCK_SIZE);
			}
		}
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @param g
	 */
	protected void drawBlock(Color blockColor,int row, int col, Graphics g) {
		g.setColor(blockColor.darker());
		g.fillRect(row * BLOCK_SIZE, 
					col * BLOCK_SIZE, 
					BLOCK_SIZE, BLOCK_SIZE);
		g.setColor(blockColor);
		g.fillRect(row * BLOCK_SIZE + BLOCK_SHADOW, 
					col * BLOCK_SIZE + BLOCK_SHADOW, 
					BLOCK_SIZE - 2 * BLOCK_SHADOW, BLOCK_SIZE - 2 * BLOCK_SHADOW);
	}
	
	protected void drawPattern(Integer[][] pattern, Color patternColor,int rotate, int row, int col, Graphics g) {
		
		for (int patternCol = 0; patternCol < 4; patternCol++) {
			for (int patternRow = 0; patternRow < 4; patternRow++) {
				if (pattern[rotate][patternCol * 4 + patternRow] == 1) {
					drawBlock(patternColor, row + patternRow, col + patternCol, g);
				}
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(5, 5);
//		drawPattern(patternModel.patternT, patternModel.colorT,3 , 0, 0, g);
		drawGrid(g);
		
	}

}
