package net.bodz.bas.db.ibatis;

import java.sql.SQLException;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetWrapper;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

public class CustomResultSetHandler
        extends DefaultResultSetHandler {

    public CustomResultSetHandler(Executor executor, MappedStatement mappedStatement, ParameterHandler parameterHandler,
            ResultHandler<?> resultHandler, BoundSql boundSql, RowBounds rowBounds) {
        super(executor, mappedStatement, parameterHandler, resultHandler, boundSql, rowBounds);
    }

    static class MyHandler
            extends DecoratedResultHandler<IResultSetForm> {

        private static final long serialVersionUID = 1L;

        ResultSetWrapper rsw;

        public MyHandler(ResultHandler<IResultSetForm> _orig, ResultSetWrapper rsw) {
            super(_orig);
            this.rsw = rsw;
        }

        @Override
        public void handleResult(ResultContext<? extends IResultSetForm> resultContext) {
            super.handleResult(resultContext);

            IResultSetForm form = resultContext.getResultObject();
            try {
                form.readObject(rsw.getResultSet());
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }

    };

    @Override
    public void handleRowValues(ResultSetWrapper rsw, ResultMap resultMap, ResultHandler<?> resultHandler,
            RowBounds rowBounds, ResultMapping parentMapping)
            throws SQLException {

        @SuppressWarnings("unchecked")
        ResultHandler<IResultSetForm> _rh = (ResultHandler<IResultSetForm>) resultHandler;

        MyHandler myHandler = new MyHandler(_rh, rsw);
        super.handleRowValues(rsw, resultMap, myHandler, rowBounds, parentMapping);
    }

}
