package net.bodz.bas.make;

public interface ITypeDerivedKey<T, K> {

    Class<T> getDerivedFromType();

    Class<K> getDerivedKeyType();

    K getDerivedKey();

}
