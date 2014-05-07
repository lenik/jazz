package net.bodz.bas.c.action;

import net.bodz.bas.c.event.IStateChangingSource;

public interface IStated
        extends IStateChangingSource {

    int getState();

}
