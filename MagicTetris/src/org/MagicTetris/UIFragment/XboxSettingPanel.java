package org.MagicTetris.UIFragment;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.java.games.input.Component;
import net.java.games.input.Controller;

import org.MagicTetris.util.ControllerListener;
import org.MagicTetris.util.ControllerPoller;
import org.MagicTetris.util.KeySettings;

import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.event.MouseInputAdapter;

import joystick.JInputJoystick;

public class XboxSettingPanel extends JPanel implements ControllerListener,KeySetting {
	private JTextField keyRotate;
	private JTextField keyLeft;
	private JTextField keyRight;
	private JTextField keyDown;
	private JTextField keyChangeItem;
	private JTextField keyUseItem;
	private String prompt = "Click to change";
	private JTextField focusedField;
	private ControllerPoller poller;
	
	public XboxSettingPanel() {

		setLayout(new GridLayout(6, 1, 5, 5));
		
		keyRotate= new JTextField(prompt);
		keyRotate.setEditable(false);
		keyRotate.addFocusListener(new mFocusAdapter(keyRotate));
		add(keyRotate);
		
		keyLeft = new JTextField(prompt);
		keyLeft.setEditable(false);
		keyLeft.addFocusListener(new mFocusAdapter(keyLeft));
		add(keyLeft);
		
		keyRight = new JTextField(prompt);
		keyRight.setEditable(false);
		keyRight.addFocusListener(new mFocusAdapter(keyRight));
		add(keyRight);
		
		keyDown = new JTextField(prompt);
		keyDown.setEditable(false);
		keyDown.addFocusListener(new mFocusAdapter(keyDown));
		add(keyDown);
		
		keyChangeItem = new JTextField(prompt);
		keyChangeItem.setEditable(false);
		keyChangeItem.addFocusListener(new mFocusAdapter(keyChangeItem));
		add(keyChangeItem);
		
		keyUseItem = new JTextField(prompt);
		keyUseItem.setEditable(false);
		keyUseItem.addFocusListener(new mFocusAdapter(keyUseItem));
		add(keyUseItem);
		
		poller =new ControllerPoller(new JInputJoystick(Controller.Type.GAMEPAD));
		poller.addListener(this);
		Thread t = new Thread(poller);
		t.setDaemon(true);
		t.start();
	}
	
	public float[] keySettings() {
		float[] keys = new float[6];
		
		try {
			keys[0] = (findFocusAdapter(keyRotate.getFocusListeners())).getKeyCode();
			keys[1] = (findFocusAdapter(keyLeft.getFocusListeners())).getKeyCode();
			keys[2] = (findFocusAdapter(keyRight.getFocusListeners())).getKeyCode();
			keys[3] = (findFocusAdapter(keyDown.getFocusListeners())).getKeyCode();
			keys[4] = (findFocusAdapter(keyUseItem.getFocusListeners())).getKeyCode();
			keys[5] = (findFocusAdapter(keyChangeItem.getFocusListeners())).getKeyCode();
		} catch (NullPointerException e) {
			return null;
		}
		
		if (hasDuplicateItem(keys)) {
			return null;
		}
		
		return keys;
	}
	
