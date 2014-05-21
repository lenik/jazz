package net.bodz.bas.context;

public class ClassContext
        extends MutableContext {

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    ClassContext(Class<?> clazz) {
        super(clazz.getName(), clazz);
    }

    @Override
    protected IContext getInternalParent(Object identity) {
        Class<?> clazz = (Class<?>) identity;
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null)
            return null;
        else
            return new ClassContext(superclass);
    }

}
