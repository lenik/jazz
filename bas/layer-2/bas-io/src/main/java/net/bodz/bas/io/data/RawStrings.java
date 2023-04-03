package net.bodz.bas.io.data;

public class RawStrings {

    public static String newString(boolean raw, char[] buf) {
        if (raw) {
            for (int i = 0; i < buf.length; i++)
                if (buf[i] == 0)
                    return new String(buf, 0, i);
        }
        return new String(buf);
    }

    public static String newString(boolean raw, char[] buf, int off, int len) {
        if (raw) {
            for (int i = 0; i < len; i++)
                if (buf[off + i] == 0)
                    return new String(buf, off, len - i);
        }
        return new String(buf, off, len);
    }

    public static String compact(boolean raw, String s) {
        if (raw) {
            int pos = s.indexOf('\0');
            if (pos != -1)
                return s.substring(0, pos);
        }
        return s;
    }

    // if (raw) len = fixLen(array, off, len);

    public static int fixLen(byte[] array, int off, int len) {
        int pos = off;
        for (int i = 0; i < len; i++)
            if (array[pos] == 0)
                return pos - off;
            else
                pos++;
        return len;
    }

    public static int fixLen(char[] array, int off, int len) {
        int pos = off;
        for (int i = 0; i < len; i++)
            if (array[pos] == 0)
                return pos - off;
            else
                pos++;
        return len;
    }

}
