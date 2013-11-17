package org.MagicTetris.Models;

import java.awt.Graphics2D;

import org.MagicTetris.GameItems.MagicItem;

/**
 * Model for StatusPanel.
 * Show next block, score, speed, buff and debuff.
 * Receive the effect from item use.
 * @author Da
 *
 */
public class StatusPanelModel {
	
	private int score;
	private int speed;
	private MagicItem items[];
	private MagicItem buff;
	private MagicItem debuff;
	
	public StatusPanelModel()
	{
		score = 0;
		speed = 0;
		items = new MagicItem[3];
		buff = null;
		debuff = null;
	}
	
	public void addItem(MagicItem item) {
		 	for (int i = 0; i < items.length; i++) {
				if (items[i] == null) {
					items[i] = item;
				}
			}
		 	
	}

	public int getScore() {
		return score;
	}

	public int getSpeed() {
		return speed;
	}

	public MagicItem[] getItems() {
		return items;
	}

	public MagicItem getBuff() {
		return buff;
	}

	public MagicItem getDebuff() {
		return debuff;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setBuff(MagicItem buff) {
		this.buff = buff;
	}

	public void setDebuff(MagicItem debuff) {
		this.debuff = debuff;
	}

	public void clearItem() {
		for (int i = 0; i < items.length; i++) {
			items[i] = null;
		}
	}
	
	

}
