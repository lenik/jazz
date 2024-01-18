package net.bodz.bas.t.list;

import java.util.ArrayList;
import java.util.function.Supplier;

public class AutoList<E>
        extends ArrayList<E>
        implements
            IAutoList<E> {

    private static final long serialVersionUID = 1L;

    final Supplier<E> factory;

    public AutoList(Class<? extends E> elementClass) {
        if (elementClass == null)
            throw new NullPointerException("elementClass");
        factory = () -> {
            try {
                E instance = elementClass.getConstructor().newInstance();
                return instance;
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        };
    }

    public AutoList(Supplier<E> factory) {
        if (factory == null)
            throw new NullPointerException("factory");
        this.factory = factory;
    }

    @Override
    public Supplier<E> getFactory() {
        return factory;
    }

    @Override
    public synchronized E getFirst() {
        if (isEmpty())
            return null;
        else
            return get(0);
    }

    @Override
    public synchronized E getLast() {
        if (isEmpty())
            return null;
        int n = size();
        return get(n - 1);
    }

    @Override
    public synchronized E clearExcept(int retainIndex) {
        E item = get(retainIndex);
        clearExcept(retainIndex, retainIndex + 1);
        return item;
    }

    @Override
    public synchronized boolean clearExcept(int retainStart, int retainEnd) {
        int end = size();
        int changes = 0;

        if (end > retainEnd) {
            changes += end - retainEnd;
            while (end > retainEnd)
                remove(--end);
        }

        for (int i = retainStart - 1; i >= 0; i--)
            remove(i);

        changes += retainStart;
        return changes != 0;
    }

    @Override
    public synchronized E clearExcept(int index, Supplier<E> defaultItemFactory) {
        int n = size();
        if (n == 0) {
            if (defaultItemFactory == null)
                return null;
            E defaultItem = defaultItemFactory.get();
            add(defaultItem);
            return defaultItem;
        } else {
            if (index < 0)
                index += n;
            return clearExcept(index);
        }
    }

    @Override
    public <item_t extends E> item_t prepend(item_t item) {
        add(0, item);
        return item;
    }

    @Override
    public <item_t extends E> item_t append(item_t item) {
        add(item);
        return item;
    }

    @Override
    public <item_t extends E> item_t insert(int index, item_t item) {
        add(index, item);
        return item;
    }

}
