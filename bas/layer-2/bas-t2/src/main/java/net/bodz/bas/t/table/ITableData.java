package net.bodz.bas.t.table;

import java.util.List;

import net.bodz.bas.err.ReadOnlyException;

public interface ITableData<cell_t> {

    int rows();

    int columns();

    cell_t get(int row, int column);

    List<cell_t> get(int row);

    List<cell_t> getColumn(int column);

    void set(int row, int column, cell_t value)
            throws ReadOnlyException;

    void set(int row, List<cell_t> vector)
            throws ReadOnlyException;

    void setColumn(int column, List<cell_t> vector)
            throws ReadOnlyException;

    void add(List<cell_t> vector)
            throws ReadOnlyException;

    void add(int row, List<cell_t> vector)
            throws ReadOnlyException;

    void addColumn(List<cell_t> vector)
            throws ReadOnlyException;

    void addColumn(int column, List<cell_t> vector)
            throws ReadOnlyException;

    void remove(int row)
            throws ReadOnlyException;

    void removeColumn(int column)
            throws ReadOnlyException;

    List<String> getNames();

    List<String> getColumnNames();

}
