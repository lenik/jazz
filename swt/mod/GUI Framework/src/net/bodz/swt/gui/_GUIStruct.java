package net.bodz.swt.gui;

import java.util.ArrayList;
import java.util.Collection;

public class _GUIStruct extends ArrayList<GUIVar<?>> implements GUIStruct {

    private static final long serialVersionUID = -7550168523709806288L;

    public _GUIStruct() {
        super();
    }

    public _GUIStruct(Collection<? extends GUIVar<?>> c) {
        super(c);
    }

    public _GUIStruct(int initialCapacity) {
        super(initialCapacity);
    }

}
