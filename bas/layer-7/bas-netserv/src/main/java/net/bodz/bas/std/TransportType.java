package net.bodz.bas.std;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.NotNull;

public enum TransportType {

    TCP,

    UDP,

    SCTP,

    DDP;

    @NotNull
    public static TransportType parse(@NotNull String transport)
            throws ParseException {
        TransportType type = parse(transport, null);
        if (type != null)
            return type;
        throw new ParseException("invalid transport: " + transport);
    }

    public static TransportType parse(@NotNull String transport, TransportType fallback) {
        switch (transport.toLowerCase()) {
            case "tcp":
                return TCP;
            case "udp":
                return UDP;
            case "sctp":
                return SCTP;
            case "ddp":
                return DDP;
            default:
                return fallback;
        }
    }

}
