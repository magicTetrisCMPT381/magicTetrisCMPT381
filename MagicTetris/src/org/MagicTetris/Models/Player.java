package org.MagicTetris.Models;

import java.util.Timer;

import org.MagicTetris.UIFragment.BoardPanel;
import org.MagicTetris.UIFragment.PlayerController;
import org.MagicTetris.UIFragment.StatusPanel;
import org.MagicTetris.util.playerTimerTask;


/**
 * Model representing a player.
 * Associated with a StatusPanelModel, a BoardPanelModel and a PlayerController.
 * Any effect caused by item use will be passed to this object first.
 * @author Da
 *
 */
public class Player {
	private StatusPanel statusPanel;
	private BoardPanel boardPanel;
	private StatusPanelModel statusPanelModel;
	private BoardPanelModel boardPanelModel;
	private PlayerController playerController;
	private Timer timer;
	private playerTimerTask timerTask;

	
	public Player()
	{
		statusPanel = new StatusPanel();
		boardPanel = new BoardPanel();
		
		statusPanelModel = new StatusPanelModel();
		boardPanelModel = new BoardPanelModel();

		statusPanel.setModel(statusPanelModel);
		boardPanel.setModel(boardPanelModel);

		timerTask = new playerTimerTask(boardPanel,boardPanelModel);
		playerController = new PlayerController(boardPanelModel,timerTask,this);

	}
	
	public void startGame(){
		timer = new Timer();
		setTimer();
	}
	
	public void setTimer() {
		timer.cancel();
		timerTask = new playerTimerTask(boardPanel, boardPanelModel);
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, (long) (1000 / playerController.getCurrentSpeed()));
	}


	public PlayerController getPlayerController() {
		return playerController;
	}

	public StatusPanel getStatusPanel() {
		return statusPanel;
	}

	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	public StatusPanelModel getStatusPanelModel() {
		return statusPanelModel;
	}

	public BoardPanelModel getBoardPanelModel() {
		return boardPanelModel;
	}

	public playerTimerTask getTimerTask() {
		return timerTask;
	}

}
