package net.bodz.bas.esm.extern;

import net.bodz.bas.esm.EsmModule;
import net.bodz.bas.esm.EsmName;

public class Esm_moment
        extends EsmModule {

    public Esm_moment(int priority) {
        super("moment-timezone", priority);
    }

    public final EsmName Moment = main._interface("Moment");

}
