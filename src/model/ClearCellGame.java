package model;

import java.awt.Color;
import java.util.Random;
import java.lang.Math;

/* This class must extend Game */
public class ClearCellGame extends Game{
	
	private int strategy, score;
	private java.util.Random random;
	
	public ClearCellGame(int maxRows, int maxCols, java.util.Random random, int strategy){
		//calls the super class's constructor with maxRows and maxCols
		super(maxRows, maxCols);
		
		//sets private strategy and random variables
		this.strategy = strategy;
		this.random = random;
		
		//sets beginning score to 0 
		this.score = 0;
	}

	@Override
	public boolean isGameOver() {
		//checks if last row is empty
		//return isRowEmpty(board[board.length - 1]) ? false:true;
		for(BoardCell cell: board[board.length - 1]) {
			if(cell.getColor() == Color.WHITE) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void nextAnimationStep() {
		
		//checks if last row in board is empty
        if (!isGameOver()) {
        	//moves rows down
        	for(int rowNum = board.length - 1; rowNum > 0; rowNum--) {
	        	for(int colNum = 0; colNum < board[0].length; colNum++) {
	        		board[rowNum][colNum] = board[rowNum - 1][colNum];
	        	}
	        }
			//fills first row in board with random cells
			for(int colNum = 0; colNum < board[0].length; colNum++) { 
				board[0][colNum] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
		} 
	}

	@Override
	public void processCell(int rowIndex, int colIndex) {
		
		//if game is over, nothing happens
		if(isGameOver()) {
			return;
		}
		
		//sets instance variable target cell's color because it's easier to type than getting the object from 
		//board reference every line
		Color targetColor = board[rowIndex][colIndex].getColor();
		
		//if target cell is empty, nothing happens
		if(targetColor == Color.WHITE) {
			return;
		}
		
		//propagates leftward from target cell 
		for(int leftShift  =  0; leftShift <= colIndex; leftShift++) {
			if(board[rowIndex][colIndex - leftShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex][colIndex - leftShift] = BoardCell.EMPTY;
			this.score++;
		}
		
		//propagates rightward from target cell 
		for(int rightShift  =  1; colIndex + rightShift < board[0].length; rightShift++) {
			if(board[rowIndex][colIndex + rightShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex][colIndex + rightShift] = BoardCell.EMPTY;
			this.score++;
		}		
		
		//propagates downward from target cell
		for(int downShift = 1; downShift <= rowIndex; downShift++) {
			if(board[rowIndex - downShift][colIndex].getColor() != targetColor) {
				break;
			}
					
			board[rowIndex - downShift][colIndex] = BoardCell.EMPTY;
			this.score++;
		}
				
		//propagates upward from target cell
		for(int upShift = 1; rowIndex + upShift < board.length; upShift++) {
			if(board[rowIndex + upShift][colIndex].getColor() != targetColor) {
				break;
			}
					
			board[rowIndex + upShift][colIndex] = BoardCell.EMPTY;
			this.score++;
		}
		
		//propagates up and to the right from the target cell
		for(int upRightShift = 1; upRightShift <= rowIndex && colIndex + upRightShift < board[0].length; upRightShift++) {
			if(board[rowIndex - upRightShift][colIndex + upRightShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex - upRightShift][colIndex + upRightShift] = BoardCell.EMPTY;
			this.score++;
		}
		
		//propagates up and to the left from the target cell
		for(int upLeftShift = 1; upLeftShift <= rowIndex && upLeftShift <= colIndex; upLeftShift++) {
			if(board[rowIndex - upLeftShift][colIndex - upLeftShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex - upLeftShift][colIndex - upLeftShift] = BoardCell.EMPTY;
			this.score++;
		}
		
		//propagates down and to the right from the target cell 
		for(int downRightShift = 1; rowIndex + downRightShift < board.length && colIndex + downRightShift < board[0].length; downRightShift++) {
			if(board[rowIndex + downRightShift][colIndex + downRightShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex + downRightShift][colIndex + downRightShift] = BoardCell.EMPTY;
			this.score++;
		}
		
		//propagates down and to the left from the target cell
		for(int downLeftShift = 1; rowIndex + downLeftShift < board.length && downLeftShift <= colIndex; downLeftShift++) {
			if(board[rowIndex + downLeftShift][colIndex - downLeftShift].getColor() != targetColor) {
				break;
			}
			
			board[rowIndex + downLeftShift][colIndex - downLeftShift] = BoardCell.EMPTY;
			this.score++;
		}
		
		shiftRows();
	}
	
	private void shiftRows() {
		//checks for empty rows and moves accordingly
		for(int rowNum = 0; rowNum < board.length; rowNum++) {
			
			//by default, boolean to check if row is empty is set to true
			boolean allEmpty = true;
					
			//checks every cell in row, to check if any are not empty
			for(BoardCell cellInRow: board[rowNum]) {
				if(cellInRow.getColor() != Color.WHITE) {
					allEmpty = false;
				}
			}
					
			//if entire row is empty, and not the last row on the board, sets each cell to its
			//equivalent in the next row, and sets next row to empty
			if(allEmpty && rowNum != board.length - 1) {
				for(int colNum = 0; colNum < board[0].length; colNum++) {
					board[rowNum][colNum] = board[rowNum + 1][colNum];
					board[rowNum + 1][colNum] = BoardCell.EMPTY;
				}
			}
		}
	}
}