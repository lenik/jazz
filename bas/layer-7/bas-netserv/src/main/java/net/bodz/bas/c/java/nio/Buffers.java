package net.bodz.bas.c.java.nio;

import java.nio.ByteBuffer;

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

}
