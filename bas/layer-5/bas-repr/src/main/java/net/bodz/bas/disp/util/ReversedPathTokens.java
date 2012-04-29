package net.bodz.bas.disp.util;

import java.util.ArrayList;

public class ReversedPathTokens
        extends ArrayList<String> {

    private static final long serialVersionUID = 1L;

    public String getPath() {
        int size = this.size();
        StringBuilder buf = new StringBuilder(size * 30);

        for (int index = size - 1; index >= 0; index--) {
            if (buf.length() != 0)
                buf.append('/');
            buf.append(this.get(index));
        }

        return buf.toString();
    }

    @Override
    public String toString() {
        return getPath();
    }

}
