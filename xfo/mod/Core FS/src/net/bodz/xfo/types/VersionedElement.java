package net.bodz.xfo.types;

import java.util.Comparator;

public interface VersionedElement<T> {

    Comparator<? super T> getComparator();

    T getVersion();

}
