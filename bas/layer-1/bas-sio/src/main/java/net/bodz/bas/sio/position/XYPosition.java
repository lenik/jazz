package net.bodz.bas.sio.position;

/**
 * @test {@link XYPositionTest}
 */
public class XYPosition
        implements IXYTellable {

    private final long offset;
    private final int y;
    private final int x;

    public XYPosition(IXYTellable xyt) {
        if (xyt == null)
            throw new NullPointerException("xyt");
        this.offset = xyt.tell();
        this.y = xyt.tellY();
        this.x = xyt.tellX();
    }

    public XYPosition(long offset, int y, int x) {
        this.offset = offset;
        this.y = y;
        this.x = x;
    }

    @Override
    public long tell() {
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

    public XYPosition add(String s) {
        if (s == null)
            throw new NullPointerException("s");
        int y = this.y;
        int x = this.x;
        int len = s.length();
        boolean CRprefixed = false;
        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)) {
            case '\r':
                if (CRprefixed) {
                    y++;
                    x = 0;
                }
                CRprefixed = true;
                continue;
            case '\n':
                y++;
                x = 0;
                CRprefixed = false;
                continue;
            default:
                if (CRprefixed) {
                    y++;
                    x = 0;
                    CRprefixed = false;
                }
                x++;
            }
        }
        if (CRprefixed) {
            y++;
            x = 0;
            CRprefixed = false;
        }
        return new XYPosition(this.offset + len, y, x);
    }

    @Override
    public int hashCode() {
        int hash = new Long(offset).hashCode();
        hash += 0x31816254 * y;
        hash += 0x88ba2abd * x;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XYPosition))
            return false;
        XYPosition xyp = (XYPosition) obj;
        return offset == xyp.offset // 
                && y == xyp.y //
                && x == xyp.x;
    }

    @Override
    public String toString() {
        return format(this);
    }

    /**
     * LINE:COLUMN(+OFFSET)
     */
    public static String format(IXYTellable xyt) {
        int line = xyt.tellY() + 1;
        int column = xyt.tellX() + 1;
        StringBuffer buf = new StringBuffer();
        buf.append(line);
        buf.append(':');
        buf.append(column);
        buf.append("(+");
        buf.append(xyt.tell());
        buf.append(")");
        return buf.toString();
    }

}
