package net.bodz.bas.lop.util;

public interface BytePassMem {

    void drop();

    void keepOnly(int keepMostRecentSize);

    int getMemSize();

    byte[] copyMem();

    void copyMem(long off, byte[] buf, int boff, int len);

    void copyMostRecent(byte[] buf);

    void copyMostRecent(byte[] buf, int boff, int len);

}
