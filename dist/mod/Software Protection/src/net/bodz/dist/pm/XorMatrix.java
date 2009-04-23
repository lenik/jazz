package net.bodz.dist.pm;

import java.util.Arrays;

public class XorMatrix implements Cloneable {

    private final int[] m;
    private final int   rows;
    private final int   cols;

    public XorMatrix(int rows, int cols) {
        this(cols, new int[rows * cols]);
    }

    public XorMatrix(int cols, int... init) {
        assert cols >= 1;
        assert init != null;
        assert init.length % cols == 0;
        this.rows = init.length / cols;
        if (rows != cols)
            throw new IllegalArgumentException("not a square-matrix");
        this.cols = cols;
        this.m = init;
    }

    @Override
    public XorMatrix clone() {
        int[] m2 = Arrays.copyOf(m, m.length);
        return new XorMatrix(cols, m2);
    }

    public int get(int row, int col) {
        int p = row * cols + col;
        return m[p];
    }

    public void set(int row, int col, int value) {
        int p = row * cols + col;
        m[p] = value;
    }

    void checkSameSize(XorMatrix m) {
        if (cols != m.cols)
            throw new IllegalArgumentException("diff cols: " + cols + "/" + m.cols);
        if (rows != m.rows)
            throw new IllegalArgumentException("diff rows: " + rows + "/" + m.rows);
    }

    void checkMultiplyWith(XorMatrix m) {
        if (cols != m.rows)
            throw new IllegalArgumentException("can't multiply with " + m);
    }

    public void add(XorMatrix b) {
        checkSameSize(b);
        for (int i = 0; i < m.length; i++)
            m[i] ^= b.m[i];
    }

    public XorMatrix multiply(XorMatrix b) {
        checkMultiplyWith(b);
        int an = rows;
        assert cols == b.rows;
        int mn = cols;
        int bm = b.cols;
        XorMatrix out = new XorMatrix(an, bm);
        for (int r = 0; r < an; r++) {
            for (int c = 0; c < bm; c++) {
                int sum = 0;
                for (int x = 0; x < mn; x++) {
                    sum ^= get(r, x) ^ b.get(x, c);
                }
                out.set(r, c, sum);
            }
        }
        return out;
    }

    @Override
    public String toString() {
        int size = rows * cols;
        StringBuffer buf = new StringBuffer(size * 10);
        for (int r = 0; r < rows; r++) {
            if (r != 0)
                buf.append("\n");
            buf.append("[ ");
            for (int c = 0; c < cols; c++) {
                if (c != 0)
                    buf.append(" ");
                int n = get(r, c);
                String x = Integer.toString(n, 16);
                buf.append(x);
            }
            buf.append("]");
        }
        return buf.toString();
    }

}
