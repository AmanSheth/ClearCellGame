package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */

public abstract class Game extends java.lang.Object{
	protected BoardCell[][] board;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		board = new BoardCell[maxRows][maxCols];
		
		//iterates through rows
		for(BoardCell[] row: board) {
			//iterates through cells in row
			for(int col = 0; col < maxCols; col++) {
				row[col] = BoardCell.EMPTY;
			}
		}
	}

	//returns the number of rows 
	public int getMaxRows() {
		return board.length;
	}

	//returns the number of columns
	public int getMaxCols() {
		return board[0].length;
	}

	//sets the cell at given coordinates in board array to the given boardCell object
	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		board[rowIndex][colIndex] = boardCell;
	}

	//returns the boardCell object at the given coordinates from board array 
	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * @param rowIndex
	 * @param cell
	 */
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for(int col = 0; col < board[0].length; col++) {
			board[rowIndex][col] = cell;
		}
		
	}
	
	/**
	 * Initializes column with the specified color.
	 * @param colIndex
	 * @param cell
	 */
	public void setColWithColor(int colIndex, BoardCell cell) {
		for(BoardCell[] row: board) {
			row[colIndex] = cell;
		}
	}
	
	/**
	 * Initializes the board with the specified color.
	 * @param cell
	 */
	public void setBoardWithColor(BoardCell cell) {
		for(BoardCell[] row: board) {
			for(int col = 0; col < board[0].length; col++) {
				row[col] = cell;
			}
		}
	}	
	
	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the
	 * selected cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}