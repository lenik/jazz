package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable2<T, U, V> {

    T make(U input1, V input2)
            throws MakeException;

}
