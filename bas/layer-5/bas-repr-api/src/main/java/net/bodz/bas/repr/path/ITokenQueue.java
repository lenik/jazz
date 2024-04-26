package net.bodz.bas.repr.path;

/**
 * Records the state of token preprocessing.
 *
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface ITokenQueue
        extends
            IBasicTokenQueue {

    String ATTRIBUTE_KEY = ITokenQueue.class.getName();

    @Override
    ITokenQueue clone();

    String getMethod();

    String getScheme();

    String getHost();

    int getPort();

    String getUserInfo();

    String getQuery();

    String getFragment();

}
