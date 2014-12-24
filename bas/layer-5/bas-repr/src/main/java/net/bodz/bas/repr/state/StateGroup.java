package net.bodz.bas.repr.state;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.err.DuplicatedKeyException;

public abstract class StateGroup {

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

}
