package net.bodz.bas.db.ctx;

import net.bodz.bas.db.jdbc.ConnectOptions;

public class JdbcDataContexts
        extends AbstractDataContexts<ConnectOptions> {

    @Override
    protected ConnectOptions getConnectOptions(ConnectOptions key) {
        return key;
    }

    private static JdbcDataContexts instance = new JdbcDataContexts();

    public static JdbcDataContexts getInstance() {
        return instance;
    }

}
