package net.bodz.bas.repr.state;

public class StdStates
        implements IStateConsts {

    public static final int ID_START = 0;
    public static final int ID_ACCEPTED = 1;
    public static final int ID_ERROR = -1;
    public static final int ID_EPSILON = -2;

    public static final State START = new State(ID_START, "start", StateType.NONTERM);
    public static final State ACCEPTED = new State(ID_ACCEPTED, "accepted", StateType.ACCEPTED);
    public static final State ERROR = new State(ID_ERROR, "error", StateType.ERROR);
    public static final State EPSILON = new State(ID_EPSILON, "epsilon", StateType.NONTERM);

}
