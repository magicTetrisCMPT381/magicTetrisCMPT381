package org.MagicTetris.Models;

import java.awt.Color;
import java.util.Random;

import org.MagicTetris.GameItems.MagicItem;
import org.MagicTetris.GameItems.MagicItemType;

/**
 * Model for BoardPanel.
 * Stores panel data and receive the effect from item use.
 * @author Da
 *
 */
public class BoardPanelModel {

	/**
	 * The number of columns on the board.
	 */
	public static final int COLUMN_COUNT = 12;
	/**
	 * The number of visible rows on the board.
	 */
	public static final int VISIBLE_ROW_COUNT = 24;
	/**
	 * The number of hidden rows on the board.
	 * For pre-draw blocks.
	 */
	public static final int HIDDEN_ROW_COUNT = 2;
	/**
	 * Total rows on the board.
	 */
	public static final int TOTAL_ROW_COUNT = VISIBLE_ROW_COUNT + HIDDEN_ROW_COUNT;
	
	/**
	 * Random number creater.
	 */
	private Random random;
	/**
	 * The board.
	 */
	private SingleBlock[][] board;
	
	/**
	 * Current moving piece in the board.
	 */
	private Integer[][] currentPiece;
	
	/**
	 * The next piece.
	 */
	private Integer[][] nextPiece;
	
	/**
	 * Current rotate of moving piece in the board.
	 */
	private int currentPieceRotate;

	/**
	 * Current color of moving piece in the board.
	 */
	private Color currentPieceColor;
	
	/**
	 * The column of current piece.
	 */
	private int currentPieceCol = -1;
	
	/**
	 * The row of current iece.
	 */
	private int currentPieceRow = -1;
	
	/**
	 * The color of next piece.
	 */
	private Color nextPieceColor;
	
	private Player player;
	
	private boolean isGameOver;
	
	private MagicItem item;
	
