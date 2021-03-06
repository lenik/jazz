package net.bodz.bas.ctx.scope;

import net.bodz.bas.ctx.scope.impl.ObjScopeInstance;

/**
 * The scope abstraction.
 * <p>
 * This class defines what a scope identity is.
 * 
 * A scope identity is used to distinguish one scope from another, and describes the scope hierachy
 * by inheritance and transient.
 */
public interface IScopeInstance {

    /**
     * A descriptive scope name.
     * <p>
     * Currently, the scope name isn't used at all.
     * 
     * @return Non-<code>null</code> scope name.
     */
    String getName();

    Object getIdentity();

    /**
     * Get the parent scope.
     * 
     * A scope should implicit inherit the status from its parent scope, this may include the
     * environ variables, locale configurations, preferences and settings, etc.
     * 
     * @return Parent scope, <code>null</code> if this is a root scope.
     */
    IScopeInstance getParent();

    IScopeInstance getRoot();

    /**
     * Transparent scope is kind of "read-only". By affecting a transparent scope, indeed, the
     * nearest ancestor scope is affected.
     * <p>
     * In the opposite, a scope is not transparent is concrete.
     * 
     * @return <code>true</code> If this is a transparent scope.
     */
    boolean isTransparent();

    /**
     * Test if a variable exists.
     */
    boolean contains(String name);

    /**
     * Get value of a specific variable.
     */
    Object get(String name);

    /**
     * Set the variable's value.
     */
    void set(String name, Object value);

    void remove(String name);

    ObjScopeInstance STATIC = new ObjScopeInstance("static", new Object());

    class fn {

        public static IScopeInstance echo(Object obj) {
            Class<?> objClass = obj.getClass();
            IScopeTeller teller = ScopeTeller.fn.fromScopedObject(objClass);
            return teller.tell();
        }
    }

}
