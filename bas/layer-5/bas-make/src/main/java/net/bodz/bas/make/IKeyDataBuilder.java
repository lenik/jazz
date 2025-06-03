package net.bodz.bas.make;

public interface IKeyDataBuilder<self_t, K, T> {

    self_t key(K key);

    self_t data(T data);

    IKeyData<K, T> build();

}
