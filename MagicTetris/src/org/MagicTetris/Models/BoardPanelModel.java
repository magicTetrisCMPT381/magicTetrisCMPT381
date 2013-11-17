package org.MagicTetris.Models;
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
	
	private SingleBlock[][] board = new SingleBlock[TOTAL_ROW_COUNT][COLUMN_COUNT];
	
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

	public SingleBlock[][] getBoard() {
		return board;
	}
}
