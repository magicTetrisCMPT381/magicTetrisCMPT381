package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.MagicTetris.GameItems.MagicItem;
import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.Models.StatusPanelModel;
/**
 * UI fragment. Showing a player's status.
 * Graphic effect from item use will be show on this.
 * @author Da
 *
 */
@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
	private StatusPanelModel model;

	// Strings for status labels
	private static final String SPEED_STRING = "Speed: ";
	private static final String SCORE_STRING = "Score: ";
	private static final String BUFF_STRING = "Buff: ";
	private static final String DEBUFF_STRING = "Debuff: ";
	private static final String ITEMS_STRING = "Items: ";
	private static final String NEXTPIECE_STRING = "Next Piece: ";
	// The status name labels
	private JLabel lblSpeed;
	private JLabel lblScore;
	private JLabel lblBuff;
	private JLabel lblDebuff;
	private JLabel lblItems;
	private JLabel lblNextPiece;
	
	// The status labels
	private JLabel lblPlayerScore;
	private JLabel lblPlayerSpeed;
	private JLabel lblPlayerBuff;
	private JLabel lblPlayerDebuff;
	private JLabel[] lblPlayerItems;
	private JLabel lblPlayerNextPiece;
	
	private ArrayList<JLabel> constantLables;
	
	public StatusPanel() {
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Dimension d = new Dimension(170, 
				BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE);
		this.setPreferredSize(d);
		this.setLayout(new GridBagLayout());		
		
		constantLables = new ArrayList<JLabel>();
		
		lblSpeed = new JLabel(SPEED_STRING);
		lblScore = new JLabel(SCORE_STRING);
		lblBuff = new JLabel(BUFF_STRING);
		lblDebuff = new JLabel(DEBUFF_STRING);
		lblItems = new JLabel(ITEMS_STRING);
		lblNextPiece = new JLabel(NEXTPIECE_STRING);
		lblPlayerScore = new JLabel("000000");
		lblPlayerSpeed = new JLabel("000000");
		lblPlayerBuff = new JLabel(new ImageIcon("res/stop.png"));
		lblPlayerDebuff = new JLabel(new ImageIcon("res/stop.png"));
		lblPlayerItems = new JLabel[5];
		for (int i = 0; i < lblPlayerItems.length; i++) {
			lblPlayerItems[i] = new JLabel(new ImageIcon("res/stop.png"));
		}
		lblPlayerNextPiece = new JLabel();
		lblPlayerNextPiece.setPreferredSize(new Dimension(BoardPanel.BLOCK_SIZE * 4 + 10, BoardPanel.BLOCK_SIZE * 4 + 10));
		
		constantLables.add(lblItems);
		constantLables.add(lblNextPiece);
		constantLables.add(lblSpeed);
		constantLables.add(lblScore);
		constantLables.add(lblBuff);
		constantLables.add(lblDebuff);
								
		GridBagConstraints c = new GridBagConstraints(
				0, 
				0, 
				5, 
				1, 
				0, 
				0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 
				2, 
				2);
		for (JLabel label : constantLables) {
			label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            add(label,c);
            c.gridy += 2;
		}

		c = new GridBagConstraints(
				0, 
				1, 
				5, 
				1, 
				0, 
				0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.NONE, 
				new Insets(2, 2, 2, 2), 
				2, 
				2);
		c.gridwidth = 1;
		for (int i = 0; i < lblPlayerItems.length; i++) {
			add(lblPlayerItems[i],c);
			c.gridx +=1;

		}
		
		c.gridx = 0;
		c.gridwidth = 5;
		

		c.gridy = 3;
		lblPlayerNextPiece.setBackground(Color.BLACK);
		lblPlayerNextPiece.setForeground(Color.WHITE);
		add(lblPlayerNextPiece,c);

		c.gridy += 2;
		lblPlayerSpeed.setBackground(Color.BLACK);
		lblPlayerSpeed.setForeground(Color.WHITE);
		add(lblPlayerSpeed,c);

		c.gridy += 2;
		lblPlayerScore.setBackground(Color.BLACK);
		lblPlayerScore.setForeground(Color.WHITE);
		add(lblPlayerScore,c);

		c.gridy += 2;
		lblPlayerBuff.setBackground(Color.BLACK);
		lblPlayerBuff.setForeground(Color.WHITE);
		add(lblPlayerBuff,c);

		c.gridy += 2;
		lblPlayerDebuff.setBackground(Color.BLACK);
		lblPlayerDebuff.setForeground(Color.WHITE);
		add(lblPlayerDebuff,c);

	}
	
	
	public void update() {
		if (model != null) {
			lblPlayerScore.setText(String.valueOf(model.getScore()));
			lblPlayerSpeed.setText(String.valueOf(model.getSpeed()));
			Integer[][] pattern = model.getNextPiece();
			Color patternColor = model.getNextPieceColor();
			Graphics g = lblPlayerNextPiece.getGraphics();
			g.fillRect(0, 0, lblPlayerNextPiece.getWidth(), lblPlayerNextPiece.getHeight());
			MagicItem items[] = model.getItems();
			for (int i = 0; i < items.length; i++) {
				if (items[i] != null) {
					ImageIcon icon = items[i].itemIcon;
					
					lblPlayerItems[i].setIcon(icon);
				}
				else {
					lblPlayerItems[i].setIcon(new ImageIcon("res/stop.png"));
				}
			}
			
			g.translate(5, 5);
			for (int patternCol = 0; patternCol < 4; patternCol++) {
				for (int patternRow = 0; patternRow < 4; patternRow++) {
					if (pattern[0][patternRow*4 + patternCol] == 1)
					{
						drawBlock(patternColor, 
								0 + patternRow, 
								0 + patternCol, g);
					}
				}
			}
			if (model.getBuff() != null) {
				Image img = model.getBuff().itemIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				lblPlayerBuff.setIcon(icon);
			}
			else {
				lblPlayerBuff.setIcon(new ImageIcon("res/stop.png"));
			}
			if (model.getDebuff() != null) {
				Image img = model.getDebuff().itemIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				lblPlayerDebuff.setIcon(icon);
			}
			else {
				lblPlayerDebuff.setIcon(new ImageIcon("res/stop.png"));
			}
		}
	}
	
	private void drawBlock(Color patternColor, int row, int col, Graphics g) {

		g.setColor(patternColor.darker());
		g.fillRect(col * BoardPanel.BLOCK_SIZE, 
					row * BoardPanel.BLOCK_SIZE, 
					BoardPanel.BLOCK_SIZE, BoardPanel.BLOCK_SIZE);
		g.setColor(patternColor);
		g.fillRect(col * BoardPanel.BLOCK_SIZE + BoardPanel.BLOCK_SHADOW, 
					row * BoardPanel.BLOCK_SIZE + BoardPanel.BLOCK_SHADOW, 
					BoardPanel.BLOCK_SIZE - 2 * BoardPanel.BLOCK_SHADOW, 
					BoardPanel.BLOCK_SIZE - 2 * BoardPanel.BLOCK_SHADOW);
		
	}

	public StatusPanelModel getModel() {
		return model;
	}

	public void setModel(StatusPanelModel model) {
		this.model = model;
	}
	
	

}
