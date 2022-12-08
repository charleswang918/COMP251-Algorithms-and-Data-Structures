import java.math.BigInteger;
import java.util.Scanner;

public class A2_Q4 {
	static BigInteger[] fib = null;
	
	public static void init(int N) {
		fib = new BigInteger[N];
		fib[0] = BigInteger.ZERO;
		fib[1] = BigInteger.ONE;
		for (int i = 2; i < N; i++)
			fib[i] = fib[i - 1].add(fib[i - 2]);
	}
	public static String mod_fibonacci(int N, BigInteger K) {
		if (N == 1) return "X";
		if (N == 2) return "Y";
		if (!(fib != null && fib.length >= N)) init(N);
		if (K.compareTo(fib[N - 2]) <= 0) {
			// the K-th character is inside fib_{N - 2}
			return mod_fibonacci(N - 2, K);
		}
		else {
			// the K-th character is inside fib_{N - 1}
			return mod_fibonacci(N - 1, K.subtract(fib[N - 2]));
		}
	}

}
