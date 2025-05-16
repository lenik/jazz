package net.bodz.bas.db.sql;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.catalog.SqlTypeEnum;
import net.bodz.bas.typer.std.ISampleGenerator;

public interface IDataType {

    @NotNull
    Class<?> getJavaClass();

    int getSqlType();

    default SqlTypeEnum getSqlTypeEnum() {
        return SqlTypeEnum.forSQLType(getSqlType(), SqlTypeEnum.OTHER);
    }

    @NotNull
    String getSqlTypeName();

    boolean isSqlTypeDefault();

    @NotNull
    String getSqlClassName();

    Class<?> getSqlClass();

    Class<?> getSqlClass(IResultColumnMetaData metaData);

    @NotNull
    IRefiner getRefiner();

    ISampleGenerator<?> getGenerator();

}
