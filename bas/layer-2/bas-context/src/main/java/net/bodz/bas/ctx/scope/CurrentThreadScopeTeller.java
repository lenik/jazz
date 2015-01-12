package net.bodz.bas.ctx.scope;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.ctx.inject.AbstractScope;

public class CurrentThreadScopeTeller
        extends AbstractScope {

    ThreadLocal<Map<String, Object>> threadLocalVars;

    public CurrentThreadScopeTeller() {
        threadLocalVars = new ThreadLocal<>();
    }

    @Override
    public IScopeToken tell() {
        return new ThreadScopeToken(Thread.currentThread());
    }

    protected synchronized Map<String, Object> getMap() {
        Map<String, Object> map = threadLocalVars.get();
        if (map == null)
            threadLocalVars.set(map = new HashMap<>());
        return map;
    }

    @Override
    public boolean contains(String name) {
        return getMap().containsKey(name);
    }

    @Override
    public Object resolve(String name) {
        return getMap().get(name);
    }

}
