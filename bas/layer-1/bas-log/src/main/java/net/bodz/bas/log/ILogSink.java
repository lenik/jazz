package net.bodz.bas.log;

import net.bodz.bas.log.objects.DelayedConcat;
import net.bodz.bas.log.objects.DelayedFormat;
import net.bodz.bas.log.objects.ILogEvent;
import net.bodz.bas.log.objects.WithThrown;

public interface ILogSink {

    int LEVEL_CRITICAL = -20000;
    int LEVEL_LESS = -10000;
    int LEVEL_DEFAULT = 0;
    int LEVEL_MORE = 10000;

    int LEVEL_STEPLEN = 10000;

    int getVerboseLevel();

    void setVerboseLevel(int level);

    void p(ILogEvent event);

    void p(Object message);

    /**
     * @see DelayedConcat
     */
    void p(Object... concatMessages);

    /**
     * @see WithThrown
     */
    void p(Object message, Throwable t);

    void p_(Object message);

    /**
     * @see DelayedConcat
     */
    void p_(Object... concatMessages);

    /**
     * Finalize/flush any pending prefixes.
     */
    void p_();

    /**
     * @see DelayedFormat
     */
    void f(String format, Object... args);

}
