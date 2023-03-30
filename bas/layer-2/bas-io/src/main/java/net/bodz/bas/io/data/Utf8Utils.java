package net.bodz.bas.io.data;

import java.io.IOException;

public class Utf8Utils {

    public static int computeUtf8Length(char ch)
            throws IOException {
        if (ch <= 0x7f)
            return 1;
        if (ch <= 0x7ff)
            return 2;
        return 3;
    }

}
