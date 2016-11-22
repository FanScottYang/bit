package bit;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		try (Scanner cin = new Scanner(new BufferedInputStream(System.in))) {
			int n, m;
			int start, end;
			List<Integer> path;
			n = cin.nextInt();
			for (int i = 0; i < n; i++) {
				start = end = 0;
				path = new ArrayList<Integer>();
				// 输入
				m = cin.nextInt();
				String startStr = cin.next();
				String endStr = cin.next();
				for (int j = 0; j < m; j++) {
					start += startStr.charAt(j) == 'L' ? Math.pow(2, m - j - 1)
							: 0;
					end += endStr.charAt(j) == 'L' ? Math.pow(2, m - j - 1) : 0;
				}

				// 计算
				System.out.println(calculate(start, end));
			}
		}
	}

	private static int calculate(int a, int b) {
		if (a == b) {
			return 0;
		} else if (a + b == 1) {
			return 1;
		} else if (a % 2 == b % 2) {
			return calculate(a / 2, b / 2);
		} else {
			return g(a / 2) + g(b / 2) + 1;
		}
	}

	// f(a, 1) or f(1, a)
	private static int g(int a) {
		if (a == 0) {
			return 1;
		} else if (a == 1) {
			return 0;
		} else if (a == 2) {
			return 2;
		} else if (a == 3) {
			return 1;
		} else if (a == 4) {
			return 4;
		} else if (a == 5) {
			return 1;
		}

		if (a % 2 == 0) {
			return g(a / 2) + 2;
		} else {
			a = a / 2;
			int q = q(a);
			if (q == 1) {
				return 1;
			} else {
				return g((q - 1) / 2) + 2;
			}
		}
	}

	public static int q(int a) {
		while (a % 2 == 0) {
			a = a >> 1;
		}
		return a;
	}
}
