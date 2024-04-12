package net.bodz.bas.db.ibatis;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public class MyConfiguration
        extends Configuration {

    @Override
    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds,
            ParameterHandler parameterHandler, ResultHandler resultHandler, BoundSql boundSql) {
        ResultSetHandler resultSetHandler;
        ResultMap firstResultMap = mappedStatement.getResultMaps().get(0);
        Class<?> type = firstResultMap.getType();
        if (IResultSetForm.class.isAssignableFrom(type))
            resultSetHandler = new CustomResultSetHandler(executor, mappedStatement, parameterHandler, resultHandler,
                    boundSql, rowBounds);
        else
            resultSetHandler = new DefaultResultSetHandler(executor, mappedStatement, parameterHandler, resultHandler,
                    boundSql, rowBounds);
        return (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
    }

}
