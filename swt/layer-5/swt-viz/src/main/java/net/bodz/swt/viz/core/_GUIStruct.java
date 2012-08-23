package net.bodz.swt.viz.core;

import java.util.ArrayList;
import java.util.Collection;


public class _GUIStruct
        extends ArrayList<SwtEntry<?>>
        implements GUIStruct {

    private static final long serialVersionUID = -7550168523709806288L;

    public _GUIStruct() {
        super();
    }

    public _GUIStruct(Collection<? extends SwtEntry<?>> c) {
        super(c);
    }

    public _GUIStruct(int initialCapacity) {
        super(initialCapacity);
    }

}
