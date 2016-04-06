package net.bodz.bas.ctx.scope.id;

public class ClassScopeDescriptor
        extends MutableScopeDescriptor {

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public ClassScopeDescriptor(Class<?> clazz) {
        super(clazz.getName(), clazz);
    }

    @Override
    protected IScopeDescriptor getInternalParent(Object identity) {
        Class<?> clazz = (Class<?>) identity;
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null)
            return null;
        else
            return new ClassScopeDescriptor(superclass);
    }

}
