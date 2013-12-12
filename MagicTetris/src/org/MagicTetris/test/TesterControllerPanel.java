package org.MagicTetris.test;

import javax.swing.JFrame;

import joystick.JInputJoystick;
import net.java.games.input.Controller;

import org.MagicTetris.UIFragment.*;
import org.MagicTetris.util.ControllerPoller;

public class TesterControllerPanel {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Controller Panel");
		ControllSettingPanel panel = new ControllSettingPanel();
		frame.add(panel);
		JInputJoystick stick = new JInputJoystick(Controller.Type.GAMEPAD);
		ControllerPoller poller = new ControllerPoller(stick, panel);
		frame.pack();
		frame.setVisible(true);
		Thread t = new Thread(poller);
		t.start();

	}

}
