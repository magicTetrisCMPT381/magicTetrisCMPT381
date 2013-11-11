package org.MagicTetris.Models;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Models of pattern in Tetris
 * Data in matrix
 * @author Xingze Guo	
 *
 */
public class patternModel extends Applet{
	
	private Integer[][] patternI;
	private Integer[][] patternJ;
	private Integer[][] patternL;
	private Integer[][] patternO;
	private Integer[][] patternS;
	private Integer[][] patternZ;
	private Integer[][] patternT;
	
	
	
	
	
	public patternModel() {
		super();
		this.patternI = new Integer[][]{{0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0},
										{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0},
										{0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0,},
										{0,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0}};
		
//		this.patternJ = patternJ;
//		this.patternL = patternL;
//		this.patternO = patternO;
//		this.patternS = patternS;
//		this.patternZ = patternZ;
//		this.patternT = patternT;
	}
	
	public void paint(Graphics g){
		g.drawRect(20, 20, 30, 30);
		for(int i =0;i<16;i++){
			if(patternI[0][i]==1)
				g.drawRect(10*i, 10*i, 10, 10);
				
		}
	}
	
	public static void main(String[] arog){
		patternModel test = new patternModel();
		JFrame newF = new JFrame();
		JPanel newP = new JPanel();
		newP.setBounds(500, 500, 500, 500);
	//	newP.setVisible(true);
		newF.add(newP);	
		newF.setBounds(0, 0, 500, 500);
		newP.setBackground(Color.red);
		newF.setVisible(true);
		Graphics g = test.getGraphics();
		g.setColor(Color.black);
		test.paint(g);

	}
}
