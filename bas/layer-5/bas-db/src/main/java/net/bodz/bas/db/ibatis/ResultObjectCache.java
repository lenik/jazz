package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

public class ResultObjectCache<T>
        extends ResultHandlerDecorator<T> {

    private static final long serialVersionUID = 1L;

    T resultObject;

    public ResultObjectCache(ResultHandler<T> _orig) {
        super(_orig);
    }

    @Override
    public void handleResult(ResultContext<? extends T> resultContext) {
        super.handleResult(resultContext);
        resultObject = resultContext.getResultObject();
    }

    public T getResultObject() {
        return resultObject;
    }

}
