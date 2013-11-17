package org.MagicTetris.Models;

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
	 * Current rotate of moving piece in the board.
	 */
	private int currentPieceRotate;

	/**
	 * The coordinate of current piece: by row and column of top-left.
	 */
	private int[][] currentPieceCoord;
	
	public BoardPanelModel() {
		random = new Random();
		board = new SingleBlock[TOTAL_ROW_COUNT][COLUMN_COUNT];
	}
	/**
	 * Represents the status of each block in the board.
	 * If a block is frozen,
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
		 * @param isOccupied
		 */
		public void setFrozen(boolean isFrozen) {
			this.isFrozen = isFrozen;
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
			} else {
				isOccupied=false;
			}
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
			return patternModel.patternJ;
		case 2:
			return patternModel.patternL;
		case 3:
			return patternModel.patternO;
		case 4:
			return patternModel.patternS;
		case 5:
			return patternModel.patternT;
		case 6:
			return patternModel.patternZ;
		default:
			return patternModel.patternI;
		}
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

	public int[][] getCurrentPieceCoord() {
		return currentPieceCoord;
	}
}
