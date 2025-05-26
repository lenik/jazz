package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable1<T, U> {

    T make(U input1)
            throws MakeException;

}
