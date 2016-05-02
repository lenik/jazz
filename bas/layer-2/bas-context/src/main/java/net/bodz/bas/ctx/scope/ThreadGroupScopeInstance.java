package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;


public class ThreadGroupScopeInstance
        extends MutableScopeInstance {

    private static Map<ThreadGroup, Map<String, Object>> tlsVars = new HashMap<>();

    private final ThreadGroup threadGroup;
    private final Map<String, Object> vars;

    /**
     * @param threadGroup
     *            Specify <code>null</code> to refer to top-level {@link ThreadGroup}.
     */
    public ThreadGroupScopeInstance(ThreadGroup threadGroup) {
        super(threadGroup.getName(), threadGroup);
        this.threadGroup = threadGroup;
        Map<String, Object> vars = tlsVars.get(threadGroup);
        if (vars == null) {
            vars = new HashMap<>();
            tlsVars.put(threadGroup, vars);
        }
        this.vars = vars;
    }

    @Override
    public IScopeInstance getParent() {
        if (threadGroup == null)
            return null;
        ThreadGroup parent = threadGroup.getParent();
        if (parent != null)
            return new ThreadGroupScopeInstance(parent);
        return null;
    }

    @Override
    protected Map<String, Object> getVarMap() {
        return vars;
    }

    public static ThreadGroupScopeInstance getInstance(ThreadGroup threadGroup) {
        return new ThreadGroupScopeInstance(threadGroup);
    }

}
