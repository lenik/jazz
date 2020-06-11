package net.bodz.lily.security.login.key;

public interface IFlyingIndex {

    long getTime();

    long getRelativeTime();

    long getIndex();

    long getRelativeIndex();

    boolean isHistory();

    boolean isFuture();

    boolean exists();

}
