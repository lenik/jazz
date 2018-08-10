package net.bodz.bas.scanner.klm;

import java.io.IOException;

import net.bodz.bas.comm.SerialSupport;

public class Klm900
        extends SerialSupport {

    public static final int BAUD_RATE = 115200;

    public Klm900()
            throws IOException {
        super(BAUD_RATE);
    }

}
