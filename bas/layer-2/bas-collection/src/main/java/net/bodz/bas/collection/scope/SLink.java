package net.bodz.bas.collection.scope;

public class SLink<T> {

    protected final T _this;
    protected final T next;

    public SLink(T _this, T next) {
        this._this = _this;
        this.next = next;
    }

    public T getThis() {
        return _this;
    }

    protected T getNext() {
        return next;
    }

    @Override
    public String toString() {
        if (next == null)
            return _this.toString();
        else
            return _this.toString() + " -> " + next.toString(); 
    }

}
