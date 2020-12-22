package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

public class StudentTests {

	@Test
	public void test() {
		int maxRows = 8, maxCols = 8, strategy = 1;
		Game ccGame = new ClearCellGame(maxRows, maxCols, new Random(1L), strategy);
	   
		ccGame.setBoardWithColor(BoardCell.BLUE);
		ccGame.processCell(3,3);
		System.out.println(getBoardStr(ccGame));
	}

	/* Support methods */
	private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
}
