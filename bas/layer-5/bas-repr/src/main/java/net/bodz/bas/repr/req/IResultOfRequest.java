package net.bodz.bas.repr.req;

public interface IResultOfRequest {

    String ATTRIBUTE_KEY = IResultOfRequest.class.getCanonicalName();

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
     * Call next method immediately.
     * 
     * @return <code>null</code> if this is the final method.
     */
    IMethodOfRequest getNextMethod();

    /**
     * Set the next method to call immediately.
     * 
     * @param nextMethod
     *            The next method to call, <code>null</code> if there's none.
     */
    void setNextMethod(IMethodOfRequest nextMethod);

}
