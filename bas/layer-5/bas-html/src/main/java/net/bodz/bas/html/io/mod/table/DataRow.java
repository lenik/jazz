package net.bodz.bas.html.io.mod.table;

import java.util.ArrayList;
import java.util.Collection;

public class DataRow
        extends ArrayList<StringBuilder> {

    private static final long serialVersionUID = 1L;

    public DataRow() {
        super();
    }

    public DataRow(Collection<? extends StringBuilder> c) {
        super(c);
    }

    public DataRow(int initialCapacity) {
        super(initialCapacity);
    }

}
