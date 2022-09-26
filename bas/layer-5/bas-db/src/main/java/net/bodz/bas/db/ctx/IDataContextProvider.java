package net.bodz.bas.db.ctx;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IAutoPriority;

@IndexedType(includeAbstract = false)
public interface IDataContextProvider
        extends
            IAutoPriority {

    /**
     * @return <code>null</code> if not defined.
     */
    ConnectOptions getConnectOptions(String key);

    /**
     * @throws NoSuchKeyException
     */
    default ConnectOptions requireConnectOptions(String key) {
        ConnectOptions options = getConnectOptions(key);
        if (options == null)
            throw new NoSuchKeyException(key);
        return options;
    }

    /**
     * @return <code>null</code> if not defined.
     */
    DataContext getDataContext(String key);

    /**
     * @throws NoSuchKeyException
     */
    default DataContext requireDataContext(String key) {
        DataContext dataContext = getDataContext(key);
        if (dataContext == null)
            throw new NoSuchKeyException(key);
        return dataContext;
    }

}
