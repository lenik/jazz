package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;

public class CtorFactory<T>
        extends AbstractFactory<T> {

    private Class<? extends T> clazz;
    private Object outer;

    public CtorFactory(Class<? extends T> clazz, Object outer) {
        assert clazz != null;
        if (clazz.isMemberClass()) {
            if (outer == null)
                throw new NullPointerException("no outer specified for member " + clazz);
        }
        this.clazz = clazz;
        this.outer = outer;
    }

    public CtorFactory(Class<? extends T> clazz) {
        this(clazz, null);
    }

    @Override
    public Class<? extends T> getType() {
        return clazz;
    }

    @Override
    public T _create(Class<?>[] argTypes, Object... args)
            throws CreateException {
        try {
            if (clazz.isMemberClass())
                // return CompatMethods.newMemberInstance(clazz, outer, args);
                throw new NotImplementedException();
            else
                // return CompatMethods.newInstance(clazz, args);
                throw new NotImplementedException();
        } catch (Exception e) {
            throw new CreateException(e.getMessage(), e);
        }
    }

}
