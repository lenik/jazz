package net.bodz.bas.make;

public interface IKeyData<K, T>
        extends IKey<K>,
                IDataRef<T> {

    default String getDisplayName() {
        return getKey().toString();
    }

}
