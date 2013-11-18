package org.MagicTetris.test;

import javax.swing.JFrame;

import org.MagicTetris.UIFragment.PlayerController;

public class playerControllerTest extends JFrame{

	private PlayerController controller;
	
	public playerControllerTest(){
		
		controller = new PlayerController();
		this.addKeyListener(controller);
		
		this.setSize(800,800); // set size of windows
		this.setTitle("FakeCrimsoland");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
	

	public static void main(String[] args) {
		
		playerControllerTest test = new playerControllerTest();


	}
}
