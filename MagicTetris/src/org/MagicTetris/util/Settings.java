package org.MagicTetris.util;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.MagicTetris.UIFragment.PlayerController;

public class Settings {

	/**
	 * Save settings in Settings.xml .
	 * @see KeySettings
	 * @param settings the settings to write
	 */
	public static void saveSettings(KeySettings[] settings) {
		File settingFile = new File("Settings.xml");
		Properties properties = new Properties();
		properties.setProperty("P1_ROTATE", String.valueOf(settings[0].getKEY_ROTATE()));
		properties.setProperty("P1_LEFT", String.valueOf(settings[0].getKEY_LEFT()));
		properties.setProperty("P1_RIGHT", String.valueOf(settings[0].getKEY_RIGHT()));
		properties.setProperty("P1_DOWN", String.valueOf(settings[0].getKEY_DOWN()));
		properties.setProperty("P1_USE_ITEM", String.valueOf(settings[0].getKEY_USE_ITEM()));
		properties.setProperty("P1_CHANGE_ITEM", String.valueOf(settings[0].getKEY_CHANGE_ITEM()));
		
		properties.setProperty("P2_ROTATE", String.valueOf(settings[1].getKEY_ROTATE()));
		properties.setProperty("P2_LEFT", String.valueOf(settings[1].getKEY_LEFT()));
		properties.setProperty("P2_RIGHT", String.valueOf(settings[1].getKEY_RIGHT()));
		properties.setProperty("P2_DOWN", String.valueOf(settings[1].getKEY_DOWN()));
		properties.setProperty("P2_USE_ITEM", String.valueOf(settings[1].getKEY_USE_ITEM()));
		properties.setProperty("P2_CHANGE_ITEM", String.valueOf(settings[1].getKEY_CHANGE_ITEM()));
		
		// try to store settings in XML. Notice user when failed.
		try {
			properties.storeToXML(new FileOutputStream(settingFile), "Settings for Magic Tetris");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, 
					"Unable to save settings to " + settingFile.getAbsolutePath(),
					"Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Get settings in Settings.xml .
	 * Any <i>null</i> return means an error.
	 * @see KeySettings
	 * @return the settings in Settings.xml .
	 */
	public static KeySettings[] loadSettings() {
		File settingFile = new File("Settings.xml");
		KeySettings[] settings = null;
		Properties properties = new Properties();
		
		// 
		try {
			properties.loadFromXML(new FileInputStream(settingFile));
		} catch (Exception e) {
			return settings;
		}
		settings = new KeySettings[2];
		if (settingFile.exists() && settingFile.canRead()) {
			
			// Read settings from file. If a value cannot be found, -1 will be returned.			
			float KEY_ROTATE = Float.valueOf(properties.getProperty("P1_ROTATE","-1"));
			float KEY_LEFT = Float.valueOf(properties.getProperty("P1_LEFT","-1"));
			float KEY_RIGHT = Float.valueOf(properties.getProperty("P1_RIGHT","-1"));
			float KEY_DOWN = Float.valueOf(properties.getProperty("P1_DOWN","-1"));
			float KEY_CHANGE_ITEM = Float.valueOf(properties.getProperty("P1_USE_ITEM","-1"));
			float KEY_USE_ITEM = Float.valueOf(properties.getProperty("P1_CHANGE_ITEM","-1"));
			
			// Check whether all keys are legal.
				
			settings[0] = new KeySettings();
			settings[0].setKEY_CHANGE_ITEM(KEY_CHANGE_ITEM);
			settings[0].setKEY_DOWN(KEY_DOWN);
			settings[0].setKEY_LEFT(KEY_LEFT);
			settings[0].setKEY_RIGHT(KEY_RIGHT);
			settings[0].setKEY_ROTATE(KEY_ROTATE);
			settings[0].setKEY_USE_ITEM(KEY_USE_ITEM);
			
			KEY_ROTATE = Float.valueOf(properties.getProperty("P2_ROTATE","-1"));
			KEY_LEFT = Float.valueOf(properties.getProperty("P2_LEFT","-1"));
			KEY_RIGHT = Float.valueOf(properties.getProperty("P2_RIGHT","-1"));
			KEY_DOWN = Float.valueOf(properties.getProperty("P2_DOWN","-1"));
			KEY_CHANGE_ITEM = Float.valueOf(properties.getProperty("P2_USE_ITEM","-1"));
			KEY_USE_ITEM = Float.valueOf(properties.getProperty("P2_CHANGE_ITEM","-1"));
			
			settings[1] = new KeySettings();
			settings[1].setKEY_CHANGE_ITEM(KEY_CHANGE_ITEM);
			settings[1].setKEY_DOWN(KEY_DOWN);
			settings[1].setKEY_LEFT(KEY_LEFT);
			settings[1].setKEY_RIGHT(KEY_RIGHT);
			settings[1].setKEY_ROTATE(KEY_ROTATE);
			settings[1].setKEY_USE_ITEM(KEY_USE_ITEM);
		};
		return settings;
	}
}
