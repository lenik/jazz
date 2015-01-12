package net.bodz.bas.ctx.inject;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.meta.codegen.ExcludedFromIndex;

@ExcludedFromIndex
public class BeanMap
        extends HashMap<String, Object>
        implements IBeanResolver {

    private static final long serialVersionUID = 1L;

    private int priority;

    public BeanMap() {
        super();
    }

    public BeanMap(Map<String, ?> m) {
        super(m);
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public boolean contains(String name) {
        return containsKey(name);
    }

    @Override
    public Object resolve(String name) {
        return get(name);
    }

}
