package org.MagicTetris.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import joystick.JInputJoystick;

public class ControllerPoller implements Runnable {

	private JInputJoystick stick;
	private Thread thisThread; 
	private Set<ControllerListener> listener;
	public final int CountofControllerButton;
	public ControllerPoller(JInputJoystick stick) {
		this(stick,null);
	}
	
	public ControllerPoller(JInputJoystick stick, ControllerListener listener) {
		stick.pollController();
		this.stick = stick;
		CountofControllerButton = stick.getNumberOfButtons();
		thisThread = Thread.currentThread();
		this.listener = Collections.synchronizedSet(new HashSet<ControllerListener>());
		if (listener != null) {
			this.listener.add(listener);
		}
	}
	
	public void stop() {
		thisThread = null;
	}
	
	public void addListener(ControllerListener listener){
		this.listener.add(listener);
	}
	
	public void removeListener(ControllerListener listener) {
		this.listener.remove(listener);
	}
	
	public void run() {
		stick.pollController();
		Boolean buttonValues[] = new Boolean[CountofControllerButton];
		Boolean newButtonValues[] = new Boolean[CountofControllerButton];
		stick.getButtonsValues().toArray(buttonValues);
		while(thisThread != null){
			if (stick.pollController()) {
				if (!listener.isEmpty()) {
					for (ControllerListener l : listener) {
						l.HatSwitchChanged(stick.getHatSwitchPosition());
						stick.getButtonsValues().toArray(newButtonValues);
						for (int i = 0; i < CountofControllerButton; i++) {
							// If status changed from false to true, then a button is released.
							if (!buttonValues[i] && newButtonValues[i]) {
								l.ButtonPressed(i+1);
							}
							
							// If status changed from true to false, then a button is released.
							if (buttonValues[i] && !newButtonValues[i]) {
								l.ButtonReleased(i+1);
							}					
						}
					}
					
					
					// Update status of buttons
					stick.getButtonsValues().toArray(buttonValues);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
			else {
				System.out.println("Poll data from gamepad failed.");
				break;
			}

		}
	}
	
	
}
