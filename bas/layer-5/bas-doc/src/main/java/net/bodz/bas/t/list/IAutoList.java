package net.bodz.bas.t.list;

import java.util.function.Supplier;

public interface IAutoList<E>
        extends
            IListEx<E> {

    Supplier<E> getFactory();

    default E clearExcept(int retainIndex, boolean addDefault) {
        if (addDefault)
            return clearExcept(retainIndex, getFactory());
        else
            return clearExcept(retainIndex);
    }

    default E prepend() {
        E a = getFactory().get();
        return prepend(a);
    }

    default E append() {
        E a = getFactory().get();
        return append(a);
    }

    default E insert(int index) {
        E a = getFactory().get();
        return insert(index, a);
    }

}
