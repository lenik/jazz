package net.bodz.bas.make;

public interface IKeyData<K, T>
        extends IKey<K>,
                IDataTypedKey<K, T>,
                IDataRef<T> {

    default String getDisplayName() {
        return getKey().toString();
    }

}
