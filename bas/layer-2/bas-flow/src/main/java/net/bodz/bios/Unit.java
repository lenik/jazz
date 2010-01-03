package net.bodz.bios;

import java.io.Flushable;
import java.io.IOException;

/**
 * I/O unit block
 */
public interface Unit extends Flushable {

    UnitMeta getUnitMeta();

    int getInPorts();

    int getOutPorts();

    InPort getInPort(int index);

    OutPort getOutPort(int index);

    /**
     * reset should not be propagated, since {@link Unit} don't know which units it connects, but
     * only ports.
     */
    void reset() throws IOException;

    /**
     * flush may be propagated.
     */
    @Override
    void flush() throws IOException;

}
