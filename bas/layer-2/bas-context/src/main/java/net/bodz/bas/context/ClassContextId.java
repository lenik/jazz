package net.bodz.bas.context;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.jvm.stack.Caller;

public class ClassContextId
        extends AbstractContextId {

    private final IContextId fallbackContext;
    private final Class<?> clazz;

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public ClassContextId(IContextId fallbackContext, Class<?> clazz) {
        super(clazz.getName());
        this.fallbackContext = fallbackContext;
        this.clazz = clazz;
    }

    public ClassContextId(Class<?> clazz) {
        this(StaticContextId.getInstance(), clazz);
    }

    @Override
    public IContextId getParentContextId() {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            return getInstance(fallbackContext, superclass);
        return fallbackContext;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClassContextId))
            return false;
        ClassContextId o = (ClassContextId) obj;
        if (!clazz.equals(o.clazz))
            return false;
        if (!Nullables.equals(fallbackContext, o.fallbackContext))
            return false;
        // return super.equals(obj);
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0xd8a20b23;
        hash += clazz.hashCode();
        if (fallbackContext != null)
            hash += fallbackContext.hashCode();
        return hash;
    }

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public static ClassContextId getInstance(IContextId fallbackContext, Class<?> clazz) {
        return new ClassContextId(fallbackContext, clazz);
    }

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public static ClassContextId getInstance(Class<?> clazz) {
        return new ClassContextId(clazz);
    }

    public static ClassContextId getCallerClassContext(IContextId fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, callerClass);
    }

    public static ClassContextId getCallerClassContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContextId.getInstance(), callerClass);
    }

}
