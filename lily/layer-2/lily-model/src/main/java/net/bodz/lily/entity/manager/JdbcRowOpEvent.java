package net.bodz.lily.entity.manager;

import java.util.EventObject;

import net.bodz.bas.db.ctx.DataContext;

public class JdbcRowOpEvent
        extends EventObject {

    private static final long serialVersionUID = 1L;

    JdbcRowOpType opType;
    DataContext dataContext;

    public JdbcRowOpEvent(Object source, JdbcRowOpType opType) {
        super(source);
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
