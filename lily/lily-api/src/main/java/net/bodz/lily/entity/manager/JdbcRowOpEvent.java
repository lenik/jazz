package net.bodz.lily.entity.manager;

import java.util.EventObject;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.IDataContextAware;

public class JdbcRowOpEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    JdbcRowOpType opType;
    DataContext dataContext;

    public JdbcRowOpEvent(Object source, JdbcRowOpType opType) {
        super(source);
        if (source instanceof IDataContextAware aware)
            dataContext = aware.getDataContext();
        this.opType = opType;
    }

    public JdbcRowOpType getOpType() {
        return opType;
    }

    public void setOpType(JdbcRowOpType opType) {
        this.opType = opType;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

}
