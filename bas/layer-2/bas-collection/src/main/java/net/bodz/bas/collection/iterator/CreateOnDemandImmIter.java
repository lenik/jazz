package net.bodz.bas.collection.iterator;

import net.bodz.bas.closure.ICreatorX;

public class CreateOnDemandImmIter<T, X extends Exception>
        extends AbstractIteratorMX<T, X> {

    private final ICreatorX<? extends T, ? extends X> creator;
    private final Object[] createParameters;

    private final int count;
    private int createdCount;

    public CreateOnDemandImmIter(ICreatorX<? extends T, ? extends X> creator) {
        this(creator, 1, (Object[]) null);
    }

    public CreateOnDemandImmIter(ICreatorX<? extends T, ? extends X> creator, int count) {
        this(creator, count, (Object[]) null);
    }

    public CreateOnDemandImmIter(ICreatorX<? extends T, ? extends X> creator, Object... createParameters) {
        this(creator, 1, createParameters);
    }

    public CreateOnDemandImmIter(ICreatorX<? extends T, ? extends X> creator, int count, Object... createParameters) {
        if (creator == null)
            throw new NullPointerException("creator");
        if (count < 0)
            throw new IllegalArgumentException("Count is negative: " + count);
        this.creator = creator;
        this.count = count;
        this.createParameters = createParameters;
    }

    @Override
    public T _next()
            throws X {
        if (createdCount < count) {
            T instance;
            if (createParameters == null)
                instance = creator.create();
            else
                instance = creator.create(createParameters);
            createdCount++;
            return instance;
        }
        return end();
    }

}
