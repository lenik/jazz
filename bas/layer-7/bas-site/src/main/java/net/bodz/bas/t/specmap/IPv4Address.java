package net.bodz.bas.t.specmap;

import org.apache.commons.text.StringTokenizer;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;

public class IPv4Address {

    private final int[] address;

    public IPv4Address() {
        address = new int[4];
    }

    public IPv4Address(int a, int b, int c, int d) {
        address = new int[] { a, b, c, d };
    }

    public IPv4Address(int[] address) {
        if (address == null)
            throw new NullPointerException("address");
        this.address = address;
    }

    public int[] getAddress() {
        return address;
    }

    public static boolean isValid(String ip) {
        if (ip == null)
            return false;

        StringTokenizer tokens = new StringTokenizer(ip, '.');
        int n = 0;
        while (tokens.hasNext()) {
            if (n >= 4)
                return false;
            String token = tokens.nextToken();
            if (!StringPred.isDecimal(token))
                return false;
            int num = Integer.parseInt(token);
            if (num < 0 || num >= 256)
                return false;
            n++;
        }
        if (n != 4)
            return false;
        return true;
    }

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

    public static String normalizeIPv4(String addr) {
        return normalizeIPv4(addr, 4);
    }

    static String normalizeIPv4(String addr, int n) {
        assert n >= 1;
        if (n == 0)
            return addr;

        int dot = addr.indexOf('.');
        String item = dot != -1 ? addr.substring(0, dot) : addr;
        item = item.trim();

        if (!StringPred.isDecimal(item))
            return null;

        int num = Integer.parseInt(item);
        if (num < 0 || num >= 256)
            return null;
        if (n == 1)
            return item;

        String remain = addr.substring(dot + 1);
        if (remain.isEmpty())
            return null;

        String remain_norm = normalizeIPv4(remain, n - 1);
        if (remain_norm == null)
            return null;
        if (remain_norm != remain)
            addr = item + "." + remain_norm;
        return addr;
    }

}
