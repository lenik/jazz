package net.bodz.bas.lang.arch;

public class FalseFilter<T>
        implements IFilter<T> {

    @Override
    public boolean accept(T o)
            throws RuntimeException {
        return false;
    }

    static FalseFilter<Object> instance = new FalseFilter<Object>();

    @SuppressWarnings("unchecked")
    public static <T> FalseFilter<T> getInstance() {
        return (FalseFilter<T>) instance;
    }

}
