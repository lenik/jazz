package net.bodz.bas.c.java.util.stream.iterable;

import net.bodz.bas.c.java.util.DecoratedStream;

public class Stream<E>
        extends DecoratedStream<E>
        implements
            Iterable<E> {

    private static final long serialVersionUID = 1L;

    public Stream(java.util.stream.Stream<E> _orig) {
        super(_orig);
    }

    public static <E> Stream<E> of(java.util.stream.Stream<E> stream) {
        return new Stream<E>(stream);
    }

}
