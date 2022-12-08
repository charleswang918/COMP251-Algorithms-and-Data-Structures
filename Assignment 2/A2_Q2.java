import java.util.*;

public class A2_Q2 {

	public static int weight(int[] plates) {
		int sum = 0;
		for (int plate: plates) {
			sum += plate;
			if (sum > 1000) break;
		}
		if (sum <= 1000) return sum;
		boolean []reachable = new boolean[2010];
		reachable[0] = true;
		for (int i = 1; i < reachable.length; ++i)
			reachable[i] = false;
		for (int plate: plates) {
			for (int i = reachable.length - 1; i >= 0; --i)
				if (reachable[i] && i + plate < reachable.length) {
					reachable[i + plate] = true;
				}
		}
		for (int delta = 0; delta <= 1000; ++delta) {
			if (reachable[1000 + delta]) return 1000 + delta;
			if (reachable[1000 - delta]) return 1000 - delta;
		}
		return 0;
	}

}
