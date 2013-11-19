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
	}

	@Override
	protected void tearDown() throws Exception {
		bpm = null;
	}

}
