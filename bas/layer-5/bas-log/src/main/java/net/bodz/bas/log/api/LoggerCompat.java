package net.bodz.bas.log.api;

import net.bodz.bas.log.ILogComposite;

import org.apache.log4j.Category;

public interface LoggerCompat
        extends ILogComposite {

    // Log4j compatibles.

    /**
     * @see Category#error(Object)
     */
    void fatal(Object message);

    /**
     * @see Category#error(Object)
     */
    void fatal(Object message, Throwable t);

    /**
     * @see Category#error(Object)
     */
    void error(Object message);

    /**
     * @see Category#error(Object)
     */
    void error(Object message, Throwable t);

    /**
     * @see Category#warn(Object)
     */
    void warn(Object message);

    /**
     * @see Category#warn(Object)
     */
    void warn(Object message, Throwable t);

    /**
     * @see Category#info(Object)
     */
    void info(Object message);

    /**
     * @see Category#info(Object)
     */
    void info(Object message, Throwable t);

    /**
     * @see Category#debug(Object)
     */
    void debug(Object message);

    /**
     * @see Category#debug(Object)
     */
    void debug(Object message, Throwable t);

}
