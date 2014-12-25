package net.bodz.bas.repr.state;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.err.DuplicatedKeyException;
import net.bodz.bas.err.UnexpectedException;

public class StateGroup {

    private Map<Integer, State> idMap;
    private Map<String, State> nameMap;

    public StateGroup() {
        idMap = new HashMap<>();
        nameMap = new HashMap<>();
    }

    public State getState(int stateId) {
        return idMap.get(stateId);
    }

    public State getState(String stateName) {
        return nameMap.get(stateName);
    }

    public State addState(State state) {
        if (state == null)
            throw new NullPointerException("state");

        Integer id = state.getId();
        String name = state.getName();

        if (idMap.containsKey(id))
            throw new DuplicatedKeyException("id: " + id);
        if (nameMap.containsKey(state.getName()))
            throw new DuplicatedKeyException("name: " + name);

        idMap.put(id, state);
        nameMap.put(name, state);
        return state;
    }

    public State addState(String name, StateType type) {
        State state = new State(name, type);
        return addState(state);
    }

    protected void addStateFields(Field... fields) {
        for (Field field : fields) {
            if (!Modifier.isPublic(field.getModifiers()))
                continue;
            if (!State.class.isAssignableFrom(field.getType()))
                continue;
            try {
                State state = (State) field.get(this);
                addState(state);
            } catch (ReflectiveOperationException e) {
                throw new UnexpectedException(e.getMessage(), e);
            }
        }
    }

    @Override
    public String toString() {
        return nameMap.keySet().toString();
    }

    public static final StateGroup INDEXED;
    static {
        INDEXED = new StateGroup();
        for (Class<?> clazz : IndexedTypes.list(IStateConsts.class, true)) {
            INDEXED.addStateFields(clazz.getDeclaredFields());
        }
    }

}
