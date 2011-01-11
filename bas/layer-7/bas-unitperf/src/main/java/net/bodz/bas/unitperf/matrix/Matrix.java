package net.bodz.bas.unitperf.matrix;

import net.bodz.bas.exceptions.IllegalUsageException;

public interface Matrix {

    int getRows();

    int getColumns();

    /**
     * @param rowIndex
     *            0-based row index
     * @param columnIndex
     *            0-based column index
     * @return cell value at specified position.
     */
    int getCell(int rowIndex, int columnIndex);

    /**
     * @param rowIndex
     *            0-based row index
     * @param columnIndex
     *            0-based column index
     * @return new value at specified position.
     */
    void setCell(int rowIndex, int columnIndex, int value);

    void swapRow(int row1, int row2);

    void swapColumn(int column1, int column2);

    Matrix transpose();

    Matrix inverse()
            throws IllegalUsageException;

    void diag(int k);

    void polyEval(int[] cv);

}
