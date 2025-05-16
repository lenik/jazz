package net.bodz.bas.db.sql;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.typer.std.ISampleGenerator;

public class DataType
        implements IDataType,
                   Comparable<DataType> {

    final Class<?> javaClass;
    final int sqlType;
    final String sqlTypeName;
    final boolean sqlTypeDefault;
    final String sqlClassName;
    final Class<?> sqlClass;
    final IRefiner refiner;
    final ISampleGenerator<?> generator;

    DataType(@NotNull Class<?> javaClass, //
            int sqlType, @NotNull String sqlTypeName, boolean sqlTypeDefault,//
            @NotNull String sqlClassName, Class<?> sqlClass,//
            IRefiner refiner, ISampleGenerator<?> generator) {
        this.javaClass = javaClass;
        this.sqlType = sqlType;
        this.sqlTypeName = sqlTypeName;
        this.sqlTypeDefault = sqlTypeDefault;
        this.sqlClassName = sqlClassName;
        this.sqlClass = sqlClass;

        if (refiner == null)
            refiner = c -> javaClass;
        this.refiner = refiner;
        this.generator = generator;
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

    @NotNull
    @Override
    public IRefiner getRefiner() {
        return refiner;
    }

    @Override
    public ISampleGenerator<?> getGenerator() {
        return generator;
    }

    @Override
    public String toString() {
        return javaClass.getName();
    }

    @Override
    public int compareTo(@NotNull DataType o) {
        if (this == o)
            return 0;
        boolean default1 = isSqlTypeDefault();
        boolean default2 = o.isSqlTypeDefault();
        if (default1 != default2)
            return default1 ? -1 : 1;
        return -1;
    }

    public static DataType.Builder newType(Class<?> type, int sqlType) {
        return DataType.builder().javaClass(type).sqlType(sqlType);
    }

    public static DataType.Builder newType(Class<?> type, int sqlType, String name) {
        return DataType.builder().javaClass(type).sqlType(sqlType).sqlTypeName(name);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Class<?> javaClass;
        private int sqlType;
        private String sqlTypeName;
        private boolean sqlTypeDefault;
        private String sqlClassName;
        private Class<?> sqlClass;
        private IRefiner refiner = null;
        private ISampleGenerator<?> generator;

        public Builder javaClass(@NotNull Class<?> javaClass) {
            this.javaClass = javaClass;
            if (sqlClass == null)
                sqlClass = javaClass;
            if (sqlClassName == null)
                sqlClassName = javaClass.getName();
            return this;
        }

        public Builder sqlType(int sqlType) {
            this.sqlType = sqlType;
            return this;
        }

        public Builder sqlTypeDefault(int sqlType) {
            this.sqlType = sqlType;
            this.sqlTypeDefault = true;
            return this;
        }

        public Builder sqlTypeName(@NotNull String sqlTypeName) {
            this.sqlTypeName = sqlTypeName;
            return this;
        }

        public Builder sqlClassName(@NotNull String sqlClassName) {
            this.sqlClassName = sqlClassName;
            return this;
        }

        public Builder sqlClass(@NotNull Class<?> sqlClass) {
            this.sqlClass = sqlClass;
            if (sqlClassName == null)
                sqlClassName = sqlClass.getName();
            return this;
        }

        public Builder refiner(@NotNull IRefiner refiner) {
            this.refiner = refiner;
            return this;
        }

        public Builder generator(@NotNull ISampleGenerator<?> generator) {
            this.generator = generator;
            return this;
        }

        public DataType build() {
            return new DataType(javaClass, sqlType, sqlTypeName, sqlTypeDefault, //
                    sqlClassName, sqlClass, refiner, generator);
        }

    }

}
