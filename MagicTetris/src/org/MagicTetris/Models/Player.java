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
	private playerTimerTask timer;
	private float playerSpeed;
	
	public Player()
	{
		playerStatusPanel = new StatusPanelModel();
		playerBoardPanel = new BoardPanelModel();
		timer = new playerTimerTask();
		playerController = new PlayerController(playerBoardPanel,timer);
		playerSpeed = 1;
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
