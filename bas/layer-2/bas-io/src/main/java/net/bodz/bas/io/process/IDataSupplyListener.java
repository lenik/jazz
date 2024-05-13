package net.bodz.bas.io.process;

import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
public interface IDataSupplyListener {

    /**
     * @return <code>false</code> for EOF.
     */
    boolean onDataRequired(OutputStream out)
            throws IOException;

}
