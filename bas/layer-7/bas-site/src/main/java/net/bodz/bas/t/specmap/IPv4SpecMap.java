package net.bodz.bas.t.specmap;

import org.apache.commons.text.StringTokenizer;

import net.bodz.bas.err.ParseException;

public class IPv4SpecMap<val_t>
        extends NetAddrSpecMap<val_t> {

    public IPv4SpecMap() {
        super(8);
    }

    public boolean containsTop(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return containsTop(address);
    }

    public boolean containsTop(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return containsTop(address);
    }

    public val_t find(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return find(address);
    }

    public val_t find(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return find(address);
    }

    public val_t putTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return putTop(address, value);
    }

    public val_t putTop(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return putTop(address, value);
    }

    public boolean addTop(String ip, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return addTop(address, value);
    }

    public boolean addTop(int a, int b, int c, int d, val_t value) {
        int[] address = { a, b, c, d };
        return addTop(address, value);
    }

    public val_t removeTop(String ip)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return removeTop(address);
    }

    public val_t removeTop(int a, int b, int c, int d) {
        int[] address = { a, b, c, d };
        return removeTop(address);
    }

    public IntSpecNode<val_t> resolvePrefixNode(String ip, int prefix, boolean create)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return super.resolvePrefixNode(address, prefix, create);
    }

    public boolean containsPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return containsPrefix(address, prefix);
    }

    public val_t getPrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return getPrefix(address, prefix);
    }

    public val_t putPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return putPrefix(address, prefix, value);
    }

    public boolean addPrefix(String ip, int prefix, val_t value)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return addPrefix(address, prefix, value);
    }

    public val_t removePrefix(String ip, int prefix)
            throws ParseException {
        int[] address = IPv4Utils.parse(ip);
        return removePrefix(address, prefix);
    }

}

class IPv4Utils {

    private static int[] _RaiseError = new int[0];

    public static int[] parse(String ip)
            throws ParseException {
        return _parse(ip, _RaiseError);
    }

    public static int[] parse(String ip, int[] fallback) {
        try {
            return _parse(ip, fallback);
        } catch (ParseException e) {
            assert false;
            return fallback;
        }
    }

    public static int[] _parse(String ip, int[] fallback)
            throws ParseException {
        if (ip == null)
            return null;

        int[] address = new int[4];
        StringTokenizer tokens = new StringTokenizer(ip, '.');
        int n = 0;
        while (tokens.hasNext()) {
            if (n >= address.length) {
                if (fallback != _RaiseError)
                    return fallback;
                else
                    throw new ParseException("more numbers than required: " + ip);
            }
            String token = tokens.nextToken();
            int num = Integer.parseInt(token);
            address[n++] = num;
        }
        if (n != address.length)
            if (fallback != _RaiseError)
                return fallback;
            else
                throw new ParseException("require more numbers: " + ip);
        return address;
    }

    public static String format(int[] address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length; i++) {
            if (i != 0)
                sb.append('.');
            sb.append(address[i]);
        }
        return sb.toString();
    }

}
