package net.bodz.bas.util.stat;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCounterSpec
        implements ICounterSpec {

    Map<String, ICounterDef<?>> defMap;

    public AbstractCounterSpec() {
        defMap = new LinkedHashMap<String, ICounterDef<?>>();
    }

    @Override
    public Collection<ICounterDef<?>> getDefinitions() {
        return defMap.values();
    }

    public ICounterDef<?> getDefinition(String name) {
        return defMap.get(name);
    }

    protected void addDefinition(ICounterDef<?> definition) {
        if (definition == null)
            throw new NullPointerException("definition");

        String name = definition.getName();
        if (defMap.containsKey(name))
            throw new IllegalStateException(String.format( //
                    "Counter definition with name %s is already existed.", name));

        defMap.put(name, definition);
    }

}
