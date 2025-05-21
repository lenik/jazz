package net.bodz.bas.make;

@FunctionalInterface
public interface MakeFunction2<T, U, V> {

    T make(U input1, V input2)
            throws MakeException;

}
