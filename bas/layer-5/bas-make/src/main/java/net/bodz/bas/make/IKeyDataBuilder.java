package net.bodz.bas.make;

import net.bodz.bas.meta.decl.NotNull;

public interface IKeyDataBuilder<self_t, K, T> {

    self_t key(@NotNull K key);

    self_t data(T data);

    IKeyData<K, T> build();

}
