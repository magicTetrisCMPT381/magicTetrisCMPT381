package org.MagicTetris.Models;

import org.MagicTetris.UIFragment.PlayerController;
import org.MagicTetris.util.playerTimer;

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
	private playerTimer timer;
	
	public Player()
	{
		playerStatusPanel = new StatusPanelModel();
		playerBoardPanel = new BoardPanelModel();
		timer = new playerTimer();
		playerController = new PlayerController(playerBoardPanel,timer);
		
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
