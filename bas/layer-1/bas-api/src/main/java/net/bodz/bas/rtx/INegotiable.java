package net.bodz.bas.rtx;

/**
 * The whole object is negotiable.
 */
public interface INegotiable {

    /**
     * Send the options parameters.
     * 
     * @throws IllegalParameterUsageException
     *             If the callee doesn't accept the request, for example, an important parameter
     *             isn't supported by the callee.
     */
    void negotiate(IOptions req);

}
