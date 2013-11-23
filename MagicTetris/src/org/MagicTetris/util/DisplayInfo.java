package org.MagicTetris.util;

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class DisplayInfo {
	private final GraphicsDevice displayDevices[];
	public final int screenCount;
	private GraphicsEnvironment graphicsEnvironment;
	private GraphicsDevice defaultDisplayDevice;
	private static volatile DisplayInfo instance;
	
	private DisplayInfo() {
		graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		displayDevices = graphicsEnvironment.getScreenDevices();
		defaultDisplayDevice = graphicsEnvironment.getDefaultScreenDevice();
		screenCount = displayDevices.length;
	}
	
	public static DisplayInfo getInstance() {
		if (instance == null) {
			synchronized (DisplayInfo.class) {
				if (instance == null) {
					instance = new DisplayInfo();
				}
			}
		}
		return instance;
	}
	
	private DisplayMode defaultDisplayMode() {
		return instance.defaultDisplayDevice.getDisplayMode();
	}
	
	public int getDefaultScreenWidth() {
		return instance.defaultDisplayMode().getWidth();
	}
	
	public int getDefaultScreenHeight() {
		return instance.defaultDisplayMode().getHeight();
	}
	
	public int getDefaultScreenRefreshRate() {
		return instance.defaultDisplayMode().getRefreshRate();
	}
	
	public Dimension getDefaultScreenResolution() {
		Dimension d = new Dimension(getDefaultScreenWidth(), getDefaultScreenHeight());
		return d;
	}
	
	public DisplayMode[] getDisplayModes(int screen) {
		if (screen >0 && screen <= screenCount) {
			return instance.displayDevices[screen - 1].getDisplayModes();
		}
		return null;
	}

	public static void main(String[] args) {
		DisplayInfo sys = DisplayInfo.getInstance();
		System.out.println(sys.screenCount);
		System.out.println(sys.getDefaultScreenResolution());
		System.out.println(sys.getDefaultScreenRefreshRate());
	}
}
