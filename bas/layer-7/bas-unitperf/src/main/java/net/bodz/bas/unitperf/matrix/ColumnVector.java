package net.bodz.bas.unitperf.matrix;

import java.util.Arrays;

/**
 * TODO
 */
public class ColumnVector
        extends AbstractVector {

    private static final long serialVersionUID = 1L;

    public ColumnVector(int... data) {
        super(data);
    }

    @Override
    protected Vector dup(int... data) {
        return new ColumnVector(Arrays.copyOf(data, data.length));
    }

    @Override
    public Vector transpose() {
        return new RowVector(Arrays.copyOf(data, data.length));
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(data.length * 8 + 5);
        for (int i = 0; i < data.length; i++) {
            buf.append("[ " + data[i] + " ]\n");
        }
        return buf.toString();
    }

    @Override
    public ArrayMatrix multiply(ArrayMatrix matrix) {
        return null;
    }

}
