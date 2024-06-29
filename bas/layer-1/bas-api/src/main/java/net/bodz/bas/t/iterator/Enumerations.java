package net.bodz.bas.t.iterator;

import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.function.Function;

import net.bodz.bas.t.coll.TransformedEnumeration;

public class Enumerations {

    public static <E> Enumeration<E> otp(Iterator<E> iter) {
        return new IteratorEnum<E>(iter);
    }

    public static <E> Enumeration<E> enm(Iterable<E> iterable) {
        return new IteratorEnum<E>(iterable.iterator());
    }

    public static <S, T> Enumeration<T> transform(Enumeration<S> source, Function<S, T> transformer) {
        return new TransformedEnumeration<S, T>(source, transformer);
    }

    public static <S extends Enum<S>, T extends Enum<T>> EnumSet<T> transform(EnumSet<S> source, Class<T> targetType,
            Function<S, T> transformer) {
        EnumSet<T> target = EnumSet.noneOf(targetType);
        for (S el : source)
            target.add(transformer.apply(el));
        return target;
    }

}

class IteratorEnum<E>
        implements
            Enumeration<E> {

    Iterator<E> it;

    public IteratorEnum(Iterator<E> it) {
        this.it = it;
    }

    @Override
    public boolean hasMoreElements() {
        return it.hasNext();
    }

    @Override
    public E nextElement() {
        return it.next();
    }

}
