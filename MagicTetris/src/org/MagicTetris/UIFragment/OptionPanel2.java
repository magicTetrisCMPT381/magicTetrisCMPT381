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

import joystick.JInputJoystick;
import net.java.games.input.Controller;

import org.MagicTetris.util.KeySettings;

@SuppressWarnings("serial")
public class OptionPanel2 extends JPanel {

	private static final String KEYBORD_CTRL = "Keyboard";
	private static final String XBOX_CTRL = "XBOX Controller";
	private ButtonGroup playerOneControllerType;
	private ButtonGroup playerTwoControllerType;
	private JRadioButton playerOneKeyboard;
	private JRadioButton playerOneXBOX;
	private JRadioButton playerTwoKeyboard;
	private JRadioButton playerTwoXBOX;
	private boolean useXboxForPlayerOne;
	private boolean useXboxForPlayerTwo;
	
	private JPanel playerOneKeyPanel;
	private JPanel playerTwoKeyPanel;
	
	private KeySettingPanel playerOneKeySettingPanel;
	private XboxSettingPanel playerOneControllSettingPanel;
	
	private KeySettingPanel playerTwoKeySettingPanel;
	private XboxSettingPanel playerTwoControllSettingPanel;
	
	private mActionListener actionListener;
	public OptionPanel2() {
		
		boolean isControllerExist = new JInputJoystick(Controller.Type.GAMEPAD).isControllerConnected();
		
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
		
		playerOneKeySettingPanel = new KeySettingPanel();		
		playerOneKeyPanel.add(playerOneKeySettingPanel,KEYBORD_CTRL);
		
		if (isControllerExist) {
			playerOneControllSettingPanel = new XboxSettingPanel();
			playerOneKeyPanel.add(playerOneControllSettingPanel,XBOX_CTRL);
		}
		else {
			playerOneKeyPanel.add(new JPanel(),XBOX_CTRL);
		}
		
		add(playerOneKeyPanel,c);
		
		c.gridx = 3;
		playerTwoKeyPanel = new JPanel(new CardLayout());
		
		playerTwoKeySettingPanel = new KeySettingPanel();
		playerTwoKeyPanel.add(playerTwoKeySettingPanel,KEYBORD_CTRL);

		if (isControllerExist) {
			playerTwoControllSettingPanel = new XboxSettingPanel();
			playerTwoKeyPanel.add(playerTwoControllSettingPanel, XBOX_CTRL);
		}
		else {
			playerTwoKeyPanel.add(new JPanel(),XBOX_CTRL);
		}
		add(playerTwoKeyPanel,c);
		
		
//		playerOneKeyboard.doClick();
//		playerTwoKeyboard.doClick();

	}
	
	public KeySettings[] obtainKeySettings() throws IllegalArgumentException {
		KeySettings[] keys = new KeySettings[2];
		KeySetting[] settingPanels = findSettingPanel();
		
		float[] keys_float;
		keys_float = settingPanels[0].keySettings();

		if(keys_float == null){
			throw new IllegalArgumentException("Duplicate key detected.");
		}
		
		keys[0] = new KeySettings();
		keys[0].setKEY_ROTATE(keys_float[0]);
		keys[0].setKEY_LEFT(keys_float[1]);
		keys[0].setKEY_RIGHT(keys_float[2]);
		keys[0].setKEY_DOWN(keys_float[3]);
		keys[0].setKEY_USE_ITEM(keys_float[4]);
		keys[0].setKEY_CHANGE_ITEM(keys_float[5]);
		keys[0].setXboxController(useXboxForPlayerOne);

		keys_float = settingPanels[1].keySettings();
		
		if(keys_float == null){
			throw new IllegalArgumentException("Duplicate key detected.");
		}
		
		keys[1] = new KeySettings();
		keys[1].setKEY_ROTATE(keys_float[0]);
		keys[1].setKEY_LEFT(keys_float[1]);
		keys[1].setKEY_RIGHT(keys_float[2]);
		keys[1].setKEY_DOWN(keys_float[3]);
		keys[1].setKEY_USE_ITEM(keys_float[4]);
		keys[1].setKEY_CHANGE_ITEM(keys_float[5]);
		keys[1].setXboxController(useXboxForPlayerTwo);
		
		if (playerOneControllSettingPanel != null && playerTwoControllSettingPanel != null) {
			playerOneControllSettingPanel.gracefullyStop();
			playerTwoControllSettingPanel.gracefullyStop();
		}
		return keys;
	}
	
	public void loadKeySettings(KeySettings[] keys) {
		if(keys[0].isXboxController()){
			playerOneXBOX.doClick();
			playerOneControllSettingPanel.loadFromKeySettings(keys[0]);
		}
		else {
			playerOneKeyboard.doClick();
			playerOneKeySettingPanel.loadFromKeySettings(keys[0]);
		}
		
		if(keys[1].isXboxController()){
			playerTwoXBOX.doClick();
			playerTwoControllSettingPanel.loadFromKeySettings(keys[1]);
		}
		else {
			playerTwoKeyboard.doClick();
			playerTwoKeySettingPanel.loadFromKeySettings(keys[1]);
		}
		
		
	}
	
	private KeySetting[] findSettingPanel(){
		KeySetting[] playerPanels = new KeySetting[2];
		
		if (useXboxForPlayerOne) {
			playerPanels[0] = playerOneControllSettingPanel;
		}
		else {
			playerPanels[0] = playerOneKeySettingPanel;
		}
		
		if (useXboxForPlayerTwo) {
			playerPanels[1] = playerTwoControllSettingPanel;
		}
		else {
			playerPanels[1] = playerTwoKeySettingPanel;
		}
		

		
		return playerPanels;
	}
	
	public void disableController() {
		playerOneXBOX.setEnabled(false);
		playerTwoXBOX.setEnabled(false);
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
				useXboxForPlayerOne = false;
				cl = (CardLayout) playerOneKeyPanel.getLayout();
				cl.show(playerOneKeyPanel, KEYBORD_CTRL);
			}
			if (e.getSource().equals(playerOneXBOX)) {
				useXboxForPlayerOne = true;
				cl = (CardLayout) playerOneKeyPanel.getLayout();
				cl.show(playerOneKeyPanel, XBOX_CTRL);

			}
			if (e.getSource().equals(playerTwoKeyboard)) {
				useXboxForPlayerTwo = false;
				cl = (CardLayout) playerTwoKeyPanel.getLayout();
				cl.show(playerTwoKeyPanel, KEYBORD_CTRL);

			}
			if (e.getSource().equals(playerTwoXBOX)) {
				useXboxForPlayerTwo = true;
				cl = (CardLayout) playerTwoKeyPanel.getLayout();
				cl.show(playerTwoKeyPanel, XBOX_CTRL);

			}
			

		}

	}

	public boolean isUseKeyboardForPlayerOne() {
		return useXboxForPlayerOne;
	}

	public boolean isUseKeyboardForPlayerTwo() {
		return useXboxForPlayerTwo;
	}
}
