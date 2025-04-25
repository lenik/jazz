package net.bodz.bas.net.io;

import net.bodz.bas.io.ILookBytesAhead;

public class ReadResult
        implements IReadResult {

    long numOfBytes;
    boolean readEnd;
    ILookBytesAhead lookAhead;

    public ReadResult(long numOfBytes, boolean readEnd, ILookBytesAhead lookAhead) {
        this.numOfBytes = numOfBytes;
        this.readEnd = readEnd;
        this.lookAhead = lookAhead;
    }

    public static ReadResult numOfBytes(long numOfBytes, boolean readEnd) {
        return new ReadResult(numOfBytes, readEnd, ILookBytesAhead.NULL);
    }

    public static ReadResult numOfBytes(long numOfBytes) {
        return numOfBytes(numOfBytes, false);
    }

    public static ReadResult end(long numOfBytes) {
        return numOfBytes(numOfBytes, true);
    }

    public static ReadResult end() {
        return numOfBytes(0, true);
    }

    @Override
    public long getNumBytesRead() {
        return numOfBytes;
    }

    @Override
    public boolean isReadEnd() {
        return readEnd;
    }

    @Override
    public ILookBytesAhead look() {
        return lookAhead;
    }

}
