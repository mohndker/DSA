package leetcode.bitmanipulation;

public class ReverseBits {
    public static void main(String[] args) {
        int num = 0b00000010100101000001111010011100;
        System.out.println(reverseBitsMasking(num));
    }

    /**
     * We use masking in the following way,
     * 0x is to tell the compiler this is a hexadecimal representation.
     * f in hexadecimal is 15 in decimal & 1111 in binary.
     * 0 is  0 & 0000
     * c is 12 & 1100
     * a is 10 & 1010
     * 3 is 3 & 0011
     * 5 is 5 & 0101
     * >>> 4 (shift binary by 4 places to right)
     * << 4  (shift binary by 4 places to left)
     */
    private static int reverseBitsMasking(int num) {
        num = ((num & 0xffff0000) >>> 16) | ((num & 0x0000ffff) << 16);
        num = ((num & 0xff00ff00) >>> 8) | ((num & 0x00ff00ff) << 8);
        num = ((num & 0xf0f0f0f0) >>> 4) | ((num & 0x0f0f0f0f) << 4);
        num = ((num & 0xcccccccc) >>> 2) | ((num & 0x33333333) << 2);
        num = ((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1);
        
        return num;
    }


    /**
     * We loop over the 32 binary bits.
     * result is 32 binary bits of 0s
     * process on bit starting from least significant bit.
     * the num & 1 will result in 1 or 0, adding it to least significant bit in result and shift
     * result to left by 1
     * shift num by one to right to remove the least significant bit which we just processed
     */
    private static int reverseBitsLooping(int num) {
        if (num == 0) return num;

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;
            result += num & 1;
            num >>= 1;
        }

        return result;
    }
}
