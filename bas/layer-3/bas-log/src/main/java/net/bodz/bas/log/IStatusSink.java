package net.bodz.bas.log;

/**
 * A log sink with progress state information.
 */
public interface IStatusSink
        extends ILogSink {

    int getProgressScale();

    /**
     * @param scale
     *            Should be positive integer.
     */
    void setProgressScale(int scale);

    int getProgressIndex();

    /**
     * @param index
     *            Truncated to the scale range.
     */
    void setProgressIndex(int index);

}
