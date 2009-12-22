package net.bodz.bas.fsm.base;

import java.io.Serializable;

/**
 * State Context
 */
public interface StateGraph extends Serializable {

    Object context();

    State current();

    boolean isAccepted();

    boolean isErrored();

    State recv(Object message);

    void jump(State state);

    void push(State state);

    State pop();

    // registry of named states
    // a group of states could find each other thru the registry.
    // key is compared by "equals".

    State get(Object key);

    Object find(State state);

    void add(Object key, State state);

    void remove(Object key);

}
