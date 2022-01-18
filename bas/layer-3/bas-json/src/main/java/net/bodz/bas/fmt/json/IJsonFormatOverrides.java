package net.bodz.bas.fmt.json;

import java.io.IOException;

public interface IJsonFormatOverrides {

    boolean writeEntryOverride(IJsonOut out, String name)
            throws IOException;

}
