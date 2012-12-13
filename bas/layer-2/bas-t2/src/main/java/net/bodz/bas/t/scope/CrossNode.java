package net.bodz.bas.t.scope;

public class CrossNode<T>
        extends AbstractCrossNode<T> {

    private T value;

    public CrossNode(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

}
