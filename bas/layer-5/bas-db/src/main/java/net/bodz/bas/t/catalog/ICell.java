package net.bodz.bas.t.catalog;

import net.bodz.bas.t.variant.IVariant;

public interface ICell {

    IColumnMetadata getMetadata();

    String getColumnName();

    int getColumnIndex();

    IRow getRow();

    Class<?> getCellType();

    Object getData();

    IVariant getDataVar();

    boolean isSet();

}
