package net.bodz.bas.ctx.scope;

public class ClassScopeToken
        extends MutableScopeToken {

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    ClassScopeToken(Class<?> clazz) {
        super(clazz.getName(), clazz);
    }

    @Override
    protected IScopeToken getInternalParent(Object identity) {
        Class<?> clazz = (Class<?>) identity;
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null)
            return null;
        else
            return new ClassScopeToken(superclass);
    }

}
