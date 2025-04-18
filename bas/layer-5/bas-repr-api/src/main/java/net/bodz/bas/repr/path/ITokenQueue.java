package net.bodz.bas.repr.path;

/**
 * Records the state of token preprocessing.
 * <p>
 * When the dispatch started, the token queue contains tokens to be dispatched, and after dispatch
 * is completed, all processed tokens are consumed, rest only the unprocessed tokens.
 */
public interface ITokenQueue // URI-TokenQueue
        extends IBasicTokenQueue {

    String ATTRIBUTE_KEY = ITokenQueue.class.getName();

    ITokenQueue clone();

    String getMethod();

    String getScheme();

    String getHost();

    int getPort();

    String getUserInfo();

    String getQuery();

    String getFragment();

}
