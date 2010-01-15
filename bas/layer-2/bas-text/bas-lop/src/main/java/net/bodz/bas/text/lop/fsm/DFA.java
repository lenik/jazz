package net.bodz.bas.text.lop.fsm;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.io.out.CharOuts.BCharOut;

public class DFA {

    private final String name;
    private List<DFAState> states;

    public DFA(String name) {
        if (name == null)
            name = getClass().getName() + "@" + System.identityHashCode(this);
        this.name = name;
        this.states = new ArrayList<DFAState>();
    }

    public String getName() {
        return name;
    }

    public DFAState getState(int id) {
        return states.get(id);
    }

    public DFAState addState() {
        return addState(null, 0);
    }

    public DFAState addState(String name) {
        return addState(name, 0);
    }

    public DFAState addState(String name, int flags) {
        int nextId = states.size();
        DFAState state = new DFAState(this, nextId, name);
        states.add(state);
        return state;
    }

    public DFAState removeState(int id) {
        DFAState state = states.get(id);
        states.set(id, null);
        return state;
    }

    @Override
    public String toString() {
        BCharOut out = new BCharOut(states.size() * 200);
        out.println("DFA " + name);
        for (DFAState state : states) {
            if (state == null)
                continue;
            out.printf("    State %s\n", //
                    state.getName());
            state.dumpTransitionMap(out, "        ");
        }
        return out.toString();
    }

}
