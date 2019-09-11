package net.bodz.bas.ctx.scope.impl;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.ctx.scope.IScopeInstance;
import net.bodz.bas.ctx.scope.MutableScopeInstance;

public class ObjScopeInstance
        extends MutableScopeInstance {

    /**
     * object-local storage.
     */
    private static Map<Object, Map<String, Object>> olsVars = new HashMap<Object, Map<String, Object>>();

    private final Map<String, Object> vars;

    public ObjScopeInstance(String name, Object identity) {
        this(name, identity, null);
    }

    public ObjScopeInstance(String name, Object identity, IScopeInstance parent) {
        super(name, identity, parent);
        Map<String, Object> vars = olsVars.get(identity);
        if (vars == null) {
            vars = new HashMap<String, Object>();
            olsVars.put(identity, vars);
        }
        this.vars = vars;
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return vars;
    }

}
