package net.bodz.bas.program.model;

import net.bodz.bas.t.order.IPriority;

public interface IAppLifecycleListener<App>
        extends
            IPriority {

    @Override
    default int getPriority() {
        return 0;
    }

    /**
     * @throws IllegalArgumentException
     * @throws IllegalStateException
     */
    default void initDefaults(App app) {
    }

    default void setup(App app)
            throws Exception {
    }

    default void shutdown(App app)
            throws Exception {
    }

}
