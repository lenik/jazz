package net.bodz.bas.lang.negotiation;

public interface INegotiation
        extends Iterable<INegotiation.IParameter> {

    /**
     * Refine this negotiation with the called respond.
     * 
     * @return <code>null</code> if the negotiation is forced, i.e., immutable.
     */
    INegotiation refine(INegotiation respond)
            throws NegotiationException;

    INegotiation clone();

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
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(String id);

    /**
     * @return <code>null</code> if the parameter is undefined, or its value is <code>null</code>.
     */
    <T> T get(Class<T> type);

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
