package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;


public class LocalScopeInstance
        extends MutableScopeInstance {

    private final Map<String, Object> vars = new HashMap<String, Object>();

    public LocalScopeInstance() {
        this(null, null);
    }

    public LocalScopeInstance(String name) {
        this(name, null);
    }

    public LocalScopeInstance(String name, IScopeInstance parent) {
        super(name, new Object(), parent);
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return vars;
    }

}
