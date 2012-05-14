package net.bodz.bas.disp.view;

public interface IResponseInfo {

    String ATTRIBUTE_KEY = IResponseInfo.class.getCanonicalName();

    /**
     * If target is <code>null</code>, the return value of the controller method is used.
     * 
     * @return The target resource object.
     */
    Object getTarget();

    /**
     * Set the target resource object.
     * 
     * @param target
     *            Non-<code>null</code> target resource object, <code>null</code> to use the return
     *            value of the controller method.
     */
    void setTarget(Object target);

    /**
     * Get the exception.
     * 
     * If the controller method throws another exception, this exception is ignored.
     * 
     * @return Exception if any.
     */
    Throwable getException();

    /**
     * Set the exception.
     * 
     * If the controller method throws another exception, this exception is ignored.
     * 
     * @param The
     *            new exception to set.
     */
    void setException(Throwable exception);

    /**
     * Repeat another method.
     */
    String getMethod();

    void setMethod(String method);

}
