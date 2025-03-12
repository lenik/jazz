package net.bodz.bas.db.ctx;

import java.io.IOException;

import net.bodz.bas.db.Config;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;

@Priority(Priority.LOW)
public class UserLocalDataContextProvider
        extends LocalDataContextProvider {

    static final Logger logger = LoggerFactory.getLogger(UserLocalDataContextProvider.class);

    public UserLocalDataContextProvider()
            throws IOException {
        super(Config.getOrCreateConfigDir());
    }

}
