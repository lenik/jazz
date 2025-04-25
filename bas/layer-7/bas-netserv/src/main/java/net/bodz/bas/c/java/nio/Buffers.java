package net.bodz.bas.c.java.nio;

import java.nio.ByteBuffer;

import net.bodz.bas.c.string.Strings;

public class Buffers {

    public static int getMost(ByteBuffer context, ByteBuffer src) {
        int n = Math.min(context.remaining(), src.remaining());
        get(context, src, n);
        return n;
    }

    public static ByteBuffer get(ByteBuffer context, ByteBuffer src, int len) {
        for (int i = 0; i < len; i++)
            context.put(src.get());
        return context;
    }

    public static String preview(ByteBuffer buf, int maxLen) {
        int pos = buf.position();
        int limit = buf.limit();
        buf.flip();

        int n = Math.min(buf.remaining(), maxLen);
        byte[] copy = new byte[n];
        buf.get(copy, 0, n);

        // unflip
        buf.limit(limit);
        buf.position(pos);

        String s = toPrintable(copy, 0, n);
        return Strings.ellipsis(s, maxLen);
    }

    public static String toPrintable(byte[] buf, int off, int len) {
        StringBuilder sb = new StringBuilder(len);
        int end = off + len;
        for (int i = off; i < end; i++) {
            int c = buf[i] & 0xFF;
            switch (c) {
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (Character.isISOControl(c)) {
                        sb.append("\\x").append(Integer.toString(c, 16));
                    } else {
                        sb.append((char) c);
                    }
            }
        }
        return sb.toString();
    }

}
