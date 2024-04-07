package net.bodz.bas.t.catalog;

import net.bodz.bas.t.variant.AbstractVariant;
import net.bodz.bas.t.variant.IVariant;

public class MutableCell
        implements
            IMutableCell {

    IColumnMetadata metadata;
    Class<?> cellType;
    IRow row;

    Object data;
    boolean dataSet;
    boolean dirty;

    public MutableCell(IRow row) {
        this(row, Object.class);
    }

    public MutableCell(IRow row, Class<?> cellType) {
        this.row = row;
        this.cellType = cellType;
    }

    public MutableCell(IRow row, int columnIndex) {
        this.row = row;
        IRowSet rowSet = row.getRowSet();
        if (rowSet != null) {
            IRowSetMetadata table = rowSet.getMetadata();
            int cc = table.getColumnCount();
            if (columnIndex < cc) {
                IColumnMetadata column = table.getColumn(columnIndex);
                setMetadata(column);
            }
        }
    }

    public MutableCell(IRow row, String columnName) {
        this.row = row;
        IRowSet rowSet = row.getRowSet();
        if (rowSet != null) {
            IRowSetMetadata rowSetMetadata = rowSet.getMetadata();
            metadata = rowSetMetadata.getColumn(columnName);
            setMetadata(metadata);
        }
    }

    @Override
    public IColumnMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(IColumnMetadata metadata) {
        this.metadata = metadata;
        if (metadata != null)
            cellType = metadata.getJavaClass();
    }

    @Override
    public String getColumnName() {
        if (metadata == null)
            return null;
        else
            return metadata.getName();
    }

    @Override
    public int getColumnIndex() {
        if (metadata == null)
            return -1;
        else
            return metadata.getOrdinal();
    }

    @Override
    public IRow getRow() {
        return row;
    }

    @Override
    public Class<?> getCellType() {
        return cellType;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
        this.dataSet = true;
        this.dirty = true;
    }

    class VariantImpl
            extends AbstractVariant {

        @Override
        public Object get() {
            return data;
        }

    }

    @Override
    public IVariant getDataVar() {
        return new VariantImpl();
    }

    @Override
    public void remove() {
        this.data = null;
        this.dataSet = false;
        this.dirty = true;
    }

    @Override
    public boolean isSet() {
        return dataSet;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void clearDirty() {
        dirty = true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        return sb.toString();
    }

}
