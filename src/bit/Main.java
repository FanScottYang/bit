package bit;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try (Scanner cin = new Scanner(new BufferedInputStream(System.in))) {
			int n, m;
			int start, end, result;
			List<Integer> path;
			n = cin.nextInt();
			for (int i = 0; i < n; i++) {
				start = end = result = 0;
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
				path.add(start);
				result = calculate(start, end, m, path, 0, 0);
				path.add(end);
				System.out.println(result);
				printPath(path);
			}
		}
	}

	private static int calculate(int start, int end, int length,
			List<Integer> path, int base, int index) {
		int result = 0;
		if (start == end) {
			return 0;
		}

		if (length == 1) {
			path.add((int) (end * Math.pow(2, index)) + base);
			return 1;
		}

		while (start % 2 == end % 2) {
			int tmp = start % 2;
			start = start >> 1;
			end = end >> 1;
			length--;
			index++;
			base += tmp * Math.pow(2, index);
		}

		if (length == 1) {
			if (start == end) {
				return 0;
			} else {
				path.add((int) (end * Math.pow(2, index)) + base);
				return 1;
			}
		}

		int tmp = end % 2;
		start = start >> 1;
		end = end >> 1;
		result += calculate(start, 1, length - 1, path, base, index + 1);
		path.add((int) ((2+tmp) * Math.pow(2, index)) + base);
		result += calculate(1, end, length - 1, path, base, index + 1);
		result++;
		index++;

		return result;
	}

	private static void printPath(List<Integer> path) {
		for (int e : path) {
			System.out.println(e);
		}
	}
}
