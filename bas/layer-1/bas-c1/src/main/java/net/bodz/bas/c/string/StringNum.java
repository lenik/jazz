package net.bodz.bas.c.string;

public class StringNum {

    private static final byte c2n[] = CharFeature.c2n;

    /**
     * @throws NumberFormatException
     */
    public static byte parseByte(String s) {
        return parseByte(s, 10);
    }

    /**
     * @throws NumberFormatException
     */
    public static byte parseByte(String s, int radix) {
        long num = parseLong(s, radix);

        byte high = (byte) (num >> 8);
        if (high != 0 && high != -1)
            throw new NumberFormatException("Value out of range. Value:\"" + s + "\" Radix:" + radix);

        return (byte) num;
    }

    /**
     * @throws NumberFormatException
     */
    public static short parseShort(String s) {
        return parseShort(s, 10);
    }

    /**
     * @throws NumberFormatException
     */
    public static short parseShort(String s, int radix) {
        long num = parseLong(s, radix);

        short high = (short) (num >> 16);
        if (high != 0 && high != -1)
            throw new NumberFormatException("Value out of range. Value:\"" + s + "\" Radix:" + radix);

        return (short) num;
    }

    /**
     * @throws NumberFormatException
     */
    public static int parseInt(String s) {
        return parseInt(s, 10);
    }

    /**
     * @throws NumberFormatException
     */
    public static int parseInt(String s, int radix) {
        long num = parseLong(s, radix);

        int high = (int) (num >> 32);
        if (high != 0 && high != -1)
            throw new NumberFormatException("Value out of range. Value:\"" + s + "\" Radix:" + radix);

        return (int) num;
    }

    /**
     * @throws NumberFormatException
     */
    public static long parseLong(String s) {
        return parseLong(s, 10);
    }

    /**
     * @throws NumberFormatException
     */
    public static long parseLong(String s, int radix) {
        boolean negative = false;
        s = s.trim();

        if (s.startsWith("-")) {
            negative = true;
            s = s.substring(1);
        }

        if (s.isEmpty())
            throw new NumberFormatException("empty numeric string");

        if (radix == 0) {
            radix = 10;

            if (s.endsWith("h")) {
                radix = 16;
                s = s.substring(0, s.length() - 1);
            }

            if (s.startsWith("0x")) {
                radix = 16;
                s = s.substring(2);
            }
        }

        long n = 0L;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch >= c2n.length)
                throw new NumberFormatException("Invalid digit char: " + ch);

            int digit = c2n[ch];
            if (digit < 0 || digit >= radix)
                throw new NumberFormatException("Invalid digit char: " + ch);

            n = n * radix + digit;
        }

        return negative ? -n : n;
    }

}
