package net.bodz.bas.db.meta;

public interface IGenericColumnType<ColumnType> {

    String getName(ColumnType column);

    int getType(ColumnType column);

    String getTypeName(ColumnType column);

    String getClassName(ColumnType column);

}
