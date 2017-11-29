package net.bodz.bas.repr.state;

@Start(0)
public class StdStates
        extends StateGroup {

    public static final int ID_INIT = 0;
    public static final int ID_OK = 1;
    public static final int ID_WAIT = 2;
    public static final int ID_USER = 100;
    public static final int ID_ERROR = -1;
    public static final int ID_EPSILON = -2;

    public static final StdStates INSTANCE = new StdStates();

    protected static StateBuilder state(int id) {
        return StateGroup.state(id, StdStates.INSTANCE).declaring(StdStates.class);
    }

    public static final State INIT = state(ID_INIT).name("init").type(StateType.NONTERM).build();
    public static final State OK = state(ID_OK).name("ok").build();
    public static final State WAIT = state(ID_WAIT).name("wait").build();
    public static final State ERROR = state(ID_ERROR).name("error").type(StateType.ERROR).build();
    public static final State EPSILON = state(ID_EPSILON).name("epsilon").type(StateType.NONTERM).build();

}
