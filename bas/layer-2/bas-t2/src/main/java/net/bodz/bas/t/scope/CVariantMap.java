package net.bodz.bas.t.scope;

import java.util.Map;
import java.util.Set;

import net.bodz.bas.t.variant.AbstractVariantMap;

public class CVariantMap
        extends AbstractVariantMap<String> {

    CMap<String, Object> vars = new CMap<>();

    @Override
    public Set<String> keySet() {
        return vars.keySet();
    }

    @Override
    public boolean containsKey(Object key) {
        return vars.containsKey(key);
    }

    @Override
    public Object get(Object key) {
        return vars.get(key);
    }

    public Object put(String key, Object value) {
        return vars.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends Object> m) {
        vars.putAll(m);
    }

    public Object remove(Object key) {
        return vars.remove(key);
    }

    public Object putIfAbsent(String key, Object value) {
        return vars.putIfAbsent(key, value);
    }

    public Map<String, Object> current() {
        return vars.current();
    }

    public void enterNew() {
        vars.enterNew();
    }

    public final void enter(Map<String, Object> scope) {
        vars.enter(scope);
    }

    public void leave() {
        vars.leave();
    }

}