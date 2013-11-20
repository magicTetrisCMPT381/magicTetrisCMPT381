package org.MagicTetris.UIFragment;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.MagicTetris.Models.StatusPanelModel;
/**
 * UI fragment. Showing a player's status.
 * Graphic effect from item use will be show on this.
 * @author Da
 *
 */
public class StatusPanel extends JPanel {
	private StatusPanelModel model;

	public StatusPanel() {
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	public StatusPanelModel getModel() {
		return model;
	}

	public void setModel(StatusPanelModel model) {
		this.model = model;
	}

}
