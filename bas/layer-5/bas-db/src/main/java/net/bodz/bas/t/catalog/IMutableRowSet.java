package net.bodz.bas.t.catalog;

public interface IMutableRowSet
        extends
            IRowSet {

    @Override
    IMutableRow getRow(int index);

    void setRow(int rowIndex, IMutableRow row);

    IMutableRow addNewRow();

    void addRow(IMutableRow row);

    void addRow(int rowIndex, IMutableRow row);

    void removeRow(IMutableRow row);

    void removeRow(int rowIndex);

    void removeAll();

}
