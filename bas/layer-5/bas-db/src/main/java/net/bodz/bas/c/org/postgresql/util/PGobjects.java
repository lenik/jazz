package net.bodz.bas.c.org.postgresql.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;

import org.postgresql.util.PGobject;

public class PGobjects {

    public static PGobject convertIP(InetAddress address) {
        if (address == null)
            return null;
        PGobject pgo = new PGobject();
        pgo.setType("");
        try {
            pgo.setValue(address.getHostAddress());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return pgo;
    }

    public static InetAddress convertIP(PGobject pgo) {
        if (pgo == null)
            return null;
        String type = pgo.getType();
        String value = pgo.getValue();
        if ("inet".equals(type)) {
            int slash = value.lastIndexOf('/');
            String ip = slash == -1 ? value : value.substring(0, slash);
            try {
                InetAddress addr = InetAddress.getByName(ip);
                return addr;
            } catch (UnknownHostException e) {
                throw new IllegalArgumentException(e.getMessage(), e);
            }
        } else {
            throw new IllegalArgumentException("Invalid PGobject type for IP value: " + type);
        }
    }

}
