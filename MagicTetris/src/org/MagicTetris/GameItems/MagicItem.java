/**
 * 
 */
package org.MagicTetris.GameItems;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;

/**
 * This class is the base class of all items.
 * All items should extend this class to create a specific item.
 * 
 *
 */
public abstract class MagicItem {
	/**
	 * the item's name
	 */
	public final String itemName;
	/**
	 * the item's icon
	 */
	public final ImageIcon itemIcon;
	
	/**
	 * the visual effect duration.
	 */
	protected long effectTime;
	
	public final MagicItemType itemType;

	/**
	 * Create a MagicItem.
	 * This constructor is used to set the name and icon of the MagicItem.
	 * @param name the item's name
	 * @param icon the item's icon
	 */
	public MagicItem(String name, ImageIcon icon, MagicItemType type){
		itemName = name;
		itemIcon = icon;
		itemType = type;
	}
	
	/**
	 * Change a {@link BoardPanelModel}.
	 * This method should be override by subclass to change the {@link BoardPanelModel} to show a MagicItem's effect.
	 * @param model the {@link BoardPanelModel} to be changed.
	 */
	public abstract void changeBoardModel(BoardPanelModel model);
	
	/**
	 * Change a {@link StatusPanelModel}.
	 * This method should be override by subclass to change the {@link StatusPanelModel} to show a MagicItem's effect.
	 * @param model the {@link StatusPanelModel} to be changed.
	 */
	public abstract void changeStatusModel(StatusPanelModel model);
	
	/**
	 * This method will draw visual effect for a magic item.
	 * This method should be override by subclass to show a MagicItem's effect.
	 * @param g The surface to draw on.
	 */
	public abstract void drawEffect(Graphics g);

	public long getEffectTime() {
		return effectTime;
	}
}
