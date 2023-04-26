package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;

public interface IIPv4SpecMap<val_t>
        extends
            INetAddrSpecMap<val_t> {

    default boolean containsKey(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return containsKey(address);
    }

    default boolean containsKey(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return containsKey(address);
    }

    default val_t find(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return find(address);
    }

    default val_t find(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return find(address);
    }

    default val_t put(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return put(address, value);
    }

    default val_t put(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return put(address, value);
    }

    default boolean add(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return add(address, value);
    }

    default boolean add(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return add(address, value);
    }

    default val_t remove(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return remove(address);
    }

    default val_t remove(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return remove(address);
    }

    default boolean containsPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return containsPrefix(address, prefix);
    }

    default val_t getPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return getPrefix(address, prefix);
    }

    default val_t putPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return putPrefix(address, prefix, value);
    }

    default boolean addPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return addPrefix(address, prefix, value);
    }

    default val_t removePrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return removePrefix(address, prefix);
    }

}
