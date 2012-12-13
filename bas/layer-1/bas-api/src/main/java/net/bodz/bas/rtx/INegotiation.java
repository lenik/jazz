package net.bodz.bas.rtx;

public interface INegotiation
        extends Iterable<INegotiation.IParameter> {

    /**
     * Refine this negotiation with the called respond.
     * 
     * @return <code>null</code> if the negotiation is forced, i.e., immutable.
     */
    INegotiation refine(INegotiation respond);

    // INegotiation clone();

    int size();

    /**
     * @return <code>null</code> if the parameter is undefined.
     */
    IParameter getParameter(String id);

    /**
     * @return <code>null</code> if the parameter is undefined.
     */
    IParameter getParameter(Class<?> type);

    /**
     * Get optional parameter.
     * 
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(String id);

    /**
     * Get optional parameter.
     * 
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    <T> T get(String id, T defaultValue);

    /**
     * Get optional parameter.
     * 
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(Class<T> type);

    /**
     * Get optional parameter.
     * 
     * @return <code>defaultValue</code> if the parameter is undefined.
     */
    <T> T get(Class<T> type, T defaultValue);

    /**
     * Get mandatory parameter.
     * 
     * @param id
     *            The id of parmeter to get.
     * @return Non-<code>null</code> parameter value.
     */
    <T> T require(String id)
            throws MandatoryException;

    /**
     * Get mandatory parameter.
     * 
     * @param id
     *            The id of parmeter to get.
     * @param description
     *            Parameter description shown in exception message.
     * @return Non-<code>null</code> parameter value.
     */
    <T> T require(String id, String description)
            throws MandatoryException;

    /**
     * Get mandatory parameter.
     * 
     * @param type
     *            The typeid of parameter to get.
     * @return Non-<code>null</code> parameter value.
     */
    <T> T require(Class<T> type)
            throws MandatoryException;

    /**
     * Get mandatory parameter.
     * 
     * @param type
     *            The typeid of parameter to get.
     * @param description
     *            Parameter description shown in exception message.
     * @return Non-<code>null</code> parameter value.
     */
    <T> T require(Class<T> type, String description)
            throws MandatoryException;

    /**
     * Callee ignore the negotiation.
     * 
     * This is also a chance for the caller to adjust its default strategy to conform to the
     * non-negotiated session.
     */
    void ignore()
            throws MandatoryException;

    public interface IParameter {

        String getId();

        <T> T getValue();

        /**
         * An important parameter is mandatory, and it must be processed by the callee..
         * 
         * @return <code>true</code> If this parameter is mandatory.
         */
        boolean isImportant();

        /**
         * The consumer want to ignore this parameter.
         * 
         * @throws MandatoryException
         *             If this parameter is mandatory.
         */
        void ignore()
                throws MandatoryException;

    }

}
