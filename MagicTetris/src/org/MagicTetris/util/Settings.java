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
	public static void saveSettings(KeySettings settings) {
		File settingFile = new File("Settings.xml");
		Properties properties = new Properties();
		properties.setProperty("ROTATE", String.valueOf(settings.getKEY_ROTATE()));
		properties.setProperty("LEFT", String.valueOf(settings.getKEY_LEFT()));
		properties.setProperty("RIGHT", String.valueOf(settings.getKEY_RIGHT()));
		properties.setProperty("DOWN", KeyEvent.getKeyText(settings.getKEY_DOWN()));
		properties.setProperty("USE_ITEM", String.valueOf(settings.getKEY_USE_ITEM()));
		properties.setProperty("CHANGE_ITEM", String.valueOf(settings.getKEY_CHANGE_ITEM()));
		
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
	public static KeySettings loadSettings() {
		File settingFile = new File("Settings.xml");
		KeySettings settings = null;
		Properties properties = new Properties();
		
		// 
		try {
			properties.loadFromXML(new FileInputStream(settingFile));
		} catch (Exception e) {
			return settings;
		}
		if (settingFile.exists() && settingFile.canRead()) {
			
			// Read settings from file. If a value cannot be found, -1 will be returned.			
			int KEY_ROTATE = Integer.valueOf(properties.getProperty("ROTATE","-1"));
			int KEY_LEFT = Integer.valueOf(properties.getProperty("LEFT","-1"));
			int KEY_RIGHT = Integer.valueOf(properties.getProperty("RIGHT","-1"));
			int KEY_DOWN = Integer.valueOf(properties.getProperty("DOWN","-1"));
			int KEY_CHANGE_ITEM = Integer.valueOf(properties.getProperty("USE_ITEM","-1"));
			int KEY_USE_ITEM = Integer.valueOf(properties.getProperty("CHANGE_ITEM","-1"));
			// Check whether all keys are legal.
			if (KEY_ROTATE > 0 
					&& KEY_LEFT > 0
					&& KEY_RIGHT > 0
					&& KEY_DOWN > 0
					&& KEY_CHANGE_ITEM > 0
					&& KEY_USE_ITEM > 0) {
				
				settings = new KeySettings();
				settings.setKEY_CHANGE_ITEM(KEY_CHANGE_ITEM);
				settings.setKEY_DOWN(KEY_DOWN);
				settings.setKEY_LEFT(KEY_LEFT);
				settings.setKEY_RIGHT(KEY_RIGHT);
				settings.setKEY_ROTATE(KEY_ROTATE);
				settings.setKEY_USE_ITEM(KEY_USE_ITEM);
			}
			
		};
		return settings;
	}
}
