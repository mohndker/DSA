package leetcode;

public class ZigzagConversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] sbRows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbRows[i] = new StringBuilder();
        }

        int currentRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            sbRows[currentRow].append(c);

            if (currentRow == 0 || currentRow == numRows - 1)
                goingDown = !goingDown;

            currentRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : sbRows) {
            result.append(row);
        }

        return result.toString();
    }

    public static String convert1(String s, int numRows) {
        if (numRows == 1)
            return s;
        // at any point the diff will increas or decrease by 2, this is because of zigzag pattern!
		/*
		0    6      12 -- for 4 rows diff is 6 = (4 + (4-2))
		1  5 7   11 13 -- here the diff for left will decrease by 2 and right will increase by 2
		2 4  8 10   14
		3    9      15
		*/
        int diff1 = numRows + (numRows - 2);
        // because for our first row, we will get row-2 elements in between giving us the next index in first row
        int diff2 = 0;
        // there will be 2 differences! one diff to the left one to the right
        StringBuilder st = new StringBuilder();

        int row = 0;
        while(row < numRows){
            int col = row;
            boolean turn = true;
            while(col < s.length()){
                st.append(s.charAt(col));
                if(turn){
                    col += (diff1 == 0) ? diff2 : diff1; // added because for first and last row there's no left or right!!
                    turn = !turn;
                }else{
                    col += (diff2 == 0) ? diff1 : diff2;
                    turn = !turn;
                }
            }
            diff1 -= 2;
            diff2 += 2;
            row++;
        }
        return st.toString();
    }

    public static String convert2(String s, int numRows) {
        if(numRows == 1) return s;
        int n = s.length();
        if(n <= numRows) return s;
        int incr = 2 * (numRows - 1);
        int ind = 0;
        int zig = incr;
        char ch[] = new char[n];

        for(int i = 0; i < numRows; i++) {
            for(int j = i; j < s.length(); j += incr) {
                ch[ind++] = s.charAt(j);
                if(i != 0 && i != numRows - 1 && j + zig < s.length()) {
                    ch[ind++] = s.charAt(j + zig);
                }
            }
            zig -= 2;
        }

        return new String(ch);
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert2(s, 3));
    }

}
