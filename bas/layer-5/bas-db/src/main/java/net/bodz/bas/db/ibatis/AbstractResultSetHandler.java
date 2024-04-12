package net.bodz.bas.db.ibatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public class AbstractResultSetHandler
        extends DefaultResultSetHandler {

    public AbstractResultSetHandler(Executor executor, MappedStatement mappedStatement,
            ParameterHandler parameterHandler, ResultHandler<?> resultHandler, BoundSql boundSql, RowBounds rowBounds) {
        super(executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds);
    }

}
