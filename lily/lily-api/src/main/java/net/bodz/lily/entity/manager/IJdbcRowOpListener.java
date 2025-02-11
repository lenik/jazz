package net.bodz.lily.entity.manager;

import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType
public interface IJdbcRowOpListener {

    /**
     * @return <code>false</code> to cancel the operation.
     */
    boolean beforeRowOperation(JdbcRowOpEvent event, Object context)
            throws Exception;

    default void afterRowOperation(JdbcRowOpEvent event, Object context)
            throws Exception {
    }

}
