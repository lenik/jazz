package net.bodz.bas.flow;

import java.io.Flushable;
import java.io.IOException;

/**
 * I/O unit block
 */
public interface IUnit
        extends Flushable {

    IUnitMeta getUnitMeta();

    int getInPorts();

    int getOutPorts();

    IInPort getInPort(int index);

    IOutPort getOutPort(int index);

    /**
     * reset should not be propagated, since {@link IUnit} don't know which units it connects, but
     * only ports.
     */
    void reset()
            throws IOException;

    /**
     * flush may be propagated.
     */
    @Override
    void flush()
            throws IOException;

}
