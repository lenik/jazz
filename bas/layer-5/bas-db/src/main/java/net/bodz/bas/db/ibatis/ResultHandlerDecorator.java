package net.bodz.bas.db.ibatis;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import net.bodz.bas.t.model.AbstractDecorator;

public class ResultHandlerDecorator<T>
        extends AbstractDecorator<ResultHandler<T>>
        implements
            ResultHandler<T> {

    private static final long serialVersionUID = 1L;

    public ResultHandlerDecorator(ResultHandler<T> _orig) {
        super(_orig);
    }

    @Override
    public void handleResult(ResultContext<? extends T> resultContext) {
        getWrapped().handleResult(resultContext);
    }

}
