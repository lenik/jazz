package net.bodz.bas.collection.table;

import java.util.Arrays;
import java.util.List;

import net.bodz.bas.util.exception.ReadOnlyException;

public class ArrayTable<T> implements Table<T> {

    private T[][] array;
    private int rows;
    private int columns;
    private List<String> rowNames;
    private List<String> columnNames;

    public int rows() {
        return rows;
    }

    public int columns() {
        return columns;
    }

    public T get(int row, int column) {
        return array[row][column];
    }

    public List<T> get(int row) {
        return Arrays.asList(array[row]);
    }

    public List<T> getColumn(int column) {
        // return null;
        // new SliceArrayVector.
        throw new UnsupportedOperationException();
    }

    public void set(int row, int column, T value) throws ReadOnlyException {
        array[row][column] = value;
    }

    public void set(int row, List<T> vector) throws ReadOnlyException {
        // array[row] = vector.toArray();
        throw new UnsupportedOperationException();
    }

    public void setColumn(int column, List<T> vector) throws ReadOnlyException {
        throw new UnsupportedOperationException();
    }

    public void add(List<T> vector) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public void add(int row, List<T> vector) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public void addColumn(List<T> vector) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public void addColumn(int column, List<T> vector) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public void remove(int row) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public void removeColumn(int column) throws ReadOnlyException {
        throw new ReadOnlyException();
    }

    public List<String> getNames() {
        return rowNames;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

}
