package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable3<T, U, V, W> {

    T make(U input1, V input2, W input3)
            throws MakeException;

}
