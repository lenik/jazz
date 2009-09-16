package net.bodz.xfo.core;

public class BlockType {

    public final int    flags;
    public final String start;
    public final String end;
    public final char   escape;

    public BlockType(String start, String end) {
        this(start, end, '\\', 0);
    }

    public BlockType(String start, String end, char escape, int flags) {
        if (start == null)
            throw new NullPointerException("start");
        if (end == null)
            throw new NullPointerException("end");
        this.start = start;
        this.end = end;
        this.escape = escape;
        this.flags = flags;
    }

    
}
