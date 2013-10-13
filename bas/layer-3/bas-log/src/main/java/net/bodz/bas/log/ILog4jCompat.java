package net.bodz.bas.log;

public interface ILog4jCompat {

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    boolean fatal(Object message);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    boolean fatal(Object message, Throwable t);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
     */
    boolean error(Object message);

    /**
     * @see org.apache.log4j.Category#error(Object)
     * @return If user interaction is concerned, return <code>true</code> for retry, and
     *         <code>false</code> for continue/cancel. Otherwise, return <code>false</code>.
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
