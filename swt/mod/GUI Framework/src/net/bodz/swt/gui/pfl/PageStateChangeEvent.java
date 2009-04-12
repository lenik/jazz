package net.bodz.swt.gui.pfl;

import java.util.EventObject;

import net.bodz.swt.nls.GUINLS;

public class PageStateChangeEvent extends EventObject {

    private static final long serialVersionUID = 6144420226013373314L;

    public Object             lastState;
    public Object             state;

    public PageStateChangeEvent(Object source, Object lastState, Object state) {
        super(source);
        this.lastState = lastState;
        this.state = state;
    }

    @Override
    public String toString() {
        return GUINLS.getString("PageStateChangeEvent.stateChanged") + lastState + " -> " + state; //$NON-NLS-1$ //$NON-NLS-2$
    }

}
