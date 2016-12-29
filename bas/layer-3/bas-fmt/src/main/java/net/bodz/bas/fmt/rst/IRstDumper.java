package net.bodz.bas.fmt.rst;

import java.io.IOException;

public interface IRstDumper {

    public String dump(Object obj);

    void dump(IRstOutput out, Object obj)
            throws IOException;

}
