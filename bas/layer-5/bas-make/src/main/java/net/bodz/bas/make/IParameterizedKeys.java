package net.bodz.bas.make;

public interface IParameterizedKeys<P, K> {

    Class<? extends P> getParameterType();

    Class<? extends K> getKeyType();

    K getKey(P parameter);

}
