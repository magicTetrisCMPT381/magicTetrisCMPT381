package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
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
	 * Draw a block on board.
	 * WARNING: prototype method. 
	 * The real method should draw block by reading SingleBlock block[][] = model.getBoard().
	 */
	protected void drawBlock(Graphics g) {
//		SingleBlock block[][] = model.getBoard();
//		if (block != null) {
			for (int x = 0; x < model.COLUMN_COUNT; x++) {
				for (int y = 0; y < model.VISIBLE_ROW_COUNT; y++) {
//					if (block[x][y] != null) {
//						
//					}
					g.setColor(Color.RED);
					g.fillRect(x * BLOCK_SIZE, 
							y * BLOCK_SIZE, 
							BLOCK_SIZE, BLOCK_SIZE);
					g.setColor(Color.RED.darker());
					g.fillRect(x * BLOCK_SIZE + BLOCK_SHADOW, 
							y * BLOCK_SIZE + BLOCK_SHADOW, 
							BLOCK_SIZE - 2 * BLOCK_SHADOW, BLOCK_SIZE - 2 * BLOCK_SHADOW);
				}
			}
//		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.translate(5, 5);
		drawBlock(g);
		drawGrid(g);
		
	}

}
