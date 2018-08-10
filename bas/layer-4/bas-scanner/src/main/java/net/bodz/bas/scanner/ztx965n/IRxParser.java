package net.bodz.bas.scanner.ztx965n;

import java.io.IOException;

public interface IRxParser
        extends IDataTypes {

    Object parse(RxPacket in)
            throws IOException;

}
