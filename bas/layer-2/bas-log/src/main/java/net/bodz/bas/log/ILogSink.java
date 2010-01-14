package net.bodz.bas.log;

import net.bodz.bas.log.objects.DelayedConcat;
import net.bodz.bas.log.objects.DelayedFormat;
import net.bodz.bas.log.objects.WithThrown;

public interface ILogSink {

    int LEVEL_CRITICAL = -20000;
    int LEVEL_LESS = -10000;
    int LEVEL_DEFAULT = 0;
    int LEVEL_MORE = 10000;

    int LEVEL_STEPLEN = 10000;

    int getVerboseLevel();

    void setVerboseLevel(int level);

    void p(Object obj);

    /**
     * @see DelayedConcat
     */
    void p(Object... concatObjs);

    /**
     * @see WithThrown
     */
    void p(Object obj, Throwable t);

    void p_(Object obj);

    /**
     * @see DelayedConcat
     */
    void p_(Object... concatObjs);

    /**
     * Finalize/flush any pending prefixes.
     */
    void p_();

    /**
     * @see DelayedFormat
     */
    void f(String format, Object... args);

}
