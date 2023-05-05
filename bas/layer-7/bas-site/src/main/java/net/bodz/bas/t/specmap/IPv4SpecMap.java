package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;

public class IPv4SpecMap<val_t>
        extends NetAddrSpecMap<val_t> {

    public IPv4SpecMap() {
        super(8);
    }

    public boolean containsTop(String ip)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return containsTop(address);
    }

    public boolean containsTop(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return containsTop(address);
    }

    public val_t find(String ip)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return find(address);
    }

    public val_t find(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return find(address);
    }

    public val_t putTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return putTop(address, value);
    }

    public val_t putTop(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return putTop(address, value);
    }

    public boolean addTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return addTop(address, value);
    }

    public boolean addTop(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return addTop(address, value);
    }

    public val_t removeTop(String ip)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return removeTop(address);
    }

    public val_t removeTop(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return removeTop(address);
    }

    public IntSpecNode<val_t> resolvePrefixNode(String ip, int prefix, boolean create)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return super.resolvePrefixNode(address, prefix, create);
    }

    public boolean containsPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return containsPrefix(address, prefix);
    }

    public val_t getPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return getPrefix(address, prefix);
    }

    public val_t putPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return putPrefix(address, prefix, value);
    }

    public boolean addPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return addPrefix(address, prefix, value);
    }

    public val_t removePrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Address.parse(ip);
        return removePrefix(address, prefix);
    }

}
