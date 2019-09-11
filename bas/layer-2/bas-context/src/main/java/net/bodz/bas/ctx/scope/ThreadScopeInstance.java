package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;


public class ThreadScopeInstance
        extends MutableScopeInstance {

    private static Map<Thread, Map<String, Object>> tlsVars = new HashMap<Thread, Map<String, Object>>();

    private Map<String, Object> vars;

    public ThreadScopeInstance(Thread thread) {
        super(thread.getName(), thread);
        Map<String, Object> vars = tlsVars.get(thread);
        if (vars == null) {
            vars = new HashMap<String, Object>();
            tlsVars.put(thread, vars);
        }
        this.vars = vars;
    }

    @Override
    protected IScopeInstance getInternalParent(Object identity) {
        Thread thread = (Thread) identity;
        ThreadGroup threadGroup = thread.getThreadGroup();
        if (threadGroup == null)
            return null;
        else
            return new ThreadGroupScopeInstance(threadGroup);
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return vars;
    }

}
