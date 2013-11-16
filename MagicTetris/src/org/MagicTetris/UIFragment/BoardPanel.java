package org.MagicTetris.UIFragment;

import javax.swing.JPanel;

import org.MagicTetris.Models.BoardPanelModel;
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
	
	/**
	 * Draw a block on board.
	 */
	protected void drawBlock() {
		
	}

}
