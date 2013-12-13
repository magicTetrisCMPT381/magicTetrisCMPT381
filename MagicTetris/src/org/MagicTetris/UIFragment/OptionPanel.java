package org.MagicTetris.UIFragment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel {
	private final String ROTATE_KEY_STRING = "Rotate";
	private final String MOVE_LEFT_KEY_STRING = "Move left";
	private final String MOVE_RIGHT_KEY_STRING = "Move right";
	private final String MOVE_DOWN_STRING = "Move down";
	private final String CHANGE_ITEM_KRY_STRING = "Change item";
	private final String USE_ITEM_KEY_STRING = "Use item";
	
	private final String DEFAULT_KEY_STRING = "Click to change";
	
	private LinkedList<JLabel> promptLabels;
	private LinkedList<JTextField> keySetAreas1;
	private LinkedList<JTextField> keySetAreas2;
	public OptionPanel() {
		setLayout( new GridBagLayout());
		promptLabels = new LinkedList<>();
		keySetAreas1 = new LinkedList<>();
		keySetAreas2 = new LinkedList<>();
		createPromptLabels();
		createKeySetAreas(keySetAreas1);
		createKeySetAreas(keySetAreas2);
		placePrompts(promptLabels, 1, 0);
		placePrompts(keySetAreas1, 2, 0);
		placePrompts(keySetAreas2, 3, 0);
	}
	
	/**
	 * This method returns a human friendly style of key settings.
	 * @return
	 */
	public String[][] getOptions() {
		String[][] result = new String[2][6];
		int i = 0;
		for (JTextField key : keySetAreas1) {
			result[0][i] = key.getText();
			i++;
		}
		
		i = 0;
		
		for (JTextField key : keySetAreas2) {
			result[1][i] = key.getText();
			i++;
		}
		return result;
	}
	
	/**
	 * This method returns a VK_* style key settings.
	 * @return null if there is a duplicate.
	 */
	public int[][] getKeys() {
		if (checkDuplicated()) {
			return null;
		}
		int[][] result = new int[2][6];
		int i = 0;
		keyRetriever k = null;
		for (JTextField key : keySetAreas1) {
			k = (keyRetriever) key.getKeyListeners()[0];
			result[0][i] = k.getKeycode();
			i++;
		}
		
		i = 0;
		
		for (JTextField key : keySetAreas2) {
			k = (keyRetriever) key.getKeyListeners()[0];
			result[1][i] = k.getKeycode();
			i++;
		}
		return result;
	}
	
	/**
	 * Add all components in linked list to window.
	 * @param prompt the linked list.
	 * @param col the column to add into.
	 * @param row start from which row.
	 */
	protected void placePrompts(LinkedList<? extends JComponent> prompt, int col, int row) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = col;
		c.gridy = row;
		c.ipadx = 10;
		c.ipady = 10;
		c.fill = GridBagConstraints.VERTICAL;
		c.insets = new Insets(10, 10, 10, 10);
		for (JComponent component : prompt) {
			
			add(component,c);
			c.gridy++;
		}
		
	}

	/**
	 * Create prompt labels.
	 */
	protected void createPromptLabels() {
		promptLabels.add(new JLabel(ROTATE_KEY_STRING));
		promptLabels.add(new JLabel(MOVE_LEFT_KEY_STRING));
		promptLabels.add(new JLabel(MOVE_RIGHT_KEY_STRING));
		promptLabels.add(new JLabel(MOVE_DOWN_STRING));
		promptLabels.add(new JLabel(CHANGE_ITEM_KRY_STRING));
		promptLabels.add(new JLabel(USE_ITEM_KEY_STRING));
	}
	
	/**
	 * Fill the given linked list by input list.
	 * @param list
	 */
	protected void createKeySetAreas(LinkedList<JTextField> list) {
		list.add(new JTextField(DEFAULT_KEY_STRING));
		list.add(new JTextField(DEFAULT_KEY_STRING));
		list.add(new JTextField(DEFAULT_KEY_STRING));
		list.add(new JTextField(DEFAULT_KEY_STRING));
		list.add(new JTextField(DEFAULT_KEY_STRING));
		list.add(new JTextField(DEFAULT_KEY_STRING));
		for (JTextField test : list) {
			test.addKeyListener(new keyRetriever(test));
			test.setEditable(false);
		}
	}
	
	private boolean checkDuplicated(){
		int[][] result = new int[2][6];
		int i = 0;
		keyRetriever k = null;
		for (JTextField key : keySetAreas1) {
			k = (keyRetriever) key.getKeyListeners()[0];
			result[0][i] = k.getKeycode();
			i++;
		}
		
		i = 0;
		
		for (JTextField key : keySetAreas2) {
			k = (keyRetriever) key.getKeyListeners()[0];
			result[1][i] = k.getKeycode();
			i++;
		}
		
		int[] AllPlayer = Arrays.copyOf(result[0], 12);
		for (int j = 6; j < AllPlayer.length; j++) {
			AllPlayer[j] = result[1][j-6];
		}
		
		boolean PlayerOne = hasDuplicateItem(result[0]);
		boolean PlayerTwo = hasDuplicateItem(result[1]);		
		boolean PlayerAndPlayer = hasDuplicateItem(AllPlayer);
		
		return (PlayerOne || PlayerTwo || PlayerAndPlayer);
	}
	
	private boolean hasDuplicateItem(int[] arr){
		HashSet<Integer> ht = new HashSet<Integer>();
        for (int i : arr) {
			if (!ht.add(i)) {
				return true;
			}
		}
        return false;
	}
	
	private class keyRetriever extends KeyAdapter{

		private JTextField textField;
		private int keycode;
		public keyRetriever(JTextField target) {
			this.textField = target;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keycode = e.getKeyCode();
			textField.setText(KeyEvent.getKeyText(e.getKeyCode()));
		}
		public int getKeycode() {
			return keycode;
		}
		
	}

}
