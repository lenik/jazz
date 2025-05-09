package net.bodz.bas.make;

public interface IMakeable<T extends IMakeTarget> {

    T getTarget();

    boolean isExpired();

    default boolean isUpdated() {
        return !isExpired();
    }

    default void make()
            throws MakeException {
        make(false);
    }

    default void make(boolean force)
            throws MakeException {
        if (force || isExpired())
            doMake();
    }

    void doMake()
            throws MakeException;

}
