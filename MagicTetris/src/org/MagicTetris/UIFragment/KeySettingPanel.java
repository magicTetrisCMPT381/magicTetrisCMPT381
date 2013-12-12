package org.MagicTetris.UIFragment;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.MagicTetris.util.KeySettings;

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
		keys[0] = ((mKeyAdapter) keyRotate.getKeyListeners()[0]).getKeycode();
		keys[1] = ((mKeyAdapter) keyLeft.getKeyListeners()[0]).getKeycode();
		keys[2] = ((mKeyAdapter) keyRight.getKeyListeners()[0]).getKeycode();
		keys[3] = ((mKeyAdapter) keyDown.getKeyListeners()[0]).getKeycode();
		keys[4] = ((mKeyAdapter) keyUseItem.getKeyListeners()[0]).getKeycode();
		keys[5] = ((mKeyAdapter) keyChangeItem.getKeyListeners()[0]).getKeycode();
		
		if (hasDuplicateItem(keys)) {
			return null;
		}
		
		return keys;
	}
	
	@Override
	public void loadFromKeySettings(KeySettings keys) {
		keyRotate.setText(KeyEvent.getKeyText((int) keys.getKEY_ROTATE()));
		keyLeft.setText(KeyEvent.getKeyText((int) keys.getKEY_LEFT()));
		keyRight.setText(KeyEvent.getKeyText((int) keys.getKEY_RIGHT()));
		keyDown.setText(KeyEvent.getKeyText((int) keys.getKEY_DOWN()));
		keyChangeItem.setText(KeyEvent.getKeyText((int) keys.getKEY_CHANGE_ITEM()));
		keyUseItem.setText(KeyEvent.getKeyText((int) keys.getKEY_USE_ITEM()));
		
		((mKeyAdapter) keyRotate.getKeyListeners()[0]).setKeycode(keys.getKEY_ROTATE());
		((mKeyAdapter) keyLeft.getKeyListeners()[0]).setKeycode(keys.getKEY_LEFT());
		((mKeyAdapter) keyRight.getKeyListeners()[0]).setKeycode(keys.getKEY_RIGHT());
		((mKeyAdapter) keyDown.getKeyListeners()[0]).setKeycode(keys.getKEY_DOWN());
		((mKeyAdapter) keyUseItem.getKeyListeners()[0]).setKeycode(keys.getKEY_USE_ITEM());
		((mKeyAdapter) keyChangeItem.getKeyListeners()[0]).setKeycode(keys.getKEY_CHANGE_ITEM());
		
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
		private float keycode;
		public mKeyAdapter(JTextField target) {
			this.textField = target;
		}
		@Override
		public void keyPressed(KeyEvent e) {
			keycode = e.getKeyCode();
			textField.setText(KeyEvent.getKeyText(e.getKeyCode()));
		}
		public float getKeycode() {
			return keycode;
		}
		public void setKeycode(float keycode) {
			this.keycode = keycode;
		}
		
	}
}
