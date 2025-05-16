package net.bodz.bas.db.sql;

import java.util.function.Function;

import net.bodz.bas.db.jdbc.util.IResultColumnMetaData;

public interface IRefiner
        extends Function<IResultColumnMetaData, Class<?>> {

    @Override
    Class<?> apply(IResultColumnMetaData column);

}
