package net.bodz.bas.log.api;

import net.bodz.bas.log.ILogComposite;

public interface LoggerCompat
        extends ILogComposite {

    // Log4j compatibles.

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return Always return <code>false</code>.
     */
    boolean fatal(Object message);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return Always return <code>false</code>.
     */
    boolean fatal(Object message, Throwable t);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return Always return <code>false</code>.
     */
    boolean error(Object message);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return Always return <code>false</code>.
     */
    boolean error(Object message, Throwable t);

    /**
     * @see org.apache.log4j.Category#warn(Object)
     */
    void warn(Object message);

    /**
     * @see org.apache.log4j.Category#warn(Object)
     */
    void warn(Object message, Throwable t);

    /**
     * @see org.apache.log4j.Category#info(Object)
     */
    void info(Object message);

    /**
     * @see org.apache.log4j.Category#info(Object)
     */
    void info(Object message, Throwable t);

    /**
     * @see org.apache.log4j.Category#debug(Object)
     */
    void debug(Object message);

    /**
     * @see org.apache.log4j.Category#debug(Object)
     */
    void debug(Object message, Throwable t);

}
