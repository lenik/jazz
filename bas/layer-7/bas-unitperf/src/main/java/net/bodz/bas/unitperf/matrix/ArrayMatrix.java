package net.bodz.bas.unitperf.matrix;

import java.io.Serializable;

/**
 * TODO
 */
public abstract class ArrayMatrix
        implements Matrix, Serializable {

    private static final long serialVersionUID = 1L;

    private final int rows;
    private final int columns;
    private final int[] data;

    public ArrayMatrix(int rows, int columns) {
        this(rows, columns, new int[rows * columns]);
    }

    public ArrayMatrix(int rows, int columns, int... data) {
        if (rows < 0)
            throw new IllegalArgumentException("rows < 0: " + rows);
        if (columns < 0)
            throw new IllegalArgumentException("columns < 0: " + columns);
        if (data == null)
            throw new NullPointerException("data");
        if (data.length != rows * columns)
            throw new IllegalArgumentException("invalid data bounds: " + data.length);
        this.rows = rows;
        this.columns = columns;
        this.data = data;
    }

    public final int getRows() {
        return rows;
    }

    public final int getColumns() {
        return columns;
    }

    /**
     * @param rowIndex
     *            0-based row index
     * @param columnIndex
     *            0-based column index
     * @return cell value at specified index.
     */
    public final int get(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= rows)
            throw new IndexOutOfBoundsException(String.format("Row %d/%d", rowIndex, rows));
        if (columnIndex < 0 || columnIndex >= columns)
            throw new IndexOutOfBoundsException(String.format("Column %d/%d", columnIndex, columns));
        return data[rowIndex * columns + columnIndex];
    }

    public final void set(int rowIndex, int columnIndex, int value) {
        if (rowIndex < 0 || rowIndex >= rows)
            throw new IndexOutOfBoundsException(String.format("Row %d/%d", rowIndex, rows));
        if (columnIndex < 0 || columnIndex >= columns)
            throw new IndexOutOfBoundsException(String.format("Column %d/%d", columnIndex, columns));
        data[rowIndex * columns + columnIndex] = value;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(rows * (columns * 8 + 5));
        int index = 0;
        for (int y = 0; y < rows; y++) {
            buf.append("[ ");
            for (int x = 0; x < columns; x++) {
                int val = data[index++];
                if (x != 0)
                    buf.append(", ");
                buf.append(val);
            }
            buf.append(" ]\n");
        }
        return buf.toString();
    }

}
