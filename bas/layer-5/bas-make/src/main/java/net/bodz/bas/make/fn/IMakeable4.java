package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable4<T, U, V, W, X> {

    T make(U input1, V input2, W input3, X input4)
            throws MakeException;

}
