package org.MagicTetris.test;

import java.util.Timer;

import org.MagicTetris.Models.BoardPanelModel;
import org.MagicTetris.util.playerTimerTask;

public class testTimer {
	private playerTimerTask testTask;
	private BoardPanelModel testB;
	private Timer timer;
	
	public testTimer(){
		testB = new BoardPanelModel();
		testTask = new playerTimerTask(testB);
		timer = new Timer();
	}
	
	public void setTimer(){
		timer.scheduleAtFixedRate(testTask, 0, 1000);
	}
	public static void main(String[] args) {
		testTimer test = new testTimer();
		test.setTimer();
	}
}
