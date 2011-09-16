package net.bodz.bas.lang.arch;

public class TrueFilter<T>
        implements IFilter<T> {

    @Override
    public boolean accept(T o)
            throws RuntimeException {
        return true;
    }

    static TrueFilter<Object> instance = new TrueFilter<Object>();

    @SuppressWarnings("unchecked")
    public static <T> TrueFilter<T> getInstance() {
        return (TrueFilter<T>) instance;
    }

}
