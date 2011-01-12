package net.bodz.bas.arch.context;

import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.util.Nullables;

/**
 * @test {@link PackageContextTest}
 */
public class ClassContext
        extends AbstractContext {

    private final IContext fallbackContext;
    private final Class<?> clazz;

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public ClassContext(IContext fallbackContext, Class<?> clazz) {
        super(clazz.getName());
        this.fallbackContext = fallbackContext;
        this.clazz = clazz;
    }

    public ClassContext(Class<?> clazz) {
        this(StaticContext.getInstance(), clazz);
    }

    @Override
    public IContext getParentContext() {
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null)
            return getInstance(fallbackContext, superclass);
        return fallbackContext;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ClassContext))
            return false;
        ClassContext o = (ClassContext) obj;
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
    public static ClassContext getInstance(IContext fallbackContext, Class<?> clazz) {
        return new ClassContext(fallbackContext, clazz);
    }

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public static ClassContext getInstance(Class<?> clazz) {
        return new ClassContext(clazz);
    }

    public static ClassContext getCallerClassContext(IContext fallbackContext) {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(fallbackContext, callerClass);
    }

    public static ClassContext getCallerClassContext() {
        Class<?> callerClass = Caller.getCallerClass(2);
        return getInstance(StaticContext.getInstance(), callerClass);
    }

}
