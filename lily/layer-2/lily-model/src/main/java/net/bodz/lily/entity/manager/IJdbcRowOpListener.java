package net.bodz.lily.entity.manager;

public interface IJdbcRowOpListener {

    /**
     * @return <code>false</code> to cancel the operation.
     */
    boolean beforeRowOperation(JdbcRowOpEvent event)
            throws Exception;

    default void afterRowOperation(JdbcRowOpEvent event)
            throws Exception {
    }

}
