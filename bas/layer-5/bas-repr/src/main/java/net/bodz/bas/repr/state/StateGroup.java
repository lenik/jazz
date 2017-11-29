package net.bodz.bas.repr.state;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.meta.codegen.PublishDir;
import net.bodz.bas.t.order.DefaultComparator;

@IndexedType(publishDir = PublishDir.features)
public abstract class StateGroup {

    private String name;
    private int nextId;

    private Map<Integer, State> keyMap = new TreeMap<>(DefaultComparator.getInstance());
    private Map<String, State> nameMap = new TreeMap<>(DefaultComparator.getInstance());

    public StateGroup() {
        this(null, null);
    }

    public StateGroup(String name, Integer start) {
        if (name == null)
            name = getClass().getSimpleName();
        this.name = name;
        if (start == null) {
            Start aStart = getClass().getAnnotation(Start.class);
            if (aStart == null)
                throw new IllegalUsageException("Require start integer.");
            start = aStart.value();
        }
        this.nextId = start;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, State> getKeyMap() {
        return Collections.unmodifiableMap(keyMap);
    }

    public Map<String, State> getNameMap() {
        return Collections.unmodifiableMap(nameMap);
    }

    public Collection<State> getValues() {
        return Collections.unmodifiableCollection(keyMap.values());
    }

    public State ofKey(int key) {
        return keyMap.get(key);
    }

    public State ofName(String name) {
        return nameMap.get(name);
    }

    public void addValue(State value) {
        if (value == null)
            throw new NullPointerException("value");

        int key = value.getKey();
        String name = value.getName();

        if (keyMap.containsKey(key))
            throw new DuplicatedKeyException(keyMap, key, "State key");
        if (nameMap.containsKey(name))
            throw new DuplicatedKeyException(nameMap, key, "State name");
        keyMap.put(key, value);
        nameMap.put(name, value);
    }

    protected static StateBuilder state(int id, StateGroup group) {
        if (id == 0)
            id = group.nextId++;
        return new StateBuilder().id(id).group(group);
    }

}
