import java.util.*;

public class A2_Q1 {
	public static int[][] dir = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

	public static boolean valid(int x, int y, int n, int m) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}
	public static int search(String[][] board, int depth) {
		int ret = depth;
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; ++j)
				if (board[i][j].equals("o")) {
					for (int k = 0; k < 4; ++k) {
						int mid_i = i + dir[k][0], mid_j = j + dir[k][1];
						int next_i = i + dir[k][0] * 2, next_j = j + dir[k][1] * 2;
						if (valid(next_i, next_j, board.length, board[i].length) && board[mid_i][mid_j].equals("o") &&
						    board[next_i][next_j].equals(".")) {
							board[i][j] = ".";
							board[mid_i][mid_j] = ".";
							board[next_i][next_j] = "o";
							int trial = search(board, depth + 1);
							if (trial > ret) ret = trial;
							board[i][j] = "o";
							board[mid_i][mid_j] = "o";
							board[next_i][next_j] = ".";
						}
					}
				}
		return ret;
	}
	public static int[] game(String[][] board){
		int cnt = 0;
		for (int i = 0; i < board.length; ++i)
			for (int j = 0; j < board[i].length; ++j)
				if (board[i][j].equals("o")) cnt++;
		int max_step = search(board, 0);
		return new int[]{cnt - max_step, max_step};
	}

}
