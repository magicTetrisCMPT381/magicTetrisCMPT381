package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.plaf.LayerUI;

import org.MagicTetris.GameItems.MagicBomb;
import org.MagicTetris.GameItems.MagicItem;

public class EffectLayer extends LayerUI<JComponent> {
	private MagicItem item;
	@Override
	public void paint(Graphics g, JComponent c){
		super.paint(g, c);
		if (item == null) {
			return;
		}
		Graphics layerGraphics = g.create();
		item.drawEffect(layerGraphics);
		layerGraphics.dispose();
	}
	public MagicItem getItem() {
		return item;
	}
	public void setItem(MagicItem item) {
		this.item = item;
	}
}
