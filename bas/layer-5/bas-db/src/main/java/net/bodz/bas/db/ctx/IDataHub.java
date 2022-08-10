package net.bodz.bas.db.ctx;

import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.bas.err.NoSuchKeyException;
import net.bodz.bas.meta.codegen.IndexedType;

@IndexedType(includeAbstract = false)
public interface IDataHub {

    String ALIAS_MAIN = "main";
    String ALIAS_TEST = "test";
    String ALIAS_TEMPLATE = "template";

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

    /**
     * @return <code>null</code> if alias isn't defined.
     */
    default String getKey(String alias) {
        return alias;
    }

    /**
     * @throws NoSuchKeyException
     */
    default String requireKey(String alias) {
        return alias;
    }

    default DataContext getMain() {
        String mainKey = requireKey(ALIAS_MAIN);
        return requireDataContext(mainKey);
    }

    default DataContext getTest() {
        String testKey = requireKey(ALIAS_TEST);
        return requireDataContext(testKey);
    }

    default ConnectOptions getTemplate() {
        String templateKey = requireKey(ALIAS_TEMPLATE);
        return requireConnectOptions(templateKey);
    }

}
