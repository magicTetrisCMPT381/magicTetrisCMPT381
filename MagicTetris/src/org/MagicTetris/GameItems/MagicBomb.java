package org.MagicTetris.GameItems;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;

public class MagicBomb extends MagicItem {

	public MagicBomb(String name, ImageIcon icon, MagicItemType type) {
		super(name, icon, type);
	}

	@Override
	public void changeBoardModel(BoardPanelModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changeStatusModel(StatusPanelModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawEffect(Graphics g) {
		// TODO Auto-generated method stub

	}

}
