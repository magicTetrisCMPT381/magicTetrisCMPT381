package org.MagicTetris.UIFragment;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.java.games.input.Component;
import net.java.games.input.Controller;

import org.MagicTetris.util.ControllerListener;
import org.MagicTetris.util.ControllerPoller;

import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;

import javax.swing.event.MouseInputAdapter;

import joystick.JInputJoystick;

public class ControllSettingPanel extends JPanel implements ControllerListener {
	private JTextField keyRotate;
	private JTextField keyLeft;
	private JTextField keyRight;
	private JTextField keyDown;
	private JTextField keyChangeItem;
	private JTextField keyUseItem;
	private String prompt = "Click to change";
	private JTextField focusedField;
	private Thread thread;
	
	public ControllSettingPanel() {

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
		
		thread = new Thread(new ControllerPoller(new JInputJoystick(Controller.Type.GAMEPAD), this));
		thread.start();
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
			System.out.println("Gained Focus");
		}
		
		public void focusLost(FocusEvent e){
			focusedField = null;
			System.out.println("Lost Focus");
		}
		
		public float getKeyCode() {
			return keyCode;
		}
		
		public void setKeyCode(float keyCode) {
			this.keyCode = keyCode;
		}
	}

}
