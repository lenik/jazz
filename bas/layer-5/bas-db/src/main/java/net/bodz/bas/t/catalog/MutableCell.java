package net.bodz.bas.t.catalog;

import net.bodz.bas.t.variant.AbstractVariant;
import net.bodz.bas.t.variant.IVariant;

public class MutableCell
        implements
            IMutableCell {

    public IRow row;
    int columnIndex;
    Object _data;

    private byte flags = 0;

    public MutableCell(IRow row) {
        this(row, row.getCellCount());
    }

    public MutableCell(IRow row, int columnIndex) {
        this.row = row;
        this.columnIndex = columnIndex;
    }

    public MutableCell(IRow row, String columnName) {
        this.row = row;
        IRowSet rowSet = row.getRowSet();
        if (rowSet == null)
            throw new NullPointerException("require rowSet.metadata to resolve column name");
        IRowSetMetadata rowSetMetadata = rowSet.getMetadata();
        IColumnMetadata metadata = rowSetMetadata.getColumn(columnName);
        this.columnIndex = metadata.getColumnIndex();
    }

    @Override
    public IColumnMetadata getMetadata() {
        IRowSet rowSet = row.getRowSet();
        if (rowSet != null) {
            IRowSetMetadata table = rowSet.getMetadata();
            int cc = table.getColumnCount();
            if (columnIndex < cc)
                return table.getColumn(columnIndex);
        }
        return null;
    }

    @Override
    public String getColumnName() {
        IColumnMetadata column = getMetadata();
        if (column == null)
            return null;
        else
            return column.getName();
    }

    @Override
    public int getColumnIndex() {
        return columnIndex;
    }

    @Override
    public IRow getRow() {
        return row;
    }

    @Override
    public Class<?> getCellType() {
        IColumnMetadata column = getMetadata();
        if (column != null)
            return column.getJavaClass();
        else
            return null;
    }

    @Override
    public Object getData() {
        return _data;
    }

    @Override
    public void setData(Object data) {
        if (data instanceof String) {
            String text = (String) data;
            data = text.intern();
        }
        this._data = data;
        this.setData();
        this.setDirty();
    }

    class VariantImpl
            extends AbstractVariant {

        @Override
        public Object get() {
            return _data;
        }

    }

    @Override
    public IVariant getDataVar() {
        return new VariantImpl();
    }

    @Override
    public void remove() {
        this._data = null;
        this.clearData();
        this.setDirty();
    }

    static final byte DATA_SET = 1;
    static final byte DIRTY = 2;

    @Override
    public boolean isSet() {
        return (flags & DATA_SET) != 0;
    }

    @Override
    public boolean isDirty() {
        return (flags & DIRTY) != 0;
    }

    public void setData() {
        flags |= DATA_SET;
    }

    public void clearData() {
        flags &= ~DATA_SET;
    }

    public void setDirty() {
        flags |= DIRTY;
    }

    @Override
    public void clearDirty() {
        flags &= ~DIRTY;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(_data);
        return sb.toString();
    }

}