	public BoardPanelModel() {
		random = new Random();
		board = new SingleBlock[TOTAL_ROW_COUNT][COLUMN_COUNT];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = new SingleBlock();
			}
		}
	}
	/**
	 * Represents the status of each block in the board.
	 * If a block is frozen, it must be cleared twice to be fully cleared
	 * @author Da
	 *
	 */
	public class SingleBlock{
		/**
		 * Is the point occupied by any block.
		 */
		private boolean isOccupied;
		/**
		 * Is the point frozen( could not be cleared in one time ).
		 */
		private boolean isFrozen;
		
		/**
		 * The block's color.
		 */
		private Color color;
		
		public SingleBlock() {
			isOccupied = false;
			isFrozen = false;
			color = Color.BLACK;
		}
		/**
		 * Return whether this block is occupied.
		 * @return true if this block is occupied.
		 */
		public boolean isOccupied() {
			return isOccupied;
		}
		/**
		 * Set the occupying status of this block.
		 * @param isOccupied
		 */
		public void setOccupied(boolean isOccupied) {
			this.isOccupied = isOccupied;
		}
		/**
		 * Return whether this block is frozen.
		 * A frozen block must be cleared twice to be unoccupied.
		 * @return true if this block is frozen.
		 */
		public boolean isFrozen() {
			return isFrozen;
		}
		/**
		 * Set the frozen status of this block.
		 * A frozen block will be in light blue.
		 * @param isOccupied
		 */
		public void setFrozen(boolean isFrozen) {
			this.isFrozen = isFrozen;
			this.isOccupied = true;
			this.color = Color.BLUE.brighter();
		}
		/**
		 * Reset this block to unoccupied and not frozen.
		 */
		public void reset() {
			this.isFrozen=false;
			this.isOccupied=false;
			this.color = Color.BLACK;
		}
		/**
		 * Clear this block.
		 * A frozen block must be cleared twice to be unoccupied.
		 */
		public void clear() {
			if (isFrozen) {
				isFrozen=false;
				color = Color.GRAY;
			} else {
				isOccupied=false;
			}
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
	}

	/**
	 * Get an array representing the board
	 * @return an array representing the board
	 */
	public SingleBlock[][] getBoard() {
		return board;
	}
	
	/**
	 * Get the next piece.
	 * @return the next piece.
	 */
	public Integer[][] getNextPiece(){
		return nextPiece;
	}
	
	public Integer[][] createNextPiece() {
		int next = random.nextInt(7);
		switch (next) {
		case 1:
			this.nextPieceColor = patternModel.colorJ;
			return patternModel.patternJ;
		case 2:
			this.nextPieceColor = patternModel.colorL;
			return patternModel.patternL;
		case 3:
			this.nextPieceColor = patternModel.colorO;
			return patternModel.patternO;
		case 4:
			this.nextPieceColor = patternModel.colorS;
			return patternModel.patternS;
		case 5:
			this.nextPieceColor = patternModel.colorT;
			return patternModel.patternT;
		case 6:
			this.nextPieceColor = patternModel.colorZ;
			return patternModel.patternZ;
		default:
			this.nextPieceColor = patternModel.colorI;
			return patternModel.patternI;
		}
	}

	public void setNextPiece(Integer[][] nextPiece) {
		this.nextPiece = nextPiece;
	}
	
	/**
	 * Spawn next piece.
	 * The next piece will be place at (0,5) .
	 */
	public void spawnNextPiece() {
		this.currentPiece = this.nextPiece;
		this.currentPieceColor = this.nextPieceColor;
		this.currentPieceCol = 5;
		this.currentPieceRow = 0;
		this.setNextPiece(createNextPiece());
	}

	/**
	 * Get the current piece.
	 * @return the current piece.
	 */
	public Integer[][] getCurrentPiece() {
		return currentPiece;
	}

	/**
	 * Get the rotate of current piece.
	 * @return the rotate of current piece.
	 */
	public int getCurrentPieceRotate() {
		return currentPieceRotate;
	}

	/**
	 * Rotate current piece.
	 * 
	 */
	public void rotateCurrentPiece(){
		if (isGameOver) {
			return;
		}
		// The piece only has four rotate statuses.
		int rotate = (currentPieceRotate +1) % 4;
		// Check if we are able to rotate this block.
		boolean ableToRotate = checkPosition(currentPiece, currentPieceCol, currentPieceRow, rotate);
		if (ableToRotate) {
			currentPieceRotate = (currentPieceRotate +1) % 4;
		}
		
	}
	

	
	public void moveCurrentPieceLeft() {
		if (isGameOver) {
			return;
		}
		boolean ableToMove = checkPosition(currentPiece, 
				currentPieceCol - 1 , 
				currentPieceRow, 
				currentPieceRotate);
		if (ableToMove) {
			currentPieceCol -= 1;
		}
		
	}
	
	public void moveCurrentPieceRight() {
		if (isGameOver) {
			return;
		}
		boolean ableToMove = checkPosition(currentPiece, 
				currentPieceCol + 1 , 
				currentPieceRow, 
				currentPieceRotate);
		if (ableToMove) {
			currentPieceCol += 1;
		}
	}
	
	public synchronized void moveCurrentPieceDown(){
		if (isGameOver) {
			return;
		}
		boolean ableToMove = checkPosition(currentPiece, 
				currentPieceCol, 
				currentPieceRow + 1, 
				currentPieceRotate);
		// Drop the piece if it is able to move down.
		if (ableToMove) {
			currentPieceRow += 1;
		}
		// If not able to move down, the piece must hit something. Add this piece and release next piece.
		else {
			int[][] points = getPoints(currentPiece, currentPieceRotate, currentPieceRow, currentPieceCol);
			isGameOver = checkGameOver(points);
			if (isGameOver) {
				player.gameOver();
				return;
			}
			
			if (item != null) {
				if (item.itemType != MagicItemType.BOMB) {
					addPieceToBoard(currentPiece, currentPieceColor, currentPieceRotate);
				}
				else {
					item.changeBoardModel(this);
				}
				
			}
			else {
				addPieceToBoard(currentPiece, currentPieceColor, currentPieceRotate);
			}
			if (nextPiece == null) {
				this.nextPiece = createNextPiece();
			}
			spawnNextPiece();
		}
	}
	

	private boolean checkGameOver(int[][] points) {
		for (int i = 0; i < 4; i++) {
			if (points[i][0] <= 1) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * Add a piece to board. Will not check position.
	 * @param piecePattern the piece's pattern.
	 * @param pieceColor the piece's color. 
	 * @param pieceCoord the piece's coordinate.
	 * @param rotate the piece's rotate.
	 */
	public void addPieceToBoard(Integer[][] piecePattern, Color pieceColor,  int rotate) {
		Integer[] piece = piecePattern[rotate];
		for (int i = 0; i < 16; i++) {
			if (piece[i] == 1) {
				board[currentPieceRow + i / 4]
					[currentPieceCol + i % 4]
							.setOccupied(true);
				board[currentPieceRow + i / 4]
					[currentPieceCol + i % 4]
							.setColor(pieceColor);
			}
			
		}

	}

	/**
	 * Check if a piece could be placed at designated position.
	 * The designated position is represented by the piece's top-left corner.
	 * @param piece the piece.
	 * @param col the column to put the piece
	 * @param row the row to put the piece
	 * @param rotate the rotate status of the piece.
	 * @return
	 */
	public synchronized boolean checkPosition(Integer[][] piece, int col, int row, int rotate) {
//		// check if the piece is moving out of board.
//		if (col >= COLUMN_COUNT || col < 0 ||
//				row >= TOTAL_ROW_COUNT || row < 0) {
//			return false;
//		}
//		
//		// check if the given position is legal.
//		if (board[row][col].isOccupied && piece[rotate][0] == 1) {
//			return false;
//		}
		
		int[][] coordToCheck = getPoints(piece, rotate, row, col);
//		for (int[] is : coordToCheck) {
//			System.out.println("检查点：行" + is[0] + "，列" + is[1]);
//		}
//		System.out.println("----------");
		for (int[] is : coordToCheck) {
			
			
			if (is[1] >= COLUMN_COUNT || is[1] < 0 ||
					is[0] >= TOTAL_ROW_COUNT  || is[0] < 0) {
//				System.out.println("检查边框: row " + is[0] +", col " + is[1] );
				return false;
			}
			
			if (board[is[0]][is[1]].isOccupied) {
//				System.out.println("检查占用: row " + is[0] +", col " + is[1] );
				return false;
			}
		}
		return true;
		
	}
	
	/**
	 * Check every lines in the board, and try to clear the full lines.
	 */
	public int clearLines(){
		int clearedLine = 0;
		for (int line = 0; line < TOTAL_ROW_COUNT; line++) {
				if (clearLine(board[line], line)) {
					clearedLine += 1;
				}
		}
		return clearedLine;
	}
	
	private boolean clearLine(SingleBlock[] lineBlocks,int line) {
		// Check if all blocks in that line are occupied.
		for (SingleBlock b : lineBlocks) {
			if (!b.isOccupied()) {
				return false;
			}
		}
		// Clear blocks in line.
		for (SingleBlock b : lineBlocks) {
			b.clear();
		}
		// Check if all blocks are cleared.
		for (SingleBlock b : lineBlocks) {
			if (b.isOccupied()) {
				return false;
			}
		}
		// Shift all rows above this line down.
		for (int row = line - 1; row >= 0; row--) {
			for (int col = 0; col < COLUMN_COUNT; col++) {
				board[row+1][col].isFrozen = board[row][col].isFrozen;
				board[row+1][col].isOccupied = board[row][col].isOccupied;
				board[row+1][col].color = board[row][col].color;
			}
		}
		return true;
	}
	
	private synchronized int[][] getPoints(Integer[][] pattern, int rotate, int row, int col){
		int[][] coordToCheck = new int[16][2];
		int coordGroupNo = 0;
		for (int i = 0; i < 16; i++) {
			if (pattern[rotate][i] == 1) {
				// i / 4 is row number;
				// i % 4 is column number.
				coordToCheck[coordGroupNo][0] = i / 4;
				coordToCheck[coordGroupNo][1] = i % 4;
				coordGroupNo++;
			}
		}
		for (int i = 0; i < coordToCheck.length; i++) {
			coordToCheck[i][0] += row;
			coordToCheck[i][1] += col;
		}
		return coordToCheck;
	}

	public void reset() {
		for (SingleBlock[] lineBlocks : board) {
			for (SingleBlock block : lineBlocks) {
				block.reset();				
			}
		}
		
		currentPiece = null;
		currentPieceCol = -1;
		currentPieceRow = -1;
		currentPieceColor = null;
		currentPieceRotate = 0;
		nextPiece = null;
		nextPieceColor = null;
	}
	public Color getCurrentPieceColor() {
		return currentPieceColor;
	}

	public Color getNextPieceColor() {
		return nextPieceColor;
	}

	public void setCurrentPiece(Integer[][] currentPiece) {
		this.currentPiece = currentPiece;
	}

	public void setCurrentPieceColor(Color currentPieceColor) {
		this.currentPieceColor = currentPieceColor;
	}

	public void setNextPieceColor(Color nextPieceColor) {
		this.nextPieceColor = nextPieceColor;
	}


	public int getCurrentPieceCol() {
		return currentPieceCol;
	}

	public void setCurrentPieceCol(int currentPieceCol) {
		this.currentPieceCol = currentPieceCol;
	}

	public int getCurrentPieceRow() {
		return currentPieceRow;
	}

	public void setCurrentPieceRow(int currentPieceRow) {
		this.currentPieceRow = currentPieceRow;
	}

	public boolean isGameOver() {
		return isGameOver;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public MagicItem getItem() {
		return item;
	}
	
	
}
