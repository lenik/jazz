package net.bodz.jna.win32;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Kernel32Utils {

    public static String getAsciz(ByteBuffer buffer) {
        return getAsciz(buffer, Charset.defaultCharset());
    }

    public static String getAsciz(ByteBuffer buffer, String encoding) {
        return getAsciz(buffer, Charset.forName(encoding));
    }

    public static String getAsciz(ByteBuffer buffer, Charset charset) {
        int limit = buffer.limit();
        byte[] array = new byte[limit];
        int len = 0;
        for (len = 0; len < limit; len++) {
            byte b = buffer.get();
            if (b == 0)
                break;
            array[len++] = b;
        }
        String s = new String(array, 0, len, charset);
        return s;
    }

}
