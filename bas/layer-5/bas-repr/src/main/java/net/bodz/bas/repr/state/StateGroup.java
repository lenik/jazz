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
    private Map<String, State> qNameMap;

    public StateGroup() {
        idMap = new HashMap<>();
        qNameMap = new HashMap<>();
    }

    public State getState(int id) {
        return idMap.get(id);
    }

    public State getState(String qName) {
        return qNameMap.get(qName);
    }

    public State addState(State state) {
        if (state == null)
            throw new NullPointerException("state");

        Integer id = state.getId();
        String qName = state.getQName();

        if (idMap.containsKey(id))
            throw new DuplicatedKeyException("id: " + id);
        if (qNameMap.containsKey(state.getName()))
            throw new DuplicatedKeyException("qName: " + qName);

        idMap.put(id, state);
        qNameMap.put(qName, state);
        return state;
    }

    public State addState(Class<?> declaringClass, String name, StateType type) {
        State state = new State(declaringClass, name, type);
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
        return qNameMap.keySet().toString();
    }

    public static final StateGroup INDEXED;
    static {
        INDEXED = new StateGroup();
        for (Class<?> clazz : IndexedTypes.list(IStateConsts.class, true)) {
            INDEXED.addStateFields(clazz.getDeclaredFields());
        }
    }

}
