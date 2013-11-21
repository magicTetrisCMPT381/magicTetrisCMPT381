package org.MagicTetris.Models;

import org.MagicTetris.UIFragment.PlayerController;
import org.MagicTetris.util.playerTimerTask;


/**
 * Model representing a player.
 * Associated with a StatusPanelModel, a BoardPanelModel and a PlayerController.
 * Any effect caused by item use will be passed to this object first.
 * @author Da
 *
 */
public class Player {
	private StatusPanelModel playerStatusPanel;
	private BoardPanelModel playerBoardPanel;
	private PlayerController playerController;
<<<<<<< HEAD
	private playerTimerTask timer;
	private float playerSpeed;
=======
	private playerTimerTask timerTask;
>>>>>>> origin/Xingze2
	
	public Player()
	{
		playerStatusPanel = new StatusPanelModel();
		playerBoardPanel = new BoardPanelModel();
<<<<<<< HEAD
		timer = new playerTimerTask();
		playerController = new PlayerController(playerBoardPanel,timer);
		playerSpeed = 1;
=======
		timerTask = new playerTimerTask(playerBoardPanel);
		playerController = new PlayerController(playerBoardPanel,timerTask);
>>>>>>> origin/Xingze2
	}

	public void updateGame(){
		
		
	}
	public StatusPanelModel getPlayerStatusPanel() {
		return playerStatusPanel;
	}

	public BoardPanelModel getPlayerBoardPanel() {
		return playerBoardPanel;
	}

	public PlayerController getPlayerController() {
		return playerController;
	}

}
