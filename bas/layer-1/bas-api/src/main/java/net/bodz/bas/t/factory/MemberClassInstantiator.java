package net.bodz.bas.t.factory;

import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.NotImplementedException;

public class MemberClassInstantiator<T>
        extends AbstractFactory<T> {

    private Class<? extends T> clazz;
    private Object enclosingObject;

    public MemberClassInstantiator(Class<? extends T> clazz, Object enclosingObject) {
        assert clazz != null;
        if (clazz.isMemberClass()) {
            if (enclosingObject == null)
                throw new NullPointerException("no enclosingObject specified for member " + clazz);
        }
        this.clazz = clazz;
        this.enclosingObject = enclosingObject;
    }

    public MemberClassInstantiator(Class<? extends T> clazz) {
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
