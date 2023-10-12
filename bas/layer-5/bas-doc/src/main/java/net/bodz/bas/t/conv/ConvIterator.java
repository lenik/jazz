package net.bodz.bas.t.conv;

import java.util.Iterator;
import java.util.function.Consumer;

public class ConvIterator<E, E_actual>
        implements
            Iterator<E> {

    IConverter<E, E_actual> converter;
    Iterator<E_actual> iterator;

    public ConvIterator(IConverter<E, E_actual> converter, Iterator<E_actual> iterator) {
        this.converter = converter;
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        E_actual actual = iterator.next();
        E e = converter.restore(actual);
        return e;
    }

    @Override
    public void remove() {
        iterator.remove();
    }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        iterator.forEachRemaining((Object actual) -> {
            E e = converter._restore(actual);
            action.accept(e);
        });
    }

}
