package net.bodz.bas.types.set;

public class NonfinalSingle<T> extends Single<T> {

    public NonfinalSingle(T value) {
        super(value);
    }

    @Override
    public boolean isFinal() {
        return false;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
