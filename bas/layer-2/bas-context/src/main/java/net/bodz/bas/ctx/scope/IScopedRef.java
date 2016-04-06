package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.id.IScopeDescriptor;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.ref.Ref;

@IndexedType
public interface IScopedRef<T>
        extends Ref<T> {

    IScopeDescriptor getCurrentScope();

    String getName();

    /**
     * Return value in the given scope, or the nearest ancestor scope if it's not defined.
     * 
     * @param scope
     *            Non-<code>null</code> scope.
     * @return {@link #getDefault() default} value if the value isn't defined in the scope chain.
     */
    T get(IScopeDescriptor scope);

    /**
     * Set value in the given scope, or the nearest ancestor scope if it's transient.
     * 
     * @param scope
     *            The scope to be affected, all transient scopes in the scope chain are
     *            skipped.
     */
    void set(IScopeDescriptor scope, T value);

    void remove(IScopeDescriptor scope);

    /**
     * Shortcut for: <code>get(getCurrentContext())</code>.
     */
    @Override
    T get();

    /**
     * Shortcut for: <code>set(getCurrentContext(), value)</code>.
     */
    @Override
    void set(T value);

    /**
     * Shortcut for: <code>remove(getCurrentContext())</code>.
     */
    @Override
    void remove();

}
