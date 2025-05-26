package net.bodz.bas.make.fn;

import net.bodz.bas.make.MakeException;

@FunctionalInterface
public interface IMakeable7<T, U1, U2, U3, U4, U5, U6, U7> {

    T make(U1 input1, U2 input2, U3 input3, U4 input4, U5 input5, U6 input6, U7 input7)
            throws MakeException;

}
