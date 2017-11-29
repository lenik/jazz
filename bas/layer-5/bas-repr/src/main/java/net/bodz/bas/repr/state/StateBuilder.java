package net.bodz.bas.repr.state;

public class StateBuilder {

    private StateGroup group;
    private Class<?> declaringClass;
    private int id;
    private String name;
    private StateType type = StateType.ACCEPTED;

    public StateBuilder group(StateGroup group) {
        this.group = group;
        return this;
    }

    public StateBuilder declaring(Class<?> c) {
        this.declaringClass = c;
        return this;
    }

    public StateBuilder id(int id) {
        this.id = id;
        return this;
    }

    public StateBuilder name(String name) {
        this.name = name;
        return this;
    }

    public StateBuilder type(StateType type) {
        this.type = type;
        return this;
    }

    public State build() {
        State state = new State(id, name, type, declaringClass);
        if (group != null)
            group.addValue(state);
        return state;
    }

}
