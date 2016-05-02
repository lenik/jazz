package net.bodz.bas.ctx.scope.impl;

import java.util.Map;

import net.bodz.bas.c.java.util.IMapEntryLoader;
import net.bodz.bas.c.type.LazyTypeMap;
import net.bodz.bas.c.type.TypeMapRegistry;
import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class ClassScopeInstance
        extends MutableScopeInstance {

    private static final LazyTypeMap<Map<String, Object>> clsVars = TypeMapRegistry.createMap(//
            ClassScopeInstance.class.getCanonicalName(), //
            IMapEntryLoader.fn.<Class<?>, String, Object> newHashMap());

    Map<String, Object> vars;

    /**
     * @throws NullPointerException
     *             If <code>clazz</code> is <code>null</code>.
     */
    public ClassScopeInstance(Class<?> clazz) {
        super(clazz.getName(), clazz);
        vars = clsVars.getOrLoad(clazz);
    }

    @Override
    protected IScopeInstance getInternalParent(Object identity) {
        Class<?> clazz = (Class<?>) identity;
        Class<?> superclass = clazz.getSuperclass();
        if (superclass == null)
            return null;
        else
            return new ClassScopeInstance(superclass);
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return vars;
    }

}
