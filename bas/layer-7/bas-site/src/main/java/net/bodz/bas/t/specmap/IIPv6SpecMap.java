package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;

public interface IIPv6SpecMap<val_t>
        extends
            INetAddrSpecMap<val_t> {

    default boolean containsKey(String ip)
            throws ParseException {
        int[] address = IPv6Utils.parse(ip);
        return containsKey(address);
    }

    default boolean containsKey(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return containsKey(address);
    }

    default val_t find(String ip)
            throws ParseException {
        int[] address = IPv6Utils.parse(ip);
        return find(address);
    }

    default val_t find(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return find(address);
    }

    default val_t put(String ip, val_t value)
            throws ParseException {
        int[] address = IPv6Utils.parse(ip);
        return put(address, value);
    }

    default val_t put(int a, int b, int c, int d, int h, int i, int j, int k, val_t value) {
        int[] address = { a, b, c, d, h, i, j, k };
        return put(address, value);
    }

    default boolean add(String ip, val_t value)
            throws ParseException {
        int[] address = IPv6Utils.parse(ip);
        return add(address, value);
    }

    default boolean add(int a, int b, int c, int d, int h, int i, int j, int k, val_t value) {
        int[] address = { a, b, c, d, h, i, j, k };
        return add(address, value);
    }

    default val_t remove(String ip)
            throws ParseException {
        int[] address = IPv6Utils.parse(ip);
        return remove(address);
    }

    default val_t remove(int a, int b, int c, int d, int h, int i, int j, int k) {
        int[] address = { a, b, c, d, h, i, j, k };
        return remove(address);
    }

}
