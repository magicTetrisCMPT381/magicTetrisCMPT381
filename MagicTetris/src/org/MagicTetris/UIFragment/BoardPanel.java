package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
	
	private final String WELCOME_STR = "Welcome!";
	
	public BoardPanel() {
		this.setBackground(Color.BLACK);
		Dimension d = new Dimension(5 + BoardPanelModel.COLUMN_COUNT * BLOCK_SIZE + 5, 
									5 + BoardPanelModel.VISIBLE_ROW_COUNT * BLOCK_SIZE +5);
		this.setPreferredSize(d);
	}
	
	/**
	 * Draw grid on board.
	 * @param g
	 */
	protected void drawGrid(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x <= BoardPanelModel.COLUMN_COUNT; x++) {
			for(int y = 0; y <= BoardPanelModel.VISIBLE_ROW_COUNT; y++) {
				g.drawLine(0, y * BLOCK_SIZE, BoardPanelModel.COLUMN_COUNT * BLOCK_SIZE, y * BLOCK_SIZE);
				g.drawLine(x * BLOCK_SIZE, 0, x * BLOCK_SIZE, BoardPanelModel.VISIBLE_ROW_COUNT * BLOCK_SIZE);
			}
		}
	}
	/**
	 * Draw a block on board.
	 * @param row the block's row.
	 * @param col the block's column.
	 * @param g the surface to draw on.
	 */
	protected void drawBlock(Color blockColor,int row, int col, Graphics g) {
		g.setColor(blockColor.darker());
		g.fillRect(col * BLOCK_SIZE, 
					row * BLOCK_SIZE, 
					BLOCK_SIZE, BLOCK_SIZE);
		g.setColor(blockColor);
		g.fillRect(col * BLOCK_SIZE + BLOCK_SHADOW, 
					row * BLOCK_SIZE + BLOCK_SHADOW, 
					BLOCK_SIZE - 2 * BLOCK_SHADOW, BLOCK_SIZE - 2 * BLOCK_SHADOW);
	}
	
	/**
	 * Draw a piece on board.
	 * The column and row is the pattern's top-left corner.
	 * @param pattern the piece's pattern.
	 * @param patternColor the piece's color.
	 * @param rotate the piece's rotate.
	 * @param row the piece's row.
	 * @param col the piece's column.
	 * @param g the surface to draw on.
	 */
	protected void drawPiece(Integer[][] pattern, Color patternColor,int rotate, int row, int col, Graphics g) {
		
		for (int patternCol = 0; patternCol < 4; patternCol++) {
			for (int patternRow = 0; patternRow < 4; patternRow++) {
				if (pattern[rotate][patternRow*4 + patternCol] == 1 &&
						(row + patternRow) >= BoardPanelModel.HIDDEN_ROW_COUNT) {
					System.out.println("Draw on: row " + (row + patternRow - BoardPanelModel.HIDDEN_ROW_COUNT) + 
							" col: " + (col + patternCol));
					drawBlock(patternColor, 
							row + patternRow - BoardPanelModel.HIDDEN_ROW_COUNT, 
							col + patternCol, g);
				}
			}
		}

	}
	
	/**
	 * Draw the board.
	 * @param g the surface to draw on.
	 */
	protected void drawBoard(Graphics g){
		if (this.model != null) {
			SingleBlock[][] board = model.getBoard();
			for (int row = BoardPanelModel.HIDDEN_ROW_COUNT; row < BoardPanelModel.TOTAL_ROW_COUNT; row++) {
				for (int col = 0; col < BoardPanelModel.COLUMN_COUNT; col++) {
					drawBlock(board[row][col].getColor(), row, col, g);
				}
			}
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// if we do not have a model...
		if (model == null) {
			g.setColor(Color.RED);
			g.drawString(WELCOME_STR, 
					this.getWidth() / 2 - g.getFontMetrics().stringWidth(WELCOME_STR) / 2, 
					this.getHeight() / 2);
		}
		else {
			// To show the grid, we need change a little
			g.translate(5, 5);
			
			drawBoard(g);
			
			if (model.getCurrentPiece() != null) {
				drawPiece(model.getCurrentPiece(),
						model.getCurrentPieceColor(),
						model.getCurrentPieceRotate(),
						model.getCurrentPieceRow(),
						model.getCurrentPieceCol(), g);
			}
			
			drawGrid(g);
		}

		
	}

	public BoardPanelModel getModel() {
		return model;
	}

	public void setModel(BoardPanelModel model) {
		this.model = model;
	}

}
