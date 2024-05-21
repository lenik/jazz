package net.bodz.bas.t.ref.reactive;

import java.util.ArrayList;
import java.util.List;

public class Ref<T>
        implements
            net.bodz.bas.t.ref.Ref<T> {

    T value;

    List<IObserver<T>> observers = new ArrayList<>();

    public Ref(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        if (this.value != value) {
            this.value = value;
            for (IObserver<T> observer : observers)
                observer.update(this, value);
        }
    }

    @Override
    public void remove() {
        this.value = null;
    }

    public void addObserver(IObserver<T> observer) {
        if (observer == null)
            throw new NullPointerException("observer");
        this.observers.add(observer);
    }

    public void removeObserver(IObserver<T> observer) {
        if (observer == null)
            throw new NullPointerException("observer");
        observers.remove(observer);
    }

    @Override
    public String toString() {
        return value == null ? null : value.toString();
    }

}
