package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
/**
 * UI fragment. Showing a player's status.
 * Graphic effect from item use will be show on this.
 * @author Da
 *
 */
public class StatusPanel extends JPanel {
	private StatusPanelModel model;

	private static final String SPEED_STRING = "Speed: ";
	private static final String SCORE_STRING = "Score: ";
	private static final String BUFF_STRING = "Buff: ";
	private static final String DEBUFF_STRING = "Debuff: ";
	private static final String ITEMS_STRING = "Items: ";
	private static final String NEXTPIECE_STRING = "Next Piece: ";
	private JLabel lblSpeed;
	private JLabel lblScore;
	private JLabel lblBuff;
	private JLabel lblDebuff;
	private JLabel lblItems;
	private JLabel lblNextPiece;
	private ArrayList<JLabel> constantLabels;
	
	public StatusPanel() {
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Dimension d = new Dimension(300, 
				5 + BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE +5);
		this.setPreferredSize(d);
		this.setLayout(new GridBagLayout());		
		
		lblSpeed = new JLabel(SPEED_STRING);
		lblScore = new JLabel(SCORE_STRING);
		lblBuff = new JLabel(BUFF_STRING);
		lblDebuff = new JLabel(DEBUFF_STRING);
		lblItems = new JLabel(ITEMS_STRING);
		lblNextPiece = new JLabel(NEXTPIECE_STRING);
		constantLabels = new ArrayList<JLabel>();
		constantLabels.add(lblSpeed);
		constantLabels.add(lblScore);
		constantLabels.add(lblBuff);
		constantLabels.add(lblDebuff);
		constantLabels.add(lblItems);
		constantLabels.add(lblNextPiece);
		
		for (JLabel label : constantLabels) {
			label.setBackground(Color.BLACK);
			label.setForeground(Color.WHITE);
			this.add(label);
		}
	}
	public StatusPanelModel getModel() {
		return model;
	}

	public void setModel(StatusPanelModel model) {
		this.model = model;
	}
	
	

}
