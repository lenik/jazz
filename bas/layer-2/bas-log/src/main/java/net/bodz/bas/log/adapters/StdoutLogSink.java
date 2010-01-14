package net.bodz.bas.log.adapters;

import net.bodz.bas.log.AbstractLogSink;

public class StdoutLogSink
        extends AbstractLogSink {

    @Override
    protected int getConfiguredVerboseLevel() {
        return 0;
    }

    @Override
    public void put(Object obj) {
    }

}
