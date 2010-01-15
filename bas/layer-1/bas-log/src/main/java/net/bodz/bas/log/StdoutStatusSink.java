package net.bodz.bas.log;

public class StdoutStatusSink
        extends AbstractStatusSink {

    @Override
    protected int getConfiguredVerboseLevel() {
        return 0;
    }

    @Override
    public void put(Object message) {
        System.out.print('\r');
        // output(COLUMNS * ' '); 
        System.out.print(message);
    }

}
