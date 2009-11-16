package net.bodz.bas.types.sg;

import java.io.Serializable;
import java.util.Map;

/**
 * State Object
 */
public interface State extends Serializable {

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
    int ERROR = 2;

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
    StateGraph getGraph();

    /**
     * Set the owner graph
     * 
     * A state is appeared in only 1 graph.
     * 
     * @param graph
     *            The new owner graph to set.
     */
    void setGraph(StateGraph graph);

    /**
     * Receive message and do transition
     * 
     * @param message
     *            The received message
     * @return null If message isn't recognized
     */
    State recv(Object message);

    /**
     * Enter into this state
     * 
     * @param prev
     *            The previous state enter from
     */
    void enter(State prev);

    /**
     * Leave from this atate
     * 
     * @param next
     *            The next state leave into
     */
    void leave(State next);

    /**
     * Static message/target state transition map.
     * 
     * This is the static part of the transition map, defined for help purpose, such as
     * visualization, etc.
     * 
     * @return the map
     */
    Map<Object, State> getStaticMap();

}
