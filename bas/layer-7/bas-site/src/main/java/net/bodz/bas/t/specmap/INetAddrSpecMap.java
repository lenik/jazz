package net.bodz.bas.t.specmap;

public interface INetAddrSpecMap<val_t> {

    val_t find(int[] address);

    boolean containsTop(int[] address);

    val_t putTop(int[] address, val_t value);

    boolean addTop(int[] address, val_t value);

    val_t removeTop(int[] address);

    void removeAllTops();

    boolean containsPrefix(int[] address, int prefix);

    val_t getPrefix(int[] address, int prefix);

    val_t putPrefix(int[] address, int prefix, val_t value);

    boolean addPrefix(int[] address, int prefix, val_t value);

    val_t removePrefix(int[] address, int prefix);

    void removeAllPrefixes();

}