package org.MagicTetris.test;

import java.util.ArrayList;
import java.util.Arrays;

import joystick.JInputJoystick;
import net.java.games.input.*;

public class testXboxController {
	private static Thread pollThread;
	public static void main(String[] args) {
		testXboxController t = new testXboxController();
		JInputJoystick stick = new JInputJoystick(Controller.Type.GAMEPAD);
		if (!stick.isControllerConnected()) {
			System.out.println("No controller connected!");
			System.exit(1);
		}
		pollThread = t.new poller(stick);
		pollThread.start();
	}
	
	private class poller extends Thread{

		private JInputJoystick stick;
		private float value;
		public poller(JInputJoystick stick) {
			stick.pollController();
			this.stick = stick;
			value = 0.0f;
		}
		@Override
		public void run() {
			
			Boolean buttonValues[] = new Boolean[stick.getNumberOfButtons()];
			Boolean newButtonValues[] = new Boolean[stick.getNumberOfButtons()];
			int[] changeList;
			stick.getButtonsValues().toArray(buttonValues);
			while(true){
				if (stick.pollController()) {
					if (value != stick.getHatSwitchPosition()) {
						System.out.println("Value changed: from " + value + " to " + stick.getHatSwitchPosition());
						value = stick.getHatSwitchPosition();
					}
					stick.getButtonsValues().toArray(newButtonValues);
					changeList = compareChange(buttonValues, newButtonValues);
					for (int i = 0; i < changeList.length; i++) {
						if (changeList[i] == 1) {
							System.out.println("Button " + i +" changed: from " + buttonValues[i] + " to " + newButtonValues[i]);
						}
					}
					stick.getButtonsValues().toArray(buttonValues);
					
				}
				else {
					System.out.println("Poll data from gamepad failed.");
					break;
				}
			}
		}
		
		private int[] compareChange(Boolean[] arrA, Boolean[] arrB){
			// Any null will result a failure, and null will be returned.
			// If the arraies does not has the same length, null will be returned too.
			// -1 means the end of comparing result.
			
			if (arrA == null || arrB == null) {
				return null;
			}
			
			for (Boolean b : arrA) {
				if (b == null) {
					return null;
				}
			}
			
			for (Boolean b : arrB) {
				if (b == null) {
					return null;
				}
			}
			
			if (arrA.length != arrB.length) {
				return null;
			}
			
			// 0 in the array means no change, 1 means change.
			int[] changedValue = new int[arrA.length];
			Arrays.fill(changedValue, 0);
			for (int i = 0; i < arrA.length; i++) {
				if (!arrA[i].equals(arrB[i])) {
					changedValue[i] = 1;
				}
			}
			
			return changedValue;
		}
		
	}

}
