package net.bodz.swt.gui.pfl;

import java.util.EventObject;

import net.bodz.swt.nls.GUINLS;

public class WizardExitEvent extends EventObject {

    private static final long serialVersionUID = 2285635185383477276L;

    public String             address;

    public WizardExitEvent(Object source, String address) {
        super(source);
        this.address = address;
    }

    @Override
    public String toString() {
        return GUINLS.getString("WizardExitEvent.exitFrom") + address; //$NON-NLS-1$
    }

}
