package net.bodz.bas.t.ref.reactive;

import net.bodz.bas.t.ref.Ref;

public interface IObserver<T> {

    void update(Ref<T> ref, T value);

}
