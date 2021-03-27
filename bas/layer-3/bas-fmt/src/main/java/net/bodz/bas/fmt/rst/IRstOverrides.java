package net.bodz.bas.fmt.rst;

import java.io.IOException;

public interface IRstOverrides {

    String[] getRstElementArguments();

    boolean writeSpecialRstEntry(IRstOutput out, String name)
            throws IOException;

}
