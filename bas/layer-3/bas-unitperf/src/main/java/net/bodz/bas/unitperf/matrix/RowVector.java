package net.bodz.bas.unitperf.matrix;

import java.io.Serializable;
import java.util.Arrays;

/**
 * TODO
 */
public class RowVector
        extends AbstractVector
        implements Serializable {

    private static final long serialVersionUID = 1L;

    public RowVector(int... data) {
        super(data);
    }

    @Override
    protected Vector dup(int... data) {
        return new RowVector(Arrays.copyOf(data, data.length));
    }

    @Override
    public Vector transpose() {
        return new ColumnVector(Arrays.copyOf(data, data.length));
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer(data.length * 8 + 5);
        for (int i = 0; i < data.length; i++) {
            if (i != 0)
                buf.append(", ");
            buf.append(data[i]);
        }
        return buf.toString();
    }

    @Override
    public ArrayMatrix multiply(ArrayMatrix matrix) {
        return null;
    }

}
