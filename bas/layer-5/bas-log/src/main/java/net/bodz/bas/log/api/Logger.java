package net.bodz.bas.log.api;

import net.bodz.bas.log.ILogComposite;

import org.apache.log4j.Category;

public interface Logger
        extends ILogComposite {

    // Test methods

    boolean isStdoutEnabled();

    boolean isStderrEnabled();

    boolean isStatusEnabled();

    boolean isFatalEnabled();

    boolean isErrorEnabled();

    boolean isWarnEnabled();

    boolean isInfoEnabled();

    boolean isDebugEnabled();

    // Log4j compatible methods

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

    // Shortcuts

    /**
     * @see Category#error(Object)
     */
    void fatal(Object... message);

    /**
     * @see Category#error(Object)
     */
    void fatal(Throwable e, Object... message);

    /**
     * @see Category#error(Object)
     */
    void error(Object... message);

    /**
     * @see Category#error(Object)
     */
    void error(Throwable e, Object... message);

    /**
     * @see Category#warn(Object)
     */
    void warn(Object... message);

    /**
     * @see Category#warn(Object)
     */
    void warn(Throwable e, Object... message);

    /**
     * @see Category#info(Object)
     */
    void info(Object... message);

    /**
     * @see Category#info(Object)
     */
    void info(Throwable e, Object... message);

    /**
     * @see Category#debug(Object)
     */
    void debug(Object... message);

    /**
     * @see Category#debug(Object)
     */
    void debug(Throwable e, Object... message);

}
