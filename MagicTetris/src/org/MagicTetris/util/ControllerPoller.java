package org.MagicTetris.util;

import joystick.JInputJoystick;

public class ControllerPoller implements Runnable {

	private JInputJoystick stick;
	private Thread thisThread; 
	private ControllerListener listener;
	public final int CountofControllerButton;
	public ControllerPoller(JInputJoystick stick) {
		this(stick,null);
	}
	
	public ControllerPoller(JInputJoystick stick, ControllerListener listener) {
		
		this.stick = stick;
		CountofControllerButton = stick.getNumberOfButtons();
		thisThread = Thread.currentThread();
		this.listener = listener;
	}
	
	public void stop() {
		thisThread = null;
	}
	
	public void setControlListener(ControllerListener listener){
		this.listener = listener;
	}
	
	public void run() {
		stick.pollController();
		Boolean buttonValues[] = new Boolean[stick.getNumberOfButtons()];
		Boolean newButtonValues[] = new Boolean[stick.getNumberOfButtons()];
		while(thisThread != null){
			if (stick.pollController()) {
				if (listener != null) {
					listener.HatSwitchChanged(stick.getHatSwitchPosition());
					stick.getButtonsValues().toArray(newButtonValues);
					for (int i = 0; i < CountofControllerButton; i++) {
						// If status changed from false to true, then a button is released.
						if (!buttonValues[i] && newButtonValues[i]) {
							listener.ButtonPressed(i+1);
						}
						
						// If status changed from true to false, then a button is released.
						if (buttonValues[i] && !newButtonValues[i]) {
							listener.ButtonReleased(i+1);
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
