package net.bodz.bas.t.specmap;

import net.bodz.bas.c.string.StringPred;
import net.bodz.bas.err.ParseException;

public class IPv6Address {

    private final int[] address;

    public IPv6Address() {
        address = new int[8];
    }

    public IPv6Address(int a, int b, int c, int d, int e, int f, int g, int h) {
        address = new int[] { a, b, c, d, e, f, g, h };
    }

    public IPv6Address(int[] address) {
        if (address == null)
            throw new NullPointerException("address");
        this.address = address;
    }

    public IPv6Address(String ip)
            throws ParseException {
        this(parse(ip));
    }

    public int[] getAddress() {
        return address;
    }

    private static int[] _RaiseError = new int[0];

    public static boolean isValid(String ip) {
        return isValid(ip, 8);
    }

    public static boolean isValid(String ip, int nComponent) {
        if (ip == null)
            return false;
        int left = 0;
        int right = 0;
        boolean jump = false;

        int len = ip.length();
        int end = 0;
        while (end < len) {
            if (left + right >= nComponent)
                return false;

            String token;
            int colon = ip.indexOf(':', end);
            if (colon == -1) {
                token = ip.substring(end);
                end = len;
            } else {
                token = ip.substring(end, colon);
                end = colon + 1;
            }

            if (token.isEmpty()) {
                if (end == 1) {
                    left++;
                    continue;
                }
                if (jump)
                    return false;
                jump = true;
                continue;
            }

            if (!StringPred.isHexadecimal(token))
                return false;
            int num = Integer.parseInt(token, 16);
            if (num < 0 || num >= 0xFFFF)
                return false;

            if (jump)
                right++;
            else
                left++;
        }
        if (jump)
            return left + right <= nComponent;
        else
            return left == nComponent;
    }

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

        int[] address = new int[8];
        int[] buf = new int[8];

        int n = 0;
        int m = 0;
        boolean jump = false;

        int len = ip.length();
        int end = 0;
        while (end < len) {
            if (n + m >= address.length)
                if (fallback != _RaiseError)
                    return fallback;
                else
                    throw new ParseException("more numbers than required: " + ip);

            String token;
            int colon = ip.indexOf(':', end);
            if (colon == -1) {
                token = ip.substring(end);
                end = len;
            } else {
                token = ip.substring(end, colon);
                end = colon + 1;
            }

            if (token.isEmpty()) {
                if (end == 1) {
                    address[n++] = 0;
                    continue;
                }
                if (jump)
                    if (fallback != _RaiseError)
                        return fallback;
                    else
                        throw new ParseException("multiple :: isn't allowed: " + ip);
                jump = true;
                continue;
            }
            int num = Integer.parseInt(token, 16);
            if (jump)
                buf[m++] = num;
            else
                address[n++] = num;
        }
        if (jump) {
            if (n + m > address.length)
                if (fallback != _RaiseError)
                    return fallback;
                else
                    throw new ParseException(String.format(//
                            "more numbers(%d) than required(%d): %s", n + m, address.length, ip));
            int skip = address.length - (n + m);
            n += skip; // n = len - m;
            for (int i = 0; i < m; i++)
                address[n++] = buf[i];
        }
        if (n != address.length)
            if (fallback != _RaiseError)
                return fallback;
            else
                throw new IllegalArgumentException(String.format(//
                        "require more numbers(%d/%d): %s", n, address.length, ip));
        return address;
    }

    public static String format(int[] address) {
        StringBuilder sb = new StringBuilder(5 * 8);
        OffLen offLen = indexOfMaxZeros(address);
        int j = address.length;
        if (offLen != null && offLen.length > 1)
            j = offLen.offset;
        for (int i = 0; i < j; i++) {
            if (i != 0)
                sb.append(':');
            int component = address[i];
            sb.append(Integer.toString(component, 16));
        }
        if (j < address.length) {
            sb.append("::");
            int off2 = j + offLen.length;
            for (int i = off2; i < address.length; i++) {
                if (i != off2)
                    sb.append(':');
                int component = address[i];
                sb.append(Integer.toString(component, 16));
            }
        }
        return sb.toString();
    }

    public static class OffLen {
        int offset;
        int length;

        public OffLen(int offset, int length) {
            this.offset = offset;
            this.length = length;
        }
    }

    static OffLen indexOfMaxZeros(int[] address) {
        int start = -1;
        int nmax = 0;
        int nzero = 0;
        for (int i = 0; i < address.length; i++) {
            if (address[i] == 0) {
                nzero++;
                if (nzero > nmax) {
                    nmax = nzero;
                    start = i - (nzero - 1);
                }
            } else {
                nzero = 0;
            }
        }
        return start == -1 ? null : new OffLen(start, nmax);
    }

}
