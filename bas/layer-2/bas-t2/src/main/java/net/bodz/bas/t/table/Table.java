package net.bodz.bas.t.table;

import java.util.List;

import net.bodz.bas.err.ReadOnlyException;

public interface Table<T> {

    int rows();

    int columns();

    T get(int row, int column);

    List<T> get(int row);

    List<T> getColumn(int column);

    void set(int row, int column, T value)
            throws ReadOnlyException;

    void set(int row, List<T> vector)
            throws ReadOnlyException;

    void setColumn(int column, List<T> vector)
            throws ReadOnlyException;

    void add(List<T> vector)
            throws ReadOnlyException;

    void add(int row, List<T> vector)
            throws ReadOnlyException;

    void addColumn(List<T> vector)
            throws ReadOnlyException;

    void addColumn(int column, List<T> vector)
            throws ReadOnlyException;

    void remove(int row)
            throws ReadOnlyException;

    void removeColumn(int column)
            throws ReadOnlyException;

    List<String> getNames();

    List<String> getColumnNames();

}
