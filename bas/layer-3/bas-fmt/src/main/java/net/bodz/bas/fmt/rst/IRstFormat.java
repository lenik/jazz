package net.bodz.bas.fmt.rst;

import java.io.IOException;

public interface IRstFormat {

    String[] getRstElementArguments();

    boolean writeEntryOverride(IRstOutput out, String name)
            throws IOException;

}
