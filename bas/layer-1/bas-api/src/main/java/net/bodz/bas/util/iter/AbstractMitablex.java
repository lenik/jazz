package net.bodz.bas.util.iter;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractMitablex<T, X extends Throwable>
        implements IMitablex<T, X> {

    @Override
    public Iterator<T> iterator() {
        Mitorx<? extends T, ? extends X> mitor = iterator(true);
        return Mitors.convert(mitor);
    }

    public T first()
            throws X {
        Mitorx<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator._next();
        if (first == null && iterator.isEnded())
            throw new NoSuchElementException();
        return first;
    }

    public T first(T fallback)
            throws X {
        Mitorx<? extends T, ? extends X> iterator = iterator(true);
        T first = iterator._next();
        if (first == null && iterator.isEnded())
            return fallback;
        return first;
    }

}
