package net.bodz.bas.lang.ref;

import java.util.concurrent.locks.ReentrantLock;

public abstract class Ref<T> {

    private boolean set;

    protected T create() {
        throw new NullPointerException("referent wan't set");
    }

    protected abstract T peek();

    protected abstract void put(T o);

    protected ReentrantLock lock = new ReentrantLock();

    public T lock() {
        lock.lock();
        T o;
        if (!set) {
            put(o = create());
            set = true;
        } else
            o = peek();
        return o;
    }

    public T get() {
        try {
            return lock();
        } finally {
            unlock();
        }
    }

    public void set(T o) throws InterruptedException {
        lock.lock();
        put(o);
        set = true;
        lock.unlock();
    }

    public void clear() throws InterruptedException {
        lock.lock();
        put(null);
        set = false;
        lock.unlock();
    }

    public void unlock() {
        lock.unlock();
    }

}
