package net.bodz.bas.disp;

public interface IPathDispatchable {

    /**
     * Resolve the tokens with-in the context object.
     * 
     * @param previous
     *            The previous arrival object in which this dispatcher runs into, should not
     *            <code>null</code>.
     * @param pathTokens
     *            Tokens to be consumed by dispatcher. Only effective dispatch could consume the
     *            corresponding token.
     *            <p>
     *            It's the caller's responsibility to check if the tokens are full consumed. It's
     *            not required all the tokens be processed by the {@link DispatchService} facade.
     * @return The final receiver be dispatched into. <code>null</code> if no more matching items.
     * @throws NullPointerException
     *             If either <code>context</code> or <code>tokens</code> is <code>null</code>.
     */
    IPathArrival dispatch(IPathArrival previous, ITokenQueue pathTokens)
            throws DispatchException;

    /**
     * Resolve the tokens with-in the context object.
     * 
     * @param previous
     *            The previous arrival object in which this dispatcher runs into, should not
     *            <code>null</code>.
     * @param path
     *            Path must be fully dispatched, otherwise <code>null</code> is returned.
     * @return The final receiver be dispatched into. <code>null</code> if no more matching items.
     * @throws NullPointerException
     *             If either <code>context</code> or <code>tokens</code> is <code>null</code>.
     */
    IPathArrival dispatch(IPathArrival previous, String path)
            throws DispatchException;

}
