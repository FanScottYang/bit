package bit;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
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
				path.add(start);
				long startTime = System.currentTimeMillis();
				calculate(start, end, m, path, 0, 0);
				System.out.println(path.size()-1);
				printPath(path, m);
				long endTime = System.currentTimeMillis();
				System.out.println("total time usage: " + (endTime - startTime));
			}
		}
	}

	private static void calculate(int start, int end, int length,
			List<Integer> path, int base, int index) {
		if (start == end) {
			return;
		}

		if (length == 1) {
			path.add((int) (end * Math.pow(2, index)) + base);
			return;
		}

		while (start % 2 == end % 2) {
			int tmp = start % 2;
			start = start >> 1;
			end = end >> 1;
			base += tmp * Math.pow(2, index);
			length--;
			index++;
		}

		if (length == 1) {
			path.add((int) (end * Math.pow(2, index)) + base);
			return;
		}

		int tmp1 = start % 2;
		int tmp2 = end % 2;
		start = start >> 1;
		end = end >> 1;
		calculate(start, 1, length - 1, path, base + tmp1 * (int)Math.pow(2, index), index + 1);
		path.add((int) ((2+tmp2) * Math.pow(2, index)) + base);
		calculate(1, end, length - 1, path, base + tmp2 * (int)Math.pow(2, index), index + 1);
		index++;
	}

	private static void printPath(List<Integer> path, int length) {
		StringBuilder sb = new StringBuilder();
		for (int e : path) {
			sb.append(String.format("%"+length+"s\n", Integer.toBinaryString(e)).replace(' ', 'D').replace('1','L').replace('0','D'));
		}
		System.out.print(sb.toString());
	}
}
