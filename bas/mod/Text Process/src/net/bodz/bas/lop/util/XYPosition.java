package net.bodz.bas.lop.util;

import java.io.IOException;

public class XYPosition implements XYTellable {

    private final long offset;
    private final int  y;
    private final int  x;

    public XYPosition(long offset, int y, int x) {
        this.offset = offset;
        this.y = y;
        this.x = x;
    }

    @Override
    public long tell() throws IOException {
        return offset;
    }

    @Override
    public int tellX() {
        return x;
    }

    @Override
    public int tellY() {
        return y;
    }

    /**
     * LINE:COLUMN(+OFFSET)
     */
    @Override
    public String toString() {
        int line = y + 1;
        int column = x + 1;
        StringBuffer buf = new StringBuffer();
        buf.append(line);
        buf.append(':');
        buf.append(column);
        buf.append("(+");
        buf.append(offset);
        buf.append(")");
        return buf.toString();
    }

}
