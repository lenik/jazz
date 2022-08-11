package net.bodz.bas.db.ctx;

import java.util.Arrays;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IAutoPriority;

@IndexedType(includeAbstract = false)
public interface IDataContextProvider
        extends
            IAutoPriority {

    String K_MAIN = "main";
    String K_TEST = "test";
    String K_TEMPLATE = "template";

    /**
     * @return <code>null</code> if not defined.
     */
    ConnectOptions getConnectOptions(String key);

    default ConnectOptions getConnectOptions(String... keys) {
        for (String key : keys) {
            ConnectOptions connectOptions = getConnectOptions(key);
            if (connectOptions != null)
                return connectOptions;
        }
        return null;
    }

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
     * @throws NoSuchKeyException
     */
    default ConnectOptions requireConnectOptions(String... keys) {
        ConnectOptions options = getConnectOptions(keys);
        if (options == null)
            throw new NoSuchKeyException(Arrays.asList(keys).toString());
        return options;
    }

    /**
     * @return <code>null</code> if not defined.
     */
    DataContext getDataContext(String key);

    default DataContext getDataContext(String... keys) {
        for (String key : keys) {
            DataContext dataContext = getDataContext(key);
            if (dataContext != null)
                return dataContext;
        }
        return null;
    }

    /**
     * @throws NoSuchKeyException
     */
    default DataContext requireDataContext(String key) {
        DataContext dataContext = getDataContext(key);
        if (dataContext == null)
            throw new NoSuchKeyException(key);
        return dataContext;
    }

    /**
     * @throws NoSuchKeyException
     */
    default DataContext requireDataContext(String... keys) {
        DataContext dataContext = getDataContext(keys);
        if (dataContext == null)
            throw new NoSuchKeyException(Arrays.asList(keys).toString());
        return dataContext;
    }

}
