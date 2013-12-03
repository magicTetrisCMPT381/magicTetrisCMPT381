package org.MagicTetris.UIFragment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
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
	

	
	public StatusPanel() {
		this.setBackground(Color.BLACK);
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		Dimension d = new Dimension(170, 
				BoardPanelModel.VISIBLE_ROW_COUNT * BoardPanel.BLOCK_SIZE);
		this.setPreferredSize(d);
		this.setLayout(null);		
		
		lblSpeed = new JLabel(SPEED_STRING);
		lblSpeed.setBackground(Color.BLACK);
		lblSpeed.setForeground(Color.WHITE);
		
		lblScore = new JLabel(SCORE_STRING);
		lblScore.setBackground(Color.BLACK);
		lblScore.setForeground(Color.WHITE);
		
		lblBuff = new JLabel(BUFF_STRING);
		lblBuff.setBackground(Color.BLACK);
		lblBuff.setForeground(Color.WHITE);
		
		lblDebuff = new JLabel(DEBUFF_STRING);
		lblDebuff.setBackground(Color.BLACK);
		lblDebuff.setForeground(Color.WHITE);
		
		lblItems = new JLabel(ITEMS_STRING);
		lblItems.setBackground(Color.BLACK);
		lblItems.setForeground(Color.WHITE);
		
		lblNextPiece = new JLabel(NEXTPIECE_STRING);
		lblNextPiece.setBackground(Color.BLACK);
		lblNextPiece.setForeground(Color.WHITE);
		
		lblPlayerScore = new JLabel("000000");
		lblPlayerScore.setBackground(Color.BLACK);
		lblPlayerScore.setForeground(Color.WHITE);
		
		lblPlayerSpeed = new JLabel("000000");
		lblPlayerSpeed.setBackground(Color.BLACK);
		lblPlayerSpeed.setForeground(Color.WHITE);
		
		lblPlayerBuff = new JLabel();
		lblPlayerBuff.setBackground(Color.BLACK);
		lblPlayerBuff.setForeground(Color.WHITE);
		
		lblPlayerDebuff = new JLabel();
		lblPlayerDebuff.setBackground(Color.BLACK);
		lblPlayerDebuff.setForeground(Color.WHITE);
		
		lblPlayerItems = new JLabel[5];
		
		for (int i = 0; i < lblPlayerItems.length; i++) {
			lblPlayerItems[i] = new JLabel();
//			lblPlayerItems[i].setBackground(Color.BLACK);
//			lblPlayerItems[i].setForeground(Color.BLACK);
		}
		
		lblPlayerNextPiece = new JLabel();
		lblPlayerNextPiece.setBackground(Color.BLACK);
		lblPlayerNextPiece.setForeground(Color.WHITE);

		lblPlayerNextPiece.setPreferredSize(new Dimension(BoardPanel.BLOCK_SIZE * 4 + 10, BoardPanel.BLOCK_SIZE * 4 + 10));
		
		
		
		add(lblItems);
		lblItems.setBounds(10, 10, 150, 10);

		for (int i = 0; i < lblPlayerItems.length; i++) {
			add(lblPlayerItems[i]);
			lblPlayerItems[i].setBounds(25 + i * 32, 25, 40, 40);
		}
		


		

		add(lblNextPiece);
		lblNextPiece.setBounds(10, 70, 150, 10);

		add(lblPlayerNextPiece);
		lblPlayerNextPiece.setBounds(10, 85, BoardPanel.BLOCK_SIZE *4 +10, BoardPanel.BLOCK_SIZE *4 +10);

		add(lblSpeed);
		lblSpeed.setBounds(10, 180, 150, 10);

		add(lblPlayerSpeed);
		lblPlayerSpeed.setBounds(10, 195, 150, 10);

		add(lblScore);
		lblScore.setBounds(10, 210, 150, 10);
		

		add(lblPlayerScore);
		lblPlayerScore.setBounds(10, 225, 150, 10);

		add(lblBuff);
		lblBuff.setBounds(10, 240, 150, 10);

		add(lblPlayerBuff);
		lblPlayerBuff.setBounds(10, 255, 40, 40);

		add(lblDebuff);
		lblDebuff.setBounds(10, 320, 150, 10);

		add(lblPlayerDebuff);
		lblPlayerDebuff.setBounds(10, 335, 40, 40);
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
				lblPlayerBuff.setIcon(null);
			}
			if (model.getDebuff() != null) {
				Image img = model.getDebuff().itemIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
				ImageIcon icon = new ImageIcon(img);
				lblPlayerDebuff.setIcon(icon);
			}
			else {
				lblPlayerDebuff.setIcon(null);
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