	@Override
	public void loadFromKeySettings(KeySettings keys) {
		(findFocusAdapter(keyRotate.getFocusListeners())).setKeyCode(keys.getKEY_ROTATE());
		(findFocusAdapter(keyLeft.getFocusListeners())).setKeyCode(keys.getKEY_LEFT());
		(findFocusAdapter(keyRight.getFocusListeners())).setKeyCode(keys.getKEY_RIGHT());
		(findFocusAdapter(keyDown.getFocusListeners())).setKeyCode(keys.getKEY_DOWN());
		(findFocusAdapter(keyUseItem.getFocusListeners())).setKeyCode(keys.getKEY_USE_ITEM());
		(findFocusAdapter(keyChangeItem.getFocusListeners())).setKeyCode(keys.getKEY_CHANGE_ITEM());
		
		keyRotate.setText(valueToName(keys.getKEY_ROTATE(), keys.getKEY_ROTATE() > 1.0f));
		keyLeft.setText(valueToName(keys.getKEY_LEFT(), keys.getKEY_LEFT() > 1.0f));
		keyRight.setText(valueToName(keys.getKEY_RIGHT(), keys.getKEY_RIGHT() > 1.0f));
		keyDown.setText(valueToName(keys.getKEY_DOWN(), keys.getKEY_DOWN() > 1.0f));
		keyChangeItem.setText(valueToName(keys.getKEY_CHANGE_ITEM(), keys.getKEY_CHANGE_ITEM() > 1.0f));
		keyUseItem.setText(valueToName(keys.getKEY_USE_ITEM(), keys.getKEY_USE_ITEM() > 1.0f));
	}
	
	private String valueToName(float value,boolean isButton){
		StringBuilder sb = new StringBuilder();
		if (isButton) {
			sb.append("Button ");
			return sb.append((int)value).toString();
		}
		else {
			sb.append("POV hat ");
			if (value == Component.POV.DOWN) {
				sb.append("DOWN");
			}
			else if (value == Component.POV.UP) {
				sb.append("UP");
			}
			else if (value == Component.POV.LEFT) {
				sb.append("LEFT");
			}
			else if (value == Component.POV.RIGHT) {
				sb.append("RIGHT");
			}
			else {
				return "Unaccepted";
			}
			
			return sb.toString();
		}
	}

	public void gracefullyStop(){
		poller.stop();
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

	@Override
	public void HatSwitchChanged(float HatPosition) {
		if (focusedField == null) {
			return;
		}
		
		mFocusAdapter focusAdapter = findFocusAdapter(focusedField.getFocusListeners());
		if (focusAdapter == null) {
			return;
		}
		
		if (HatPosition == Component.POV.DOWN) {
			focusedField.setText("POV hat DOWN");
			focusAdapter.setKeyCode(HatPosition);
		}
		else if (HatPosition == Component.POV.UP) {
			focusedField.setText("POV hat UP");
			focusAdapter.setKeyCode(HatPosition);
		}
		else if (HatPosition == Component.POV.LEFT) {
			focusedField.setText("POV hat LEFT");
			focusAdapter.setKeyCode(HatPosition);
		}
		else if (HatPosition == Component.POV.RIGHT) {
			focusedField.setText("POV hat RIGHT");
			focusAdapter.setKeyCode(HatPosition);
		}
		else {
			return;
		}
		
	}

	@Override
	public void ButtonPressed(int ButtonNum) {

		
	}

	@Override
	public void ButtonReleased(int ButtonNum) {
		if (focusedField == null) {
			return;
		}
		
		mFocusAdapter focusAdapter = findFocusAdapter(focusedField.getFocusListeners());
		
		if (focusAdapter == null) {
			return;
		}
		focusedField.setText("Button " + ButtonNum);
		focusAdapter.setKeyCode(ButtonNum);
	}
	
	
	private mFocusAdapter findFocusAdapter(FocusListener[] listeners) {
		for (FocusListener listener : listeners) {
			if (listener instanceof mFocusAdapter) {
				return (mFocusAdapter) listener;
			}
		}
		return null;
	}
	
	
	private class mFocusAdapter extends FocusAdapter{
		private JTextField mTextField;
		private float keyCode;
		public mFocusAdapter(JTextField target) {
			mTextField = target;
			keyCode = -1.0f;
		}
		public void focusGained(FocusEvent e){
			focusedField = mTextField;
		}
		
		public void focusLost(FocusEvent e){
			focusedField = null;
		}
		
		public float getKeyCode() {
			return keyCode;
		}
		
		public void setKeyCode(float keyCode) {
			this.keyCode = keyCode;
		}
	}

}
