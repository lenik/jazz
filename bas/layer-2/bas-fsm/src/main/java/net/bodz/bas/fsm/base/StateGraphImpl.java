package net.bodz.bas.fsm.base;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateGraphImpl implements StateGraph {

    private static final long serialVersionUID = 3618890808666598908L;

    protected Object context;
    protected State current;

    private Stack<State> stack;

    // <name, state>
    private Map<Object, State> registry;

    public StateGraphImpl() {
        this(new DummyState(), null);
    }

    public StateGraphImpl(Object context) {
        this(new DummyState(), context);
    }

    public StateGraphImpl(State start) {
        this(start, null);
    }

    public StateGraphImpl(State start, Object context) {
        assert start != null;
        current = start;
        current.setGraph(this);

        this.context = context;
        this.registry = new HashMap<Object, State>();
    }

    public StateGraphImpl(Map<Object, State> registry, Object startKey) {
        this(registry, startKey, null);
    }

    public StateGraphImpl(Map<Object, State> registry, Object startKey, Object context) {
        assert registry != null;
        assert startKey != null;

        current = (State) registry.get(startKey);
        assert current != null;

        for (Object regKey : registry.keySet()) {
            State state = (State) registry.get(regKey);
            state.setGraph(this);
        }

        this.registry = registry;
        this.context = context;
    }

    public Object context() {
        return context;
    }

    public State current() {
        return current;
    }

    public boolean isAccepted() {
        return current.getType() == State.ACCEPT;
    }

    public boolean isErrored() {
        return current.getType() == State.ERROR;
    }

    public State recv(Object message) {
        return current = current.recv(message);
    }

    /**
     * Check if the target state is valid.
     * 
     * @param state
     *            the state to be checked.
     * @return true if the state is valid.
     */
    protected boolean checkState(State state) {
        return true;
    }

    public void jump(State target) {
        assert target != null;

        if (!checkState(target))
            throw new InvalidStateException();

        State source = current;

        leave(source);
        source.leave(target);

        current = target;

        target.enter(source);
        enter(target);
    }

    public void push(State state) {
        stack.push(current);
        jump(state);
    }

    public State pop() {
        if (stack.isEmpty())
            throw new EmptyStackException();

        State top = stack.pop();
        jump(top);

        return current;
    }

    /**
     * Graph scope entered-handler
     * 
     * @param current
     *            The entered state
     */
    protected void enter(State current) {
    }

    /**
     * Graph scope left-handler
     * 
     * @param current
     *            The left state
     */
    protected void leave(State current) {
    }

    public void add(Object key, State state) {
        assert key != null;
        assert state != null;
        if (registry.containsKey(key))
            throw new IllegalArgumentException("Key " + key 
                    + " is already existed."); 
        registry.put(key, state);
    }

    public void remove(Object key) {
        assert key != null;
        if (!registry.containsKey(key))
            throw new IllegalArgumentException(
                    "Key " + key + " isn\'t existed.");  
        registry.remove(key);
    }

    public State get(Object key) {
        assert key != null;
        return (State) registry.get(key);
    }

    public Object find(State state) {
        assert state != null;
        for (Object key : registry.keySet()) {
            State st = (State) registry.get(key);
            if (st == state)
                return key;
        }
        return null;
    }

}
