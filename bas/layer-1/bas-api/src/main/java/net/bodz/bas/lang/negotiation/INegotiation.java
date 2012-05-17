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

        Object getValue();

        <T> T value();

        /**
         * An important parameter is mandatory, and it must be processed by the callee..
         * 
         * @return <code>true</code> If this parameter is mandatory.
         */
        boolean isImportant();

        boolean is(String id);

        boolean is(Class<?> type);

        /**
         * The consumer iterates over this parameter, checked or not.
         * 
         * @param id
         *            The specific parameter id, may equal or not to this parameter.
         * @param use
         *            <code>true</code> if the consumer wants to use the parameter,
         *            <code>false</code> if the consumer wants to ignore the parameter.
         * @return <code>true</code> If <code>forId</code> matches this parameter.
         * @throws MandatoryException
         *             If the consumer wants to ignore this parameter, but this parameter is
         *             mandatory.
         */
        boolean is(String id, boolean use)
                throws MandatoryException;

        /**
         * The consumer iterates over this parameter, checked or not.
         * 
         * @param type
         *            The specific parameter class, may equal or not to this parameter.
         * @param use
         *            <code>true</code> if the consumer wants to use the parameter,
         *            <code>false</code> if the consumer wants to ignore the parameter.
         * @return <code>true</code> If <code>forType</code> matches this parameter.
         * @throws MandatoryException
         *             If the consumer wants to ignore this parameter, but this parameter is
         *             mandatory.
         */
        boolean is(Class<?> type, boolean use)
                throws MandatoryException;

        /**
         * If this parameter is for the specific type, returns the casted parameter value.
         * Otherwise, returns <code>null</code>.
         * 
         * @return Casted parameter value if type is matched, otherwise <code>null</code>.
         */
        <T> T cast(Class<T> type);

        /**
         * If this parameter is for the specific type, returns the casted parameter value.
         * Otherwise, returns <code>null</code>.
         * 
         * Same as {@link #cast(Class)}, except check for importance.
         * 
         * @return Casted parameter value if type is matched, otherwise <code>null</code>.
         */
        <T> T cast(Class<T> type, boolean use)
                throws MandatoryException;

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
