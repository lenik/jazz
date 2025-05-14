package net.bodz.bas.db.sql;

import java.util.function.Function;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.meta.decl.NotNull;

public class DataType
        implements IDataType {

    final Class<?> javaClass;
    final int sqlType;
    final String sqlTypeName;
    final boolean sqlTypeDefault;
    final String sqlClassName;
    final Class<?> sqlClass;
    final Function<IResultColumnMetaData, Class<?>> refiner;

    public DataType(@NotNull Class<?> javaClass, int sqlType, @NotNull String sqlTypeName) {
        this(javaClass, sqlType, sqlTypeName, false);
    }

    public DataType(@NotNull Class<?> javaClass, int sqlType, @NotNull String sqlTypeName, boolean sqlTypeDefault) {
        this(javaClass, sqlType, sqlTypeName, false, null);
    }

    public DataType(@NotNull Class<?> javaClass, int sqlType, @NotNull String sqlTypeName, Function<IResultColumnMetaData, Class<?>> refiner) {
        this(javaClass, sqlType, sqlTypeName, false, javaClass.getName(), javaClass, refiner);
    }

    public DataType(@NotNull Class<?> javaClass, int sqlType, @NotNull String sqlTypeName, boolean sqlTypeDefault, Function<IResultColumnMetaData, Class<?>> refiner) {
        this(javaClass, sqlType, sqlTypeName, sqlTypeDefault, javaClass.getName(), javaClass, refiner);
    }

    public DataType(@NotNull Class<?> javaClass, int sqlType, @NotNull String sqlTypeName, boolean sqlTypeDefault, @NotNull String sqlClassName, Class<?> sqlClass, Function<IResultColumnMetaData, Class<?>> refiner) {
        this.javaClass = javaClass;
        this.sqlType = sqlType;
        this.sqlTypeName = sqlTypeName;
        this.sqlTypeDefault = sqlTypeDefault;
        this.sqlClassName = sqlClassName;
        this.sqlClass = sqlClass;
        if (refiner == null)
            refiner = c -> sqlClass;
        this.refiner = refiner;
    }

    @NotNull
    @Override
    public Class<?> getJavaClass() {
        return javaClass;
    }

    @Override
    public int getSqlType() {
        return sqlType;
    }

    @NotNull
    @Override
    public String getSqlTypeName() {
        return sqlTypeName;
    }

    @Override
    public boolean isSqlTypeDefault() {
        return sqlTypeDefault;
    }

    @NotNull
    @Override
    public String getSqlClassName() {
        return sqlClassName;
    }

    @Override
    public Class<?> getSqlClass() {
        return sqlClass;
    }

    @Override
    public Class<?> getSqlClass(IResultColumnMetaData metaData) {
        return refiner.apply(metaData);
    }

    @Override
    public String toString() {
        return javaClass.getName();
    }

}
