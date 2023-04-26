package net.bodz.bas.t.specmap;

import org.apache.commons.text.StringTokenizer;

import net.bodz.bas.err.ParseException;

public class IPv4Utils {

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
