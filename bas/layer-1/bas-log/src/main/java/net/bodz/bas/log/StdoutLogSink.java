package net.bodz.bas.log;


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
