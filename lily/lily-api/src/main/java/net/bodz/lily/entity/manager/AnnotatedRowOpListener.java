package net.bodz.lily.entity.manager;

public class AnnotatedRowOpListener
        implements
            IJdbcRowOpListener {

    @Override
    public boolean beforeRowOperation(JdbcRowOpEvent event)
            throws Exception {
        return false;
    }

    @Override
    public void afterRowOperation(JdbcRowOpEvent event)
            throws Exception {
        IJdbcRowOpListener.super.afterRowOperation(event);
    }

}
