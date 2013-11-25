package org.MagicTetris.UIFragment;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class OptionPanel extends JPanel {
	private final String START_STRING = "Start Game";
	private final String OPTION_STRING = "Show options";
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
	
	public String[][] getOptions() {
		String[][] result = new String[2][8];
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
	 * 将链表内的所有组件添加到窗口。
	 * @param prompt 组件链表
	 * @param col 添加到的列
	 * @param row 从哪一行开始添加
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
	
	private class keyRetriever extends KeyAdapter{

		private JTextField textField;
		public keyRetriever(JTextField target) {
			this.textField = target;
		}
		@Override
		public void keyTyped(KeyEvent e) {
			if (!Character.isLetterOrDigit(e.getKeyChar())) {
				JOptionPane.showMessageDialog(null, "Must be a number or letter.");
				return;
			}
			String key = String.valueOf(e.getKeyChar()).toUpperCase();
			textField.setText(key);
		}
		
	}

}
