package org.MagicTetris.Models;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.plaf.LayerUI;

import org.MagicTetris.GameItems.MagicItem;
import org.MagicTetris.UIFragment.BoardPanel;
import org.MagicTetris.UIFragment.EffectLayer;
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
	private Timer effectTimer;
	private playerTimerTask timerTask;
	private float speed;
	private Player opponent;
	private boolean isProtected;
	public final JLayer<JComponent> playerBoard;
	private EffectLayer effectLayer;
	
	public Player()
	{
		statusPanel = new StatusPanel();
		boardPanel = new BoardPanel();
		speed = 1;
		
		statusPanelModel = new StatusPanelModel();
		statusPanelModel.setSpeed(speed);
		boardPanelModel = new BoardPanelModel();

		statusPanel.setModel(statusPanelModel);
		boardPanel.setModel(boardPanelModel);

		effectLayer = new EffectLayer();
		playerBoard = new JLayer<JComponent>(boardPanel, effectLayer);
		
		effectTimer = new Timer(true);
		
		timerTask = new playerTimerTask(this);
		playerController = new PlayerController(boardPanelModel,timerTask,this);
		
		isProtected = false;

	}
	
	public void startGame(){
		timer = new Timer();
		setTimer();
	}
	
	public void pauseGame() {
		if (timer != null) {
			timer.cancel();
		}
		
	}
	
	public void setTimer() {
		if (!boardPanelModel.isGameOver()) {
			timer.cancel();
			timerTask = new playerTimerTask(this);
			timer = new Timer();
			timer.scheduleAtFixedRate(timerTask, 0, (long) (1000 / playerController.getCurrentSpeed()));
		}
	}
	
	public void gameOver() {
		if (timer != null) {
			timer.cancel();
			timer.purge();
		}
		
		boardPanelModel.reset();
		boardPanel.repaint();
	}
	
	public void useItem() {
		MagicItem item = statusPanelModel.useItem();
		if (item == null) {
			return;
		}
		switch (item.itemType) {
		case BUFF:
			isProtected = true;
			statusPanelModel.setBuff(item);
			effectLayer.setItem(item);
			break;
		case BOMB:
			item.changeBoardModel(boardPanelModel);
			effectLayer.setItem(item);
			break;
		case ENEMY_BOMB:
		case DEBUFF:
		case FREEZER:
			opponent.doItemEffect(item);
			break;
			
		default:
			break;
		}
		
	}
	
	public void changeItem() {
		statusPanelModel.changeItem();
	}
	
	public void doItemEffect(MagicItem item) {
		if (item == null) {
			return;
		}
		if (isProtected) {
			effectLayer.setItem(null);
			statusPanelModel.setBuff(null);
			isProtected = false;
			return;
		}
		switch (item.itemType) {
		case ENEMY_BOMB:
			item.changeBoardModel(boardPanelModel);
			effectLayer.setItem(item);
			effectTimer.schedule(new effectTimerTask(), new Date(System.currentTimeMillis() + item.getEffectTime()));
			break;
			
		case DEBUFF:
			item.changeBoardModel(boardPanelModel);
			item.changeStatusModel(statusPanelModel);
			effectLayer.setItem(item);
			effectTimer.schedule(new effectTimerTask(), new Date(System.currentTimeMillis() + item.getEffectTime()));
			break;
			
		case FREEZER:
			item.changeBoardModel(boardPanelModel);
			effectLayer.setItem(item);
			effectTimer.schedule(new effectTimerTask(), new Date(System.currentTimeMillis() + item.getEffectTime()));
			break;
			
		default:
			break;
		}
		item.drawEffect(boardPanel.getGraphics());
	}
	
	public void addItem(MagicItem item){
		statusPanelModel.addItem(item);
		statusPanel.update();
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

	public float getSpeed() {
		return speed;
	}
	
	public void setOpponent(Player opponent){
		this.opponent = opponent;
	}
	
	public Player getOpponent() {
		return opponent;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
		setTimer();
		statusPanelModel.setSpeed(speed);
	}
	
	private class effectTimerTask extends TimerTask{
		@Override
		public void run() {
			effectLayer.setItem(null);
			statusPanelModel.setDebuff(null);
		}
		
	}

}
