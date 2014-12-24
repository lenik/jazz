package net.bodz.lily.model.base;

import net.bodz.bas.repr.state.State;
import net.bodz.bas.repr.state.StateType;

public interface States {

    State S_INIT = new State("init", StateType.NONTERM);
    State S_ACCEPT = new State("accept", StateType.NONTERM);

}
