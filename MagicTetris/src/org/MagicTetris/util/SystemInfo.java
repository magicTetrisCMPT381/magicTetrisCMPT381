package org.MagicTetris.util;

import java.awt.Dimension;

import joystick.JInputJoystick;
import net.java.games.input.*;

public class SystemInfo {
	
	private JInputJoystick stick;
	private DisplayInfo displays;
	public final boolean isControllerExist;
	
	public SystemInfo() {
		stick = new JInputJoystick(Controller.Type.GAMEPAD);
		isControllerExist = stick.isControllerConnected();
		displays = DisplayInfo.getInstance();
	}
	
	public boolean isXboxControllerExists(){
		return stick.isControllerConnected();
	}
	
	public Dimension DefaultScreenResolution() {
		return displays.getDefaultScreenResolution();
	}
	
	public int DefaultScreenHeight() {
		return displays.getDefaultScreenHeight();
	}
	
	public int DefaultScreenWidth() {
		return displays.getDefaultScreenWidth();
	}

}
