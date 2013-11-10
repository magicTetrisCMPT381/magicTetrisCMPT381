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
	 * @author Da
	 *
	 */
	private class SingleBlock{
		/**
		 * Is the point occupied by any block.
		 */
		private boolean isOccupied;
		/**
		 * Is the point frozen( could not be cleared ).
		 */
		private boolean isFrozen;
		
		protected boolean isOccupied() {
			return isOccupied;
		}
		protected void setOccupied(boolean isOccupied) {
			this.isOccupied = isOccupied;
		}
		protected boolean isFrozen() {
			return isFrozen;
		}
		protected void setFrozen(boolean isFrozen) {
			this.isFrozen = isFrozen;
		}
	}
}
