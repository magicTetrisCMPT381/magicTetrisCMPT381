package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
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
	private JLabel lblPlayerItems;
	private JLabel lblPlayerNextPiece;
	
	// 
	private ArrayList<JLabel> listOfLabels;
	
	public StatusPanel() {
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Dimension d = new Dimension(200, 
				BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE);
		this.setPreferredSize(d);
		this.setLayout(new GridBagLayout());		
		
		lblSpeed = new JLabel(SPEED_STRING);
		lblScore = new JLabel(SCORE_STRING);
		lblBuff = new JLabel(BUFF_STRING);
		lblDebuff = new JLabel(DEBUFF_STRING);
		lblItems = new JLabel(ITEMS_STRING);
		lblNextPiece = new JLabel(NEXTPIECE_STRING);
		lblPlayerScore = new JLabel("000000");
		lblPlayerSpeed = new JLabel("000000");
		lblPlayerBuff = new JLabel("Work In Progress...");
		lblPlayerDebuff = new JLabel("Work In Progress...");
		lblPlayerItems = new JLabel("Work In Progress...");
		lblPlayerNextPiece = new JLabel();
		
		lblPlayerNextPiece.setText("Nothing...");;
		lblPlayerNextPiece.setPreferredSize(new Dimension(BoardPanel.BLOCK_SIZE * 4 + 10, BoardPanel.BLOCK_SIZE * 4 + 10));
		
		listOfLabels = new ArrayList<JLabel>();
		listOfLabels.add(lblItems);
		listOfLabels.add(lblPlayerItems);
		listOfLabels.add(lblNextPiece);
		listOfLabels.add(lblPlayerNextPiece);
		listOfLabels.add(lblSpeed);
		listOfLabels.add(lblPlayerSpeed);
		listOfLabels.add(lblScore);
		listOfLabels.add(lblPlayerScore);
		listOfLabels.add(lblBuff);
		listOfLabels.add(lblPlayerBuff);
		listOfLabels.add(lblDebuff);
		listOfLabels.add(lblPlayerDebuff);
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.ipadx = 10;
		c.ipady = 10;
		for (JLabel label : listOfLabels) {
			label.setBackground(Color.BLACK);
			label.setForeground(Color.WHITE);
			this.add(label,c);
			c.gridy += 1;
		}
	}
	
	public void update() {
		if (model != null) {
			lblPlayerScore.setText(String.valueOf(model.getScore()));
			lblPlayerSpeed.setText(String.valueOf(model.getSpeed()));
			Integer[][] pattern = model.getNextPiece();
			Color patternColor = model.getNextPieceColor();
			Graphics g = lblPlayerNextPiece.getGraphics();
			g.fillRect(0, 0, lblPlayerNextPiece.getWidth(), lblPlayerNextPiece.getHeight());
			
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
