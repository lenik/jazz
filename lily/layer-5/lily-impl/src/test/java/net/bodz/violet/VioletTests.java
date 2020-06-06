package net.bodz.violet;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.ctx.DataContexts;
import net.bodz.bas.db.jdbc.ConnectOptions;

public class VioletTests
        extends DataContexts {

    static final ConnectOptions LOCAL = declare("local test");
    static {
        LOCAL.setServer("localhost:5432");
        LOCAL.setDatabase("violet");
        LOCAL.setUserName("postgres");
        LOCAL.setPassword("cW3EADp8");
    }

    static DataContext defaultContext = new DataContext(LOCAL);

    public static DataContext getDefaultContext() {
        return defaultContext;
    }

}
