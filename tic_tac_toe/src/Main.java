import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Tic_tac_toe tic = new Tic_tac_toe();
		tic.initiate();

		int curr_player;
		while (tic.getStatus() == -1) {
			curr_player = tic.getCurr_player();
			Scanner scan = new Scanner(System.in);
			System.out.println("Player" + curr_player + " please make your move by entering row");

			String input = scan.nextLine();
			int row = Integer.parseInt(input);

			System.out.println("Player" + curr_player + " please make your move by entering col");

			input = scan.nextLine();
			int col = Integer.parseInt(input);

			tic.move(row,col);
		}
	}
}
