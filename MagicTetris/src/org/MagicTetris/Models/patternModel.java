package org.MagicTetris.Models;

import java.awt.Color;

/**
 * Models of pattern in Tetris
 * Data in matrix
 * @author Xingze Guo	
 *
 */
public class patternModel{
	
	public static final Integer[][] patternI = new Integer[][]{
			{
				 0,0,0,0,
				 1,1,1,1,
				 0,0,0,0,
				 0,0,0,0
				},
				
				{
				0,0,1,0,
				0,0,1,0,
				0,0,1,0,
				0,0,1,0
				},
				
				{
				0,0,0,0,
				0,0,0,0,
				1,1,1,1,
				0,0,0,0,
				},
			
				{
				0,1,0,0,
				0,1,0,0,
				0,1,0,0,
				0,1,0,0
				
			}};
	public static final Integer[][] patternJ = new Integer[][]{
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
	public static final Integer[][] patternL= new Integer[][]{
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
	public static final Integer[][] patternO = new Integer[][]{
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
	public static final Integer[][] patternS = new Integer[][]{
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
	public static final Integer[][] patternZ = new Integer[][]{
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
	public static final Integer[][] patternT = new Integer[][]{
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
	public static final Integer[][] patternOdd_1 = new Integer[][]{
		{
			 0,1,0,0,
			 1,1,1,1,
			 1,0,1,0,
			 1,0,0,0
			},
			
			{
			0,1,0,0,
			1,1,1,1,
			1,0,1,0,
			1,0,0,0
			},
			
			{
			0,1,0,0,
			1,1,1,1,
			1,0,1,0,
			1,0,0,0
			},
		
			{
			0,1,0,0,
			1,1,1,1,
			1,0,1,0,
			1,0,0,0
		}};
	public static final Integer[][] patternOdd_2 = new Integer[][]{
		{
			 1,0,1,0,
			 0,1,1,0,
			 0,1,0,0,
			 0,1,0,1
			},
			
			{
			1,0,1,0,
			0,1,1,0,
			0,1,0,0,
			0,1,0,1
			},
			
			{
			1,0,1,0,
			0,1,1,0,
			0,1,0,0,
			0,1,0,1
			},
		
			{
			1,0,1,0,
			0,1,1,0,
			0,1,0,0,
			0,1,0,1
		}};
	
	public static final Color colorI = new Color(139,0,255); // This is purple.
	public static final Color colorJ = Color.GREEN;
	public static final Color colorL = Color.RED;
	public static final Color colorO = Color.YELLOW;
	public static final Color colorS = Color.CYAN;
	public static final Color colorZ = Color.ORANGE;
	public static final Color colorT = Color.MAGENTA;	

}
