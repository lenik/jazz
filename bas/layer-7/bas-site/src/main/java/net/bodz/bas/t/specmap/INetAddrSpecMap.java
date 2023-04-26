package net.bodz.bas.t.specmap;

public interface INetAddrSpecMap<val_t> {

    boolean containsKey(int[] address);

    val_t find(int[] address);

    val_t put(int[] address, val_t value);

    boolean add(int[] address, val_t value);

    val_t remove(int[] address);

    void removeAllTops();

    boolean containsPrefix(int[] address, int prefix);

    val_t getPrefix(int[] address, int prefix);

    val_t putPrefix(int[] address, int prefix, val_t value);

    boolean addPrefix(int[] address, int prefix, val_t value);

    val_t removePrefix(int[] address, int prefix);

    void removeAllPrefixes();

}