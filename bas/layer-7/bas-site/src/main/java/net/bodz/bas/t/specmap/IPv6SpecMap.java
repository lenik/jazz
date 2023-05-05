package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;

public class IPv6SpecMap<val_t>
        extends NetAddrSpecMap<val_t> {

    public IPv6SpecMap() {
        super(16);
    }

    public val_t find(String ip)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return find(address);
    }

    public val_t find(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return find(address);
    }

    public boolean containsTop(String ip)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return containsTop(address);
    }

    public boolean containsKey(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return containsTop(address);
    }

    public val_t putTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return putTop(address, value);
    }

    public val_t putTop(int a, int b, int c, int d, int h, int i, int j, int k, val_t value) {
        int[] address = { a, b, c, d, h, i, j, k };
        return putTop(address, value);
    }

    public boolean addTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return addTop(address, value);
    }

    public boolean addTop(int a, int b, int c, int d, int h, int i, int j, int k, val_t value) {
        int[] address = { a, b, c, d, h, i, j, k };
        return addTop(address, value);
    }

    public val_t removeTop(String ip)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return removeTop(address);
    }

    public val_t removeTop(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return removeTop(address);
    }

    public IntSpecNode<val_t> getOrAddPrefixNode(String ip, int prefix, boolean create)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return resolvePrefixNode(address, prefix, create);
    }

    public boolean containsPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return containsPrefix(address, prefix);
    }

    public val_t getPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return getPrefix(address, prefix);
    }

    public val_t putPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return putPrefix(address, prefix, value);
    }

    public boolean addPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return addPrefix(address, prefix, value);
    }

    public val_t removePrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv6Address.parse(ip);
        return removePrefix(address, prefix);
    }

}
