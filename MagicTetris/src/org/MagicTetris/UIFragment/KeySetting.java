package org.MagicTetris.UIFragment;

import org.MagicTetris.util.KeySettings;

public interface KeySetting {
	public float[] keySettings();
	public void loadFromKeySettings(KeySettings keys);
}
