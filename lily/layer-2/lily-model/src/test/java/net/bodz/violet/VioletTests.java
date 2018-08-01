package net.bodz.violet;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.db.jdbc.ConnectOptions;
import net.bodz.lily.test.TestContext;

public class VioletTests
        extends TestContext {

    static final ConnectOptions LOCAL;
    static {
        LOCAL = new ConnectOptions();
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
