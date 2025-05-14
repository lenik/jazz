package net.bodz.bas.db.sql;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.meta.decl.NotNull;

public interface IDataType {

    @NotNull
    Class<?> getJavaClass();

    int getSqlType();

    @NotNull
    String getSqlTypeName();

    boolean isSqlTypeDefault();

    @NotNull
    String getSqlClassName();

//    Class<?> getSqlClass();

    Class<?> getSqlClass(IResultColumnMetaData metaData);

}
