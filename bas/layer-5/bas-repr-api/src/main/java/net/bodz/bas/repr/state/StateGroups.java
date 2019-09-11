package net.bodz.bas.repr.state;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.c.type.IndexedTypes;
import net.bodz.bas.c.type.SingletonUtil;
import net.bodz.bas.err.DuplicatedKeyException;

public class StateGroups {

    static Map<Class<?>, StateGroup> groupTypes = new HashMap<Class<?>, StateGroup>();
    static Map<String, StateGroup> groupNames = new HashMap<String, StateGroup>();

    static Map<Integer, State> stateKeys = new HashMap<Integer, State>();
    static Map<String, State> stateNames = new HashMap<String, State>();

    static {
        load();
    }

    static void load() {
        // This will also load the class, hence instantiate all states.
        for (Class<? extends StateGroup> type : IndexedTypes.list(StateGroup.class, false)) {
            StateGroup group = SingletonUtil.getInstanceField(type);

            groupTypes.put(type, group);
            String name = group.getName();
            StateGroup g = groupNames.get(name);
            if (g != null)
                throw new DuplicatedKeyException(groupNames, name, "State group name");
            groupNames.put(name, group);

            for (State state : group.getValues())
                addState(state);
        }
    }

    static void addState(State state) {
        Integer key = state.getKey();
        State dup = stateKeys.get(key);
        if (dup != null)
            throw new DuplicatedKeyException(stateKeys, key, "State key");

        String name = state.getName();
        dup = stateNames.get(name);
        if (dup != null)
            throw new DuplicatedKeyException(stateNames, name, "State name");

        stateKeys.put(key, state);
        stateNames.put(name, state);
    }

    public static State getState(int key) {
        return stateKeys.get(key);
    }

    public static State getState(String name) {
        return stateNames.get(name);
    }

}
