package org.MagicTetris.GameItems;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
import org.MagicTetris.Models.patternModel;
import org.MagicTetris.UIFragment.BoardPanel;

public class MagicOdd extends MagicItem {

	private Integer[][] oddPiece;
	public MagicOdd() {
		super("Odd Block",
				new ImageIcon("res/odd.png"),
				MagicItemType.ENEMY_BOMB);
		super.effectTime = 3000;
		getOddPiece();
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {
		model.setNextPiece(oddPiece);
		
	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		model.setNextPiece(oddPiece);
		
	}

	@Override
	public void drawEffect(Graphics g) {
		g.setColor(Color.RED);
		Font font = new Font("Sans Serif", Font.BOLD, 32);
		g.setFont(font);
		g.drawString("Whattttttttt?!", 
				(BoardPanel.BLOCK_SIZE * BoardPanelModel.COLUMN_COUNT - g.getFontMetrics().stringWidth("Whattttttttt?!")) /2 ,
				40);
		
	}
	
	private void getOddPiece(){
		double random = Math.random();
		if (random > 0.5d) {
			oddPiece = (patternModel.patternOdd_2);
		}
		else {
			oddPiece = (patternModel.patternOdd_1);
		}
	}

}
