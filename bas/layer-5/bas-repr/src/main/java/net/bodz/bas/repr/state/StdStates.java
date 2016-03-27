package net.bodz.bas.repr.state;

public class StdStates
        implements IStateConsts {

    public static final int ID_INIT = 0;
    public static final int ID_OK = 1;
    public static final int ID_WAIT = 2;
    public static final int ID_USER = 100;
    public static final int ID_ERROR = -1;
    public static final int ID_EPSILON = -2;

    public static final State INIT = new State(ID_INIT, "init", StateType.NONTERM);
    public static final State OK = new State(ID_OK, "ok", StateType.ACCEPTED);
    public static final State WAIT = new State(ID_WAIT, "wait", StateType.ACCEPTED);
    public static final State ERROR = new State(ID_ERROR, "error", StateType.ERROR);
    public static final State EPSILON = new State(ID_EPSILON, "epsilon", StateType.NONTERM);

}
