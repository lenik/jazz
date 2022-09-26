package net.bodz.bas.db.ctx;

import java.util.Collection;

import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.t.order.IPriority;

@IndexedType
public interface IDefaultContextIdsResolver
        extends
            IPriority {

    int PRIORITY_VIRTUALHOST_NAME = 100;
    int PRIORITY_SERVER_NAME = 200;
    int PRIORITY_SERVER_IP = 300;
    int PRIORITY_PROJECT = 400;

    /**
     * Level is 0-based.
     */
    default int getMaxLevel() {
        return 0;
    }

    /**
     * @return <code>null</code> if information not available.
     */
    Collection<String> resolveContextIds(int level);

}
