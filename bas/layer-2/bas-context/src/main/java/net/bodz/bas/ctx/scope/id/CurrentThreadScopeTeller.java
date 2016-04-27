package net.bodz.bas.ctx.scope.id;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.ctx.scope.AbstractScope;

public class CurrentThreadScopeTeller
        extends AbstractScope {

    ThreadLocal<Map<String, Object>> threadLocalVars;

    public CurrentThreadScopeTeller() {
        threadLocalVars = new ThreadLocal<>();
    }

    @Override
    public IScopeDescriptor tell() {
        return new ThreadScopeDescriptor(Thread.currentThread());
    }

    protected synchronized Map<String, Object> getMap() {
        Map<String, Object> map = threadLocalVars.get();
        if (map == null)
            threadLocalVars.set(map = new HashMap<>());
        return map;
    }

}
