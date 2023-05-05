package net.bodz.bas.t.specmap;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.t.tuple.Split;

public class NamePort {

    String name;
    Integer port;

    public NamePort() {
    }

    public NamePort(String name, Integer port) {
        this.name = name;
        this.port = port;
    }

    public static NamePort parse(String host)
            throws ParseException {
        Split split = Split.hostPort(host);
        NamePort np = new NamePort();
        np.name = split.a;
        if (split.b != null)
            try {
                np.port = Integer.parseInt(split.b);
            } catch (NumberFormatException e) {
                throw new ParseException("invalid port: " + split.b, e);
            }
        return np;
    }

    public static NamePort parse(String host, Integer portFallback) {
        Split split = Split.hostPort(host);
        NamePort np = new NamePort();
        np.name = split.a;
        if (split.b != null)
            try {
                np.port = Integer.parseInt(split.b);
            } catch (NumberFormatException e) {
                np.port = portFallback;
            }
        return np;
    }

}
