package net.bodz.swt.controls;

import java.util.EventObject;

public class DetailSwitchEvent extends EventObject {

    private static final long serialVersionUID = -4823684865042327496L;

    public boolean            expanded;

    public DetailSwitchEvent(WindowComposite dc, boolean expanded) {
        super(dc);
        this.expanded = expanded;
    }

}
