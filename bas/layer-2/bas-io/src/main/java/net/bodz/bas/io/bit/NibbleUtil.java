package net.bodz.bas.io.bit;

public class NibbleUtil {

    static char hextbl[] = "0123456789abcdef".toCharArray();

    /**
     * Only the LSB nibbleCount*4 is used.
     */
    public static String toHex(int nibbles, int nibbleCount) {
        if (nibbleCount == 1)
            return String.valueOf(hextbl[nibbles & 0x0F]);

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < nibbleCount; i++) {
            int nibble = nibbles & 0x0F;
            nibbles >>= 4;
            char c = hextbl[nibble];
            sb.append(c);
        }
        sb.reverse();
        return sb.toString();
    }

    public static String toHex(byte[] buf, int nibbleOffset, int nibbleCount) {
        return toHex(buf, nibbleOffset, nibbleCount, 0, 4, ' ');
    }

    public static String toHex(byte[] buf, int nibbleOffset, int nibbleCount, int lineSize, int wordSize) {
        return toHex(buf, nibbleOffset, nibbleCount, lineSize, wordSize, ' ');
    }

    public static String toHex(byte[] buf, int nibbleOffset, int nibbleCount, int lineSize, int wordSize, char delim) {
        if (buf == null)
            throw new NullPointerException("buf");
        if (wordSize < 1)
            throw new IllegalArgumentException("wordSize must be positive: " + wordSize);
        if (nibbleCount == 0)
            return "";
        int appx = nibbleCount + nibbleCount / wordSize;
        if (lineSize > 0)
            appx += nibbleCount / lineSize;

        StringBuilder sb = new StringBuilder(appx);
        int byteOffset = nibbleOffset >> 1;
        boolean mid = (nibbleOffset & 1) != 0;
        int byt = buf[byteOffset] & 0xff;
        int i = 0;
        int columnIndex = 0;
        boolean trim = false;

        while (i < nibbleCount) {
            int nib;
            if (mid) {
                nib = byt & 0x0f;
                mid = false;
            } else {
                byt = buf[byteOffset++] & 0xff;
                nib = byt >> 4;
                mid = true;
            }

            char hex = hextbl[nib];
            sb.append(hex);

            i++;
            columnIndex++;

            if (lineSize > 0 && columnIndex % lineSize == 0) {
                sb.append('\n');
                columnIndex = 0;
                trim = true;
            } else if (columnIndex % wordSize == 0) {
                sb.append(delim);
                trim = true;
            } else
                trim = false;
        }
        if (trim)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

}
