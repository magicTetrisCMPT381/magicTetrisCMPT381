package org.MagicTetris.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.MagicTetris.Models.patternModel;

public class patternModelTest extends JPanel{
	
	private patternModel test = new patternModel();
	public patternModelTest() {
		super();
		this.setBounds(10, 10, 500, 500);
	}

	
	
	
	
	 public void paintComponent(Graphics g){
		 Integer[][] patternJ = test.getPatternJ();
//		 g.drawRect(20, 20, 30, 30);
	 	for(int i =0;i<16;i++){
	 		if(patternJ[0][i]==1){
	 			
	 			if(i<4)
	 				g.drawRect(10*i, 10, 10, 10);
	 			else if(i<8)
	 				g.drawRect(10*(i-4), 20, 10, 10);
	 			else if(i<12)
	 				g.drawRect(10*(i-8), 30, 10, 10);
	 			else if(i<16)
	 				g.drawRect(10*(i-12), 40, 10, 10);
	 		}
	 			
				
	 	}
	 }
	
	 public static void main(String[] arog){
	 	
	 	JFrame newF = new JFrame();
	 	patternModelTest newP = new patternModelTest();

	 	newF.add(newP);	
	 	newF.setBounds(0, 0, 500, 500);
	 	newP.setBackground(Color.white);
	 	newF.setVisible(true);
	 	newP.repaint();
//	 	newP.paint(Graphics g);
//	 	Graphics g = test.getGraphics();
// 	    g.setColor(Color.black);
//	 	test.paint(g);

	 }
}
