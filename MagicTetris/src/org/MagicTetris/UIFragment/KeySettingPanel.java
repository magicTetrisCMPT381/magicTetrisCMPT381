package org.MagicTetris.UIFragment;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class KeySettingPanel extends JPanel implements KeySetting {
	private JTextField keyRotate;
	private JTextField keyLeft;
	private JTextField keyRight;
	private JTextField keyDown;
	private JTextField keyChangeItem;
	private JTextField keyUseItem;
	private String prompt = "Click to change";
	
	public KeySettingPanel() {
		setLayout(new GridLayout(6, 1, 5, 5));
		
		keyRotate= new JTextField(prompt);
		keyRotate.setEditable(false);
		keyRotate.addKeyListener(new mKeyAdapter(keyRotate));
		add(keyRotate);
		
		keyLeft = new JTextField(prompt);
		keyLeft.setEditable(false);
		keyLeft.addKeyListener(new mKeyAdapter(keyLeft));
		add(keyLeft);
		
		keyRight = new JTextField(prompt);
		keyRight.setEditable(false);
		keyRight.addKeyListener(new mKeyAdapter(keyRight));
		add(keyRight);
		
		keyDown = new JTextField(prompt);
		keyDown.setEditable(false);
		keyDown.addKeyListener(new mKeyAdapter(keyDown));
		add(keyDown);
		
		keyChangeItem = new JTextField(prompt);
		keyChangeItem.setEditable(false);
		keyChangeItem.addKeyListener(new mKeyAdapter(keyChangeItem));
		add(keyChangeItem);
		
		keyUseItem = new JTextField(prompt);
		keyUseItem.setEditable(false);
		keyUseItem.addKeyListener(new mKeyAdapter(keyUseItem));
		add(keyUseItem);
	}
	
	public float[] keySettings() {
		float[] keys = new float[6];
		keys[0] = ((mKeyAdapter) keyRotate.getKeyListeners()[0]).getKeyCode();
		keys[1] = ((mKeyAdapter) keyLeft.getKeyListeners()[0]).getKeyCode();
		keys[2] = ((mKeyAdapter) keyRight.getKeyListeners()[0]).getKeyCode();
		keys[3] = ((mKeyAdapter) keyDown.getKeyListeners()[0]).getKeyCode();
		keys[4] = ((mKeyAdapter) keyUseItem.getKeyListeners()[0]).getKeyCode();
		keys[5] = ((mKeyAdapter) keyChangeItem.getKeyListeners()[0]).getKeyCode();
		
		if (hasDuplicateItem(keys)) {
			return null;
		}
		
		return keys;
	}
	
	private boolean hasDuplicateItem(float[] arr){
		HashSet<Float> ht = new HashSet<Float>();
        for (float i : arr) {
			if (!ht.add(i)) {
				return true;
			}
		}
        return false;
	}
	
	private class mKeyAdapter extends KeyAdapter{

		private JTextField textField;
		private int keycode;
		public mKeyAdapter(JTextField target) {
			this.textField = target;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keycode = e.getKeyCode();
			textField.setText(KeyEvent.getKeyText(e.getKeyCode()));
		}
		public int getKeyCode() {
			return keycode;
		}
		
	}
}
