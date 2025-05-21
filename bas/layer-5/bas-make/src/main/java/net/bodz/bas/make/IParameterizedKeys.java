package net.bodz.bas.make;

public interface IParameterizedKeys<P, K> {

    Class<P> getParameterType();

    Class<K> getKeyType();

    K getKey(P parameter);

}
