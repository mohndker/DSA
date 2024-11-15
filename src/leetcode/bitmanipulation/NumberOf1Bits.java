package leetcode.bitmanipulation;

public class NumberOf1Bits {
	public static void main(String[] args) {
		System.out.println(hammingWeightLoop(11));
		System.out.println(hammingWeightBit(11));
		System.out.println(hammingWeightShift(11));
		System.out.println(hammingWeightModule(11));
	}
	
	private static int hammingWeightBit(int num) {
		num = (num & 0x55555555) + ((num >> 1) & 0x55555555);
		num = (num & 0x33333333) + ((num >> 2) & 0x33333333);
		num = (num & 0x0f0f0f0f) + ((num >> 4) & 0x0f0f0f0f);
		num = (num & 0x00ff00ff) + ((num >> 8) & 0x00ff00ff);
		num = (num & 0x0000ffff) + ((num >> 16) & 0x0000ffff);

		return num;
	}

	private static int hammingWeightLoop(int num) {
		int count = 0;
		while (num > 0) {
			num &= num - 1;
			count++;
		}

		return count;
	}

	private static int hammingWeightModule(int num) {
		int count = 0;
		while (num != 0) {
			count += num % 2;
			num = num / 2;
		}

		return count;
	}

	private static int hammingWeightShift(int num) {
		int count = 0;
		for (int i = 0; i < 32; i++) {
			if (((num >> i) & 1) == 1)
				count++;
		}

		return count;
	}
}	

