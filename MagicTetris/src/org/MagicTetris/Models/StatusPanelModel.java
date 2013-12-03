package org.MagicTetris.Models;

import java.awt.Color;


import org.MagicTetris.GameItems.MagicItem;

/**
 * Model for StatusPanel.
 * Show next block, score, speed, buff and debuff.
 * Receive the effect from item use.
 * @author Da
 *
 */
public class StatusPanelModel {
	/**
	 * Player's score.
	 */
	private int score;
	
	/**
	 * The speed of block dropping.
	 */
	private float speed;
	
	/**
	 * Player's items.
	 */
	private MagicItem items[];
	
	/**
	 * Player's buff
	 */
	private MagicItem buff;
	
	/**
	 * Player's debuff
	 */
	private MagicItem debuff;
	
	/**
	 * The next piece.
	 */
	private Integer[][] nextPiece;
	
	private Color nextPieceColor;
	
	public StatusPanelModel()
	{
		score = 0;
		speed = 0;
		items = new MagicItem[5];
		buff = null;
		debuff = null;
		nextPiece = null;
		nextPieceColor = Color.BLACK;
	}
	
	/**
	 * Add an new MagicItem.
	 * If items is full, then kick out the first and add the new item as the third.
	 * @param item
	 */
	public void addItem(MagicItem item) {
		// add the item if there is an empty slot for it. 
	 	for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = item;
				return;
			}
		}
		// if not, then kick out the first item and add the new item as the third one.
	 	items[0] = items[1];
	 	items[1] = items[2];
	 	items[2] = items[3];
	 	items[3] = items[4];
	 	items[4] = item;
	}

	public MagicItem useItem(){
		MagicItem item = items[0];
		items[0] = items[1];
	 	items[1] = items[2];
	 	items[2] = items[3];
	 	items[3] = items[4];
	 	items[4] = null;
		return item;
	}
	
	public void changeItem(){
		MagicItem tempItem = items[0];
		items[0] = items[1];
	 	items[1] = items[2];
	 	items[2] = items[3];
	 	items[3] = items[4];
	 	items[4] = tempItem;
	}

	public void clearItem() {
		for (int i = 0; i < items.length; i++) {
			items[i] = null;
		}
	}

	public int getScore() {
		return score;
	}

	public float getSpeed() {
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

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setBuff(MagicItem buff) {
		this.buff = buff;
	}

	public void setDebuff(MagicItem debuff) {
		this.debuff = debuff;
	}

	public Integer[][] getNextPiece() {
		return nextPiece;
	}

	public void setNextPiece(Integer[][] nextPiece) {
		this.nextPiece = nextPiece;
	}
	
	public Color getNextPieceColor() {
		return nextPieceColor;
	}

	public void setNextPieceColor(Color nextPieceColor) {
		this.nextPieceColor = nextPieceColor;
	}

}
