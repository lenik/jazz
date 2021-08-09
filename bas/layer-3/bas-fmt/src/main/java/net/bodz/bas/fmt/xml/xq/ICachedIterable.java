package net.bodz.bas.fmt.xml.xq;

public interface ICachedIterable<T>
        extends
            Iterable<T> {

    int size();

    T get(int index);

}
