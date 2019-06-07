package net.bodz.bas.fmt.json;

import java.io.IOException;
import java.io.Writer;

public interface IJsonVerbatim {

    void write(Writer writer)
            throws IOException;

}
