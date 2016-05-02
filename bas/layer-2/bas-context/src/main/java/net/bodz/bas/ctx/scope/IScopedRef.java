package net.bodz.bas.ctx.scope;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.ref.Ref;

@IndexedType
public interface IScopedRef<T>
        extends Ref<T> {
    
    IScopeInstance getCurrentScope();

    String getName();

    /**
     * Return value in the given scope, or the nearest ancestor scope if it's not defined.
     * 
     * @param scope
     *            Non-<code>null</code> scope.
     * @return {@link #getDefault() default} value if the value isn't defined in the scope chain.
     */
    T get(IScopeInstance scope);

    /**
     * Set value in the given scope, or the nearest ancestor scope if it's transient.
     * 
     * @param scope
     *            The scope to be affected, all transient scopes in the scope chain are skipped.
     */
    void set(IScopeInstance scope, T value);

    void remove(IScopeInstance scope);

    /**
     * @see #getCurrentScope()
     */
    @Override
    T get();

    /**
     * @see #getCurrentScope()
     */
    @Override
    void set(T value);

    /**
     * @see #getCurrentScope()
     */
    @Override
    void remove();

}
