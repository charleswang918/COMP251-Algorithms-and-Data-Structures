import java.util.*;


public class Q1 {
	static class Position {
		public int x, y, z;

		public Position(int i, int j, int k) {
			x = i;
			y = j;
			z = k;
		}

		@Override
		public int hashCode() {
			return (x + "," + y + "," + z).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			return x == other.x && y == other.y && z == other.z;
		}
	}
	public static int[][] dir = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}};
	public static int find_exit(String[][][] jail) {
		Queue<Position> queue = new LinkedList<Position>();
		HashMap<Position, Integer> min_dist = new HashMap<Position, Integer>();
		Position S = null, E = null;
		for (int i = 0; i < jail.length; i++)
			for (int j = 0; j < jail[i].length; j++)
				for (int k = 0; k < jail[i][j].length; k++) {
					if (jail[i][j][k].equals("S")) {
						S = new Position(i, j, k);
					}
					else if (jail[i][j][k].equals("E")) {
						E = new Position(i, j, k);
					}
				}
		queue.offer(S);
		min_dist.put(S, 0);
		while (!queue.isEmpty()) {
			Position now = queue.poll();
			int length = min_dist.get(now);
			for (int i = 0; i < dir.length; ++i) {
				Position next = new Position(now.x + dir[i][0], now.y + dir[i][1], now.z + dir[i][2]);
				if (next.x >= 0 && next.x < jail.length &&
				    next.y >= 0 && next.y < jail[next.x].length &&
				    next.z >= 0 && next.z < jail[next.x][next.y].length &&
				    !jail[next.x][next.y][next.z].equals("#")) {
					if (next.equals(E)) return length + 1;
					if (!min_dist.containsKey(next)) {
						min_dist.put(next, length + 1);
						queue.offer(next);
					}
				}
			}
		}
		return -1;
	}
	

	public static void main(String[] args) {
		/*String[][][] map = {
				{{"S",".",".",".","."},
				 {".","#","#","#","."},
				 {".","#","#",".","."},
				 {"#","#","#",".","#"}},
				{{"#","#","#","#","#"},
				 {"#","#","#","#","#"},
				 {"#","#",".","#","#"},
				 {"#","#",".",".","."}},
				{{"#","#","#","#","#"},
				 {"#","#","#","#","#"},
				 {"#",".","#","#","#"},
				 {"#","#","#","#","E"}},
		};*/
		String[][][] map = {
				{{"S",".",".",".","."},
				 {".","#","#","#","."},
				 {".","#","#",".","."},
				 {"#","#","#",".","#"}},
				{{"#","#","#","#","#"},
				 {"#","#","#","#","#"},
				 {"#","#",".","#","#"},
				 {"#","#",".",".","."}},
				{{"#","#","#","#","#"},
				 {"#","#","#","#","#"},
				 {"#","E","#","#","#"},
				 {"#","#","#","#","."}},
		};
		System.out.println(find_exit(map));
	}

}
