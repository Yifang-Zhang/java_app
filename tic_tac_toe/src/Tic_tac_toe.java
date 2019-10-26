import java.util.ArrayList;
import java.util.List;
/*
Created by Yifang Zhang at Oct 25th.
 */
public class Tic_tac_toe {
	private int[][] board;
	private int status;
	private int curr_player;

	/*
	Initiate the game and local variables.
	 */
	public void initiate() {
		this.board = new int[3][3];
		this.status = -1;
		this.curr_player = 1;   // by default, starts with player 1
		System.out.println("Welcome to the Tic_Tac_Toe game!");
		printOutTable();
	}

	/*
	Print out the current board.
	 */
	public void printOutTable() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == 0) System.out.print("-");
				else if (board[row][col] == 1) System.out.print("o"); //marked by player 1
				else if (board[row][col] == 2) System.out.print("x"); //marked by player 2
			}
			System.out.print("\n");
		}
	}

	public int getStatus() {
		return status;
	}

	private void setStatus(int status) {
		this.status = status;
	}

	public int getCurr_player() {
		return curr_player;
	}

	private void setCurr_player(int curr_player) {
		this.curr_player = curr_player;
	}

	/*
	Make the valid move by the current player. If the move is invalid, print out an error message.
	If the current player won the game, print out the winning message of game.
	Switch the player after the current player's round is over.
	 */
	public void move(int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[row].length || board[row][col] != 0) {
			System.out.println("Invalid position!");
			return;
		}
		board[row][col] = getCurr_player();
		printOutTable();

		int result = checkWinner();
		if (result != -1) {
			endGame(result);
			setStatus(result);
		}else {
			setCurr_player(getCurr_player() == 1 ? 2 : 1);
		}

	}

	/*
	Based on the result, end the game and print out the final message properly.
	 */
	private void endGame(int result) {

		if (result == 0) System.out.println("The game is ended with a tie.");
		else {
			String winner = (result == 1) ? "Player1" : "Player2";
			System.out.println("The game winner is " + winner);
		}
	}

	/*
	Check the game status to see if the game has ended.
	Return 1 if player1 won the game, return 2 if player2 won the game, return 0 if no one can win the game based
	on current situation. (all cells are filled without a winner)
	Return -1 if the game has not been finished.
	 */
	private int checkWinner() {

		int count_field = 0;
		for (int row = 0; row < board.length; row++) {
			int count_row = 0;
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] == 1) { count_row++; count_field++;}
				else if (board[row][col] == 2) {count_row--; count_field++;}
			}
			if (count_row == 3) return 1;
			else if (count_row == -3) return 2;
		}

		for (int col = 0; col < board[0].length; col++) {
			int count_col = 0;
			for (int row = 0; row < board.length; row++) {
				if (board[row][col] == 1) count_col++;
				else if (board[row][col] == 2) count_col--;
			}
			if (count_col == 3) return 1;
			else if (count_col == -3) return 2;
		}

		int i = 0, j = 0;
		int count_diagonal_1 = 0;
		int count_diagonal_2 = 0;
		while (i >= 0 && i < board.length && j >= 0 && j < board[0].length) {
			if (board[i][j] == 1) count_diagonal_1++;
			else if (board[i][j] == 2) count_diagonal_1--;
			if (board[i][board[0].length-j-1] == 1) count_diagonal_2++;
			else if (board[i][board[0].length-j-1] == 2) count_diagonal_2--;
			i++;
			j++;
		}

		if (count_diagonal_1 == 3 || count_diagonal_2 == 3) return 1;
		else if (count_diagonal_2 == -3 || count_diagonal_2 == -3) return 2;

		if (count_field == 9) return 0;     //tie the game, no winner possible

		return -1;      // no winners
	}
}
