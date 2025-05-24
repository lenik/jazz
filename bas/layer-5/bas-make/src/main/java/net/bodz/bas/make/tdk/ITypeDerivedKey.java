package net.bodz.bas.make.tdk;

public interface ITypeDerivedKey<T, K> {

    Class<? extends T> getDerivedFromType();

    Class<? extends K> getDerivedKeyType();

    K getDerivedKey();

}
