package org.MagicTetris.Models;

import java.awt.Color;
import java.util.Random;

/**
 * Model for BoardPanel.
 * Stores panel data and receive the effect from item use.
 * @author Da
 *
 */
public class BoardPanelModel {

	/**
	 * The number of columns on the board.
	 */
	public final int COLUMN_COUNT = 12;
	/**
	 * The number of visible rows on the board.
	 */
	public final int VISIBLE_ROW_COUNT = 24;
	/**
	 * The number of hidden rows on the board.
	 * For pre-draw blocks.
	 */
	public final int HIDDEN_ROW_COUNT = 2;
	/**
	 * Total rows on the board.
	 */
	public final int TOTAL_ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	
	/**
	 * Random number creater.
	 */
	private Random random;
	/**
	 * The board.
	 */
	private SingleBlock[][] board;
	
	/**
	 * Current moving piece in the board.
	 */
	private Integer[][] currentPiece;
	
	/**
	 * The next piece.
	 */
	private Integer[][] nextPiece;
	
	/**
	 * Current rotate of moving piece in the board.
	 */
	private int currentPieceRotate;

	/**
	 * The coordinate of current piece: by row and column of top-left.
	 * the first is x, the second is y.
	 */
	private int[] currentPieceCoord = {-1,-1};
	
	public BoardPanelModel() {
		random = new Random();
		board = new SingleBlock[TOTAL_ROW_COUNT][COLUMN_COUNT];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new SingleBlock();
			}
		}
	}
	/**
	 * Represents the status of each block in the board.
	 * If a block is frozen, it must be cleared twice to be fully cleared
	 * @author Da
	 *
	 */
	public class SingleBlock{
		/**
		 * Is the point occupied by any block.
		 */
		private boolean isOccupied;
		/**
		 * Is the point frozen( could not be cleared in one time ).
		 */
		private boolean isFrozen;
		
		/**
		 * The block's color.
		 */
		private Color color;
		
		public SingleBlock() {
			isOccupied = false;
			isFrozen = false;
			color = Color.BLACK;
		}
		/**
		 * Return whether this block is occupied.
		 * @return true if this block is occupied.
		 */
		public boolean isOccupied() {
			return isOccupied;
		}
		/**
		 * Set the occupying status of this block.
		 * @param isOccupied
		 */
		public void setOccupied(boolean isOccupied) {
			this.isOccupied = isOccupied;
		}
		/**
		 * Return whether this block is frozen.
		 * A frozen block must be cleared twice to be unoccupied.
		 * @return true if this block is frozen.
		 */
		public boolean isFrozen() {
			return isFrozen;
		}
		/**
		 * Set the frozen status of this block.
		 * A frozen block will be in light blue.
		 * @param isOccupied
		 */
		public void setFrozen(boolean isFrozen) {
			this.isFrozen = isFrozen;
			this.color = Color.BLUE.brighter();
		}
		/**
		 * Reset this block to unoccupied and not frozen.
		 */
		public void reset() {
			this.isFrozen=false;
			this.isOccupied=false;
		}
		/**
		 * Clear this block.
		 * A frozen block must be cleared twice to be unoccupied.
		 */
		public void clear() {
			if (isFrozen) {
				isFrozen=false;
				color = Color.GRAY;
			} else {
				isOccupied=false;
			}
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
	}

	/**
	 * Get an array representing the board
	 * @return an array representing the board
	 */
	public SingleBlock[][] getBoard() {
		return board;
	}
	
	/**
	 * Get the next piece.
	 * @return the next piece.
	 */
	public Integer[][] getNextPiece(){
		int next = random.nextInt(7);
		switch (next) {
		case 1:
			this.nextPiece = patternModel.patternJ;
			return nextPiece;
		case 2:
			this.nextPiece = patternModel.patternL;
			return nextPiece;
		case 3:
			this.nextPiece = patternModel.patternO;
			return nextPiece;
		case 4:
			this.nextPiece = patternModel.patternS;
			return nextPiece;
		case 5:
			this.nextPiece = patternModel.patternT;
			return nextPiece;
		case 6:
			this.nextPiece = patternModel.patternZ;
			return nextPiece;
		default:
			this.nextPiece = patternModel.patternI;
			return nextPiece;
		}
	}

	public void setNextPiece(Integer[][] nextPiece) {
		this.nextPiece = nextPiece;
	}

	/**
	 * Get the current piece.
	 * @return the current piece.
	 */
	public Integer[][] getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * Get the rotate of current piece.
	 * @return the rotate of current piece.
	 */
	public int getCurrentPieceRotate() {
		return currentPieceRotate;
	}

	/**
	 * Rotate current piece.
	 * WARNING: SHOULD CHECK WHETHER POSSIBLE TO ROTATE.
	 */
	public void rotateCurrentPiece(){
		// The piece only has four rotate statuses.
		currentPieceRotate = (currentPieceRotate +1) % 4;
	}
	public int[] getCurrentPieceCoord() {
		return currentPieceCoord;
	}
	
	public void moveCurrentPieceLeft() {
		if (currentPieceCoord[0] > 0) {
			currentPieceCoord[0] -= 1;
		}
		
	}
	
	public void moveCurrentPieceRight() {
		if (currentPieceCoord[0] < COLUMN_COUNT) {
			currentPieceCoord[0] += 1;
		}
	}
	
	// TODO: Check can this block drop.
	public void moveCurrentPieceDown(){
		currentPieceCoord[1] += 1;
	}
	
	/**
	 * Check if a piece could be placed at designated position.
	 * The designated position is represented by the piece's top-left corner.
	 * @param piece the piece.
	 * @param col the column to put the piece
	 * @param row the row to put the piece
	 * @param rotate the rotate status of the piece.
	 * @return
	 */
	public boolean checkPosition(Integer[][] piece, int col, int row, int rotate) {
		if (board[row][col].isOccupied) {
			return false;
		}
		// There should be 4 coordinates to check.
		int[][] coordToCheck = new int[4][2];
		int coordGroupNo = 0;
		for (int i = 0; i < 16; i++) {
			if (piece[rotate][i] == 1) {
				// col + i / 4 is column number; row + i % 4 is row number.
				coordToCheck[coordGroupNo][0] = row + i % 4;
				coordToCheck[coordGroupNo][1] = col + i / 4;
				coordGroupNo++;
			}
		}
		
		for (int[] is : coordToCheck) {
			if (board[is[0]][is[1]].isOccupied) {
				return false;
			}
		}
		return true;
		
	}
}
