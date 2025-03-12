package net.bodz.bas.c.java.io;

import java.io.Closeable;
import java.io.IOException;

public class Closeables {

    public static boolean close(Closeable closeable) {
        if (closeable != null)
            try {
                closeable.close();
            } catch (IOException e) {
                return false;
            }
        return true;
    }

}
