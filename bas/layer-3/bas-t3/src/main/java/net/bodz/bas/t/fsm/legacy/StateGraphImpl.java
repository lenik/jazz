package net.bodz.bas.t.fsm.legacy;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StateGraphImpl
        implements IStateGraph {

    private static final long serialVersionUID = 1L;

    protected Object context;
    protected IState current;

    private Stack<IState> stack;

    // <name, state>
    private Map<Object, IState> registry;

    public StateGraphImpl() {
        this(new DummyState(), null);
    }

    public StateGraphImpl(Object context) {
        this(new DummyState(), context);
    }

    public StateGraphImpl(IState start) {
        this(start, null);
    }

    public StateGraphImpl(IState start, Object context) {
        assert start != null;
        current = start;
        current.setGraph(this);

        this.context = context;
        this.registry = new HashMap<Object, IState>();
    }

    public StateGraphImpl(Map<Object, IState> registry, Object startKey) {
        this(registry, startKey, null);
    }

    public StateGraphImpl(Map<Object, IState> registry, Object startKey, Object context) {
        assert registry != null;
        assert startKey != null;

        current = (IState) registry.get(startKey);
        assert current != null;

        for (Object regKey : registry.keySet()) {
            IState state = (IState) registry.get(regKey);
            state.setGraph(this);
        }

        this.registry = registry;
        this.context = context;
    }

    public Object context() {
        return context;
    }

    public IState current() {
        return current;
    }

    public boolean isAccepted() {
        return current.getType() == IState.ACCEPT;
    }

    public boolean isErrored() {
        return current.getType() == IState.ERROR;
    }

    public IState recv(Object message) {
        return current = current.recv(message);
    }

    /**
     * Check if the target state is valid.
     * 
     * @param state
     *            the state to be checked.
     * @return true if the state is valid.
     */
    protected boolean checkState(IState state) {
        return true;
    }

    public void jump(IState target) {
        assert target != null;

        if (!checkState(target))
            throw new InvalidStateException();

        IState source = current;

        leave(source);
        source.leave(target);

        current = target;

        target.enter(source);
        enter(target);
    }

    public void push(IState state) {
        stack.push(current);
        jump(state);
    }

    public IState pop() {
        if (stack.isEmpty())
            throw new EmptyStackException();

        IState top = stack.pop();
        jump(top);

        return current;
    }

    /**
     * Graph scope entered-handler
     * 
     * @param current
     *            The entered state
     */
    protected void enter(IState current) {
    }

    /**
     * Graph scope left-handler
     * 
     * @param current
     *            The left state
     */
    protected void leave(IState current) {
    }

    public void add(Object key, IState state) {
        assert key != null;
        assert state != null;
        if (registry.containsKey(key))
            throw new IllegalArgumentException("Key " + key + " is already existed.");
        registry.put(key, state);
    }

    public void remove(Object key) {
        assert key != null;
        if (!registry.containsKey(key))
            throw new IllegalArgumentException("Key " + key + " isn\'t existed.");
        registry.remove(key);
    }

    public IState get(Object key) {
        assert key != null;
        return (IState) registry.get(key);
    }

    public Object find(IState state) {
        assert state != null;
        for (Object key : registry.keySet()) {
            IState st = (IState) registry.get(key);
            if (st == state)
                return key;
        }
        return null;
    }

}
