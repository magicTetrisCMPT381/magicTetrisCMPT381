package org.MagicTetris.UIFragment;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class OptionPanel2 extends JPanel {

	private static final String KEYBORD_CTRL = "Keyboard";
	private static final String XBOX_CTRL = "XBOX Controller";
	private ButtonGroup playerOneControllerType;
	private ButtonGroup playerTwoControllerType;
	private JRadioButton playerOneKeyboard;
	private JRadioButton playerOneXBOX;
	private JRadioButton playerTwoKeyboard;
	private JRadioButton playerTwoXBOX;
	private boolean useKeyboardForPlayerOne;
	private boolean useKeyboardForPlayerTwo;
	private JPanel playerOneKeyPanel;
	private JPanel playerTwoKeyPanel;
	
	private mActionListener actionListener;
	public OptionPanel2() {
		actionListener = new mActionListener();
		
		playerOneControllerType = new ButtonGroup();
		playerOneKeyboard = new JRadioButton(KEYBORD_CTRL);
		playerOneKeyboard.setActionCommand(KEYBORD_CTRL);
		playerOneKeyboard.addActionListener(actionListener);
		playerOneXBOX = new JRadioButton(XBOX_CTRL);
		playerOneXBOX.setActionCommand(XBOX_CTRL);
		playerOneXBOX.addActionListener(actionListener);
		playerOneControllerType.add(playerOneKeyboard);
		playerOneControllerType.add(playerOneXBOX);
		
		
		playerTwoControllerType = new ButtonGroup();
		playerTwoKeyboard = new JRadioButton(KEYBORD_CTRL);
		playerTwoKeyboard.setActionCommand(KEYBORD_CTRL);
		playerTwoKeyboard.addActionListener(actionListener);;
		playerTwoXBOX = new JRadioButton(XBOX_CTRL);
		playerTwoXBOX.setActionCommand(XBOX_CTRL);
		playerTwoXBOX.addActionListener(actionListener);
		playerTwoControllerType.add(playerTwoKeyboard);
		playerTwoControllerType.add(playerTwoXBOX);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(0, 0, 0, 0);
		
		add(new JLabel("Player One/Two"),c);
		
		c.gridy = 1;
		add(new promptPanel(),c);
		
		c.gridx = 1;
		c.gridy = 0;		
		add(playerOneKeyboard,c);
		
		c.gridx = 2;
		add(playerOneXBOX,c);
		
		c.gridx = 3;
		add(playerTwoKeyboard,c);
		
		c.gridx = 4;
		add(playerTwoXBOX,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		playerOneKeyPanel = new JPanel(new CardLayout());
		playerOneKeyPanel.add(new KeySettingPanel(),KEYBORD_CTRL);
		playerOneKeyPanel.add(new ControllSettingPanel(),XBOX_CTRL);
		add(playerOneKeyPanel,c);
		
		c.gridx = 3;
		playerTwoKeyPanel = new JPanel(new CardLayout());
		playerTwoKeyPanel.add(new KeySettingPanel(),KEYBORD_CTRL);
		playerTwoKeyPanel.add(new ControllSettingPanel(),XBOX_CTRL);
		add(playerTwoKeyPanel,c);
		
		
		playerOneKeyboard.doClick();
		playerTwoKeyboard.doClick();

	}
	
	private class promptPanel extends JPanel{
		private final String ROTATE_KEY_STRING = "Rotate";
		private final String MOVE_LEFT_KEY_STRING = "Move left";
		private final String MOVE_RIGHT_KEY_STRING = "Move right";
		private final String MOVE_DOWN_STRING = "Move down";
		private final String CHANGE_ITEM_KRY_STRING = "Change item";
		private final String USE_ITEM_KEY_STRING = "Use item";
		public promptPanel() {
			setLayout(new GridLayout(6, 1, 10, 10));
			add(new JLabel(ROTATE_KEY_STRING));
			add(new JLabel(MOVE_LEFT_KEY_STRING));
			add(new JLabel(MOVE_RIGHT_KEY_STRING));
			add(new JLabel(MOVE_DOWN_STRING));
			add(new JLabel(CHANGE_ITEM_KRY_STRING));
			add(new JLabel(USE_ITEM_KEY_STRING));
		}
	}
	
	private class mActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cl;
			if (e.getSource().equals(playerOneKeyboard)) {
				useKeyboardForPlayerOne = true;
				cl = (CardLayout) playerOneKeyPanel.getLayout();
				cl.show(playerOneKeyPanel, KEYBORD_CTRL);
			}
			if (e.getSource().equals(playerOneXBOX)) {
				useKeyboardForPlayerOne = false;
				cl = (CardLayout) playerOneKeyPanel.getLayout();
				cl.show(playerOneKeyPanel, XBOX_CTRL);

			}
			if (e.getSource().equals(playerTwoKeyboard)) {
				useKeyboardForPlayerTwo = true;
				cl = (CardLayout) playerTwoKeyPanel.getLayout();
				cl.show(playerTwoKeyPanel, KEYBORD_CTRL);

			}
			if (e.getSource().equals(playerTwoXBOX)) {
				useKeyboardForPlayerTwo = false;
				cl = (CardLayout) playerTwoKeyPanel.getLayout();
				cl.show(playerTwoKeyPanel, XBOX_CTRL);

			}
			

		}

	}
}
