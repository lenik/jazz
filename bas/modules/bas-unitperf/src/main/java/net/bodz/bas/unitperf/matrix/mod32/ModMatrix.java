package net.bodz.bas.unitperf.matrix.mod32;

import net.bodz.bas.unitperf.matrix.ArrayMatrix;

public class ModMatrix
        extends ArrayMatrix {

    private static final long serialVersionUID = 1L;

    public ModMatrix(int rows, int columns, int... data) {
        super(rows, columns, data);
    }

    public ModMatrix(int rows, int columns) {
        super(rows, columns);
    }

}
