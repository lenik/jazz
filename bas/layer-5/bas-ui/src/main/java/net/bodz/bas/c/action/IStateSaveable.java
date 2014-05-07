package net.bodz.bas.c.action;

public interface IStateSaveable {

    Object saveState();

    void restoreState(Object state);

}
