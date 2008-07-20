package net.bodz.mda.parsers.io;

public interface CharPassMem {

    void drop();

    void keepOnly(int keepMostRecentSize);

    int getMemSize();

    char[] copyMem();

    void copyMem(long off, char[] buf, int boff, int len);

    void copyMostRecent(char[] buf);

    void copyMostRecent(char[] buf, int boff, int len);

}
