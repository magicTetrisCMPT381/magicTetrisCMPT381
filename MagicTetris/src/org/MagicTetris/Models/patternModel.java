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
		this.patternI = new Integer[][]{
			{
			 0,0,0,0,
			 1,1,1,1,
			 0,0,0,0,
			 0,0,0,0
			},
			
			{
			0,1,0,0,
			0,1,0,0,
			0,1,0,0,
			0,1,0,0
			},
			
			{
			0,0,0,0,
			1,1,1,1,
			0,0,0,0,
			0,0,0,0,
			},
		
			{
			0,1,0,0,
			0,1,0,0,
			0,1,0,0,
			0,1,0,0
		}};

//		pattern J
			this.patternJ = new Integer[][]{
			{
			 0,1,0,0,
			 0,1,0,0,
			 1,1,0,0,
			 0,0,0,0
			},
			
			{
			1,0,0,0,
			1,1,1,0,
			0,0,0,0,
			0,0,0,0
			},
			
			{
			1,1,0,0,
			1,0,0,0,
			1,0,0,0,
			0,0,0,0,
			},
		
			{
			1,1,1,0,
			0,0,1,0,
			0,0,0,0,
			0,0,0,0
		}};

//		pattern L
			this.patternL = new Integer[][]{
			{
			 1,0,0,0,
			 1,0,0,0,
			 1,1,0,0,
			 0,0,0,0
			},
			
			{
			1,1,1,0,
			1,0,0,0,
			0,0,0,0,
			0,0,0,0
			},
			
			{
			1,1,0,0,
			0,1,0,0,
			0,1,0,0,
			0,0,0,0,
			},
		
			{
			0,0,1,0,
			1,1,1,0,
			0,0,0,0,
			0,0,0,0
		}};
		
//		pattern O
			this.patternO = new Integer[][]{
			{
			 1,1,0,0,
			 1,1,0,0,
			 0,0,0,0,
			 0,0,0,0
			},
			
			{
			1,1,0,0,
			1,1,0,0,
			0,0,0,0,
			0,0,0,0
			},
			
			{
			1,1,0,0,
			1,1,0,0,
			0,0,0,0,
			0,0,0,0,
			},
		
			{
			1,1,0,0,
			1,1,0,0,
			0,0,0,0,
			0,0,0,0
		}};

//		pattern S
			this.patternS = new Integer[][]{
			{
			 0,1,1,0,
			 1,1,0,0,
			 0,0,0,0,
			 0,0,0,0
			},
			
			{
			1,0,0,0,
			1,1,0,0,
			0,1,0,0,
			0,0,0,0
			},
			
			{
			0,1,1,0,
			1,1,0,0,
			0,0,0,0,
			0,0,0,0,
			},
		
			{
			1,0,0,0,
			1,1,0,0,
			0,1,0,0,
			0,0,0,0
		}};


//		pattern Z
			this.patternZ = new Integer[][]{
			{
			 1,1,0,0,
			 0,1,1,0,
			 0,0,0,0,
			 0,0,0,0
			},
			
			{
			0,1,0,0,
			1,1,0,0,
			1,0,0,0,
			0,0,0,0
			},
			
			{
			1,1,0,0,
			0,1,1,0,
			0,0,0,0,
			0,0,0,0,
			},
		
			{
			0,1,0,0,
			1,1,0,0,
			1,0,0,0,
			0,0,0,0
		}};

//		pattern T
			this.patternO = new Integer[][]{
			{
			 0,1,0,0,
			 1,1,1,0,
			 0,0,0,0,
			 0,0,0,0
			},
			
			{
			0,1,0,0,
			1,1,0,0,
			0,1,0,0,
			0,0,0,0
			},
			
			{
			1,1,1,0,
			0,1,0,0,
			0,0,0,0,
			0,0,0,0,
			},
		
			{
			0,1,0,0,
			0,1,1,0,
			0,1,0,0,
			0,0,0,0
		}};


	}





	public Integer[][] getPatternI() {
		return patternI;
	}





	public Integer[][] getPatternJ() {
		return patternJ;
	}





	public Integer[][] getPatternL() {
		return patternL;
	}





	public Integer[][] getPatternO() {
		return patternO;
	}





	public Integer[][] getPatternS() {
		return patternS;
	}





	public Integer[][] getPatternZ() {
		return patternZ;
	}





	public Integer[][] getPatternT() {
		return patternT;
	}
	

}
