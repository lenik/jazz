package net.bodz.bas.crypto.trans;

public interface IFlyingIndex {

    long getTime();

    long getRelativeTime();

    long getIndex();

    long getRelativeIndex();

    boolean isHistory();

    boolean isFuture();

    boolean exists();

}
