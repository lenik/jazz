package net.bodz.bas.db.ctx;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.NoSuchKeyException;

public interface IDataHub {

    String K_MAIN = "main";
    String K_TEST = "test";
    String K_TEMPLATE = "template";

    /**
     * @return <code>null</code> if not defined.
     */
    ConnectOptions getConnectOptions(String key);

    /**
     * @throws NoSuchKeyException
     */
    ConnectOptions requireConnectOptions(String key);

    /**
     * @return <code>null</code> if not defined.
     */
    DataContext getDataContext(String key);

    /**
     * @throws NoSuchKeyException
     */
    DataContext requireDataContext(String key);

    default DataContext getMain() {
        return requireDataContext(K_MAIN);
    }

    default DataContext getTest() {
        return requireDataContext(K_TEST);
    }

    default ConnectOptions getTemplate() {
        return requireConnectOptions(K_TEMPLATE);
    }

}
