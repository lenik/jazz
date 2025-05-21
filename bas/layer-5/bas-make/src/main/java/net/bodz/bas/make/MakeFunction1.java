package net.bodz.bas.make;

@FunctionalInterface
public interface MakeFunction1<T, U> {

    T make(U input)
            throws MakeException;

}
