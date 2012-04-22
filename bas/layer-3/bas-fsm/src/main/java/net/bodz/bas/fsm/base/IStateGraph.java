package net.bodz.bas.fsm.base;

import java.io.Serializable;

/**
 * State Context
 */
public interface IStateGraph extends Serializable {

    Object context();

    IState current();

    boolean isAccepted();

    boolean isErrored();

    IState recv(Object message);

    void jump(IState state);

    void push(IState state);

    IState pop();

    // registry of named states
    // a group of states could find each other thru the registry.
    // key is compared by "equals".

    IState get(Object key);

    Object find(IState state);

    void add(Object key, IState state);

    void remove(Object key);

}
