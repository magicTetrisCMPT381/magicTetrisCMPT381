package org.MagicTetris.test;


import junit.framework.TestCase;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.BoardPanelModel.SingleBlock;
import org.MagicTetris.Models.patternModel;



public class TesterBoardPanelModel extends TestCase {

	BoardPanelModel bpm;
	
	@Override
	protected void setUp() {
		bpm = new BoardPanelModel();
	}
	
	public void testcheckPosition() {
		assertTrue("Pattern I should be able to be placed at 0,0 .", bpm.checkPosition(patternModel.patternI, 0, 0, 0));
		SingleBlock block = bpm.getBoard()[1][1];
		block.setOccupied(true);
		assertFalse("Pattern I should not be able to be placed at 0,0 .", bpm.checkPosition(patternModel.patternI, 0, 0, 0));
		block.reset();
		assertFalse("Pattern I should not be able to be placed at 26,12 .", bpm.checkPosition(patternModel.patternI, 12, 26, 0));
		
	}
	
	public void testclearLines(){
		SingleBlock[][] board = bpm.getBoard();
		// Froze each line.
		for (SingleBlock[] lines : board) {
			for (SingleBlock block : lines) {
				block.setFrozen(true);
			}
		}
		
		// Try to clear lines. No lines are supposed to be cleared, since all are frozen.
		assertEquals(0, bpm.clearLines());
		// Try to clear lines. All lines are supposed to be cleared, since all are not frozen.
		assertEquals(26, bpm.clearLines());
		
		for (int y = 0; y < bpm.TOTAL_ROW_COUNT; y++) {
			for (int x = 0; x < bpm.COLUMN_COUNT; x++) {
				assertFalse(board[y][x].isOccupied());
			}
		}
		// Set some blocks in different lines occupied.
		(board[10][2]).setOccupied(true);
		(board[5][2]).setOccupied(true);
		(board[7][3]).setOccupied(true);
		(board[8][4]).setOccupied(true);
		

		// Try to clear lines. No lines are supposed to be cleared, since no lines are fully occupied.
		assertEquals(0, bpm.clearLines());
		
		// Verify the occupied blocks are not cleared.
		assertTrue("Block 2,10 should not be cleared.", board[10][2].isOccupied());
		assertTrue("Block 2,5 should not be cleared.", board[5][2].isOccupied());
		assertTrue("Block 3,7 should not be cleared.", board[7][3].isOccupied());
		assertTrue("Block 4,8 should not be cleared.", board[8][4].isOccupied());
		
		// Set a line is occupied.
		for (int x = 0; x < bpm.COLUMN_COUNT; x++) {
			board[10][x].setOccupied(true);
		}

		// Try to clear lines. One line is supposed to be cleared.
		assertEquals(1, bpm.clearLines());
		// Verify the other occupied blocks are not cleared.
		assertTrue("Block 2,6 should not be cleared.", board[6][2].isOccupied());
		assertTrue("Block 3,8 should not be cleared.", board[8][3].isOccupied());
		assertTrue("Block 4,9 should not be cleared.", board[9][4].isOccupied());
	}

	@Override
	protected void tearDown() throws Exception {
		bpm = null;
	}

}
