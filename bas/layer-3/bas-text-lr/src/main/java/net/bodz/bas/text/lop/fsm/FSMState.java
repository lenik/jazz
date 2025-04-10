package net.bodz.bas.text.lop.fsm;

import java.util.Map;

import net.bodz.bas.io.ILookAhead;

public interface FSMState {

    int getId();

    String getName();

    FSMState next(int input);

    FSMState next(int input, int lookahead);

    FSMState next(int input, ILookAhead lookahead);

    Map<CharSet, FSMState> getTransitionMap();

}
