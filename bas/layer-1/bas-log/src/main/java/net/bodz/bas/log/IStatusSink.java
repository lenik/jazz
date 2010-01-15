package net.bodz.bas.log;

public interface IStatusSink
        extends ILogSink {

    void clear();

    void progress(int value, int scale);

}
