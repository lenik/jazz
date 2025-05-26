package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable6<T, U, V, W, X, Y, Z> {

    T make(U input1, V input2, W input3, X input4, Y input5, Z input6)
            throws MakeException;

}
