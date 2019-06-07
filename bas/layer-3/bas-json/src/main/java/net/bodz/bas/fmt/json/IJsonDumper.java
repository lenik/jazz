package net.bodz.bas.fmt.json;

import java.io.IOException;

public interface IJsonDumper {

    void dump(Object obj)
            throws IOException;

}
