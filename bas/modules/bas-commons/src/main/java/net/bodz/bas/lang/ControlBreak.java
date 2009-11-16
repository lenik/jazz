package net.bodz.bas.lang;

import net.bodz.bas.functors.ControlGoto;

public class ControlBreak extends ControlGoto {

    private static final long serialVersionUID = -732592070241912407L;

    public ControlBreak() {
        super(null);
    }

    public ControlBreak(Object label) {
        super(label);
    }

    @Override
    public Object getLabel() {
        return this.getDetail();
    }

}
