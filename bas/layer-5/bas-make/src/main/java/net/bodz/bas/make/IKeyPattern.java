package net.bodz.bas.make;

public interface IKeyPattern<Param, K> {

    Class<Param> getParameterType();

    Class<K> getKeyType();

    Param match(Object key);

}
