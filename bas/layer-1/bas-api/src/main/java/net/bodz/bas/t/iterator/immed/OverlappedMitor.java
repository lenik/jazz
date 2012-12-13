package net.bodz.bas.t.iterator.immed;

public abstract class OverlappedMitor<T, X extends Exception>
        extends AbstractMitorx<T, X> {

    @Override
    public boolean isOverlapped() {
        return true;
    }

    @Override
    public abstract T deoverlap(T o);

}
