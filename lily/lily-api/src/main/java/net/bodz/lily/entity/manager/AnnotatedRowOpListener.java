package net.bodz.lily.entity.manager;

public class AnnotatedRowOpListener
        implements IJdbcRowOpListener {

    @Override
    public boolean beforeRowOperation(JdbcRowOpEvent event, Object context)
            throws Exception {
        return false;
    }

}
