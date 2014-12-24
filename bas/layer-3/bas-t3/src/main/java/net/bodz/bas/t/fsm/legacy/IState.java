package net.bodz.bas.t.fsm.legacy;

import java.io.Serializable;
import java.util.Map;

/**
 * State Object
 */
public interface IState
        extends Serializable {

    /**
     * Ordinary state
     */
    int NORMAL = 0;

    /**
     * Accepted state. Used in DFA/NFA.
     */
    int ACCEPT = 1;

    /**
     * Error state
     */
    int ERROR = -1;

    /**
     * Get the state name.
     *
     * @return The state name
     */
    String getName();

    /**
     * May be NORMAL, ACCEPT, ERROR
     *
     * @return The type of state.
     */
    int getType();

    /**
     * Get the owner graph.
     *
     * A state is appeared in only 1 graph.
     *
     * @return The owner graph
     */
//    IStateGraph getGraph();

    /**
     * Set the owner graph
     *
     * A state is appeared in only 1 graph.
     *
     * @param graph
     *            The new owner graph to set.
     */
    void setGraph(IStateGraph graph);

    /**
     * Receive message and do transition
     *
     * @param message
     *            The received message
     * @return null If message isn't recognized
     */
    IState recv(Object message);

    /**
     * Enter into this state
     *
     * @param prev
     *            The previous state enter from
     */
    void enter(IState prev);

    /**
     * Leave from this atate
     *
     * @param next
     *            The next state leave into
     */
    void leave(IState next);

    /**
     * Static message/target state transition map.
     *
     * This is the static part of the transition map, defined for help purpose, such as
     * visualization, etc.
     *
     * @return the map
     */
    Map<Object, IState> getStaticMap();

}
