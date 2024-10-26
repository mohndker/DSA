package strings;

public class StringImpl {
    private char[] value;
    private int offset;
    private int length;
    private int hash;

    public int length() {
        return length;
    }

    public char charAt(int i) {
        return value[i + offset];
    }

    private StringImpl(int offset, int length, char[] value) {
        this.offset = offset;
        this.length = length;
        this.value = value;
    }

    public StringImpl substring(int from, int to) {
        return new StringImpl(offset + from, to -from, value);
    }
}
