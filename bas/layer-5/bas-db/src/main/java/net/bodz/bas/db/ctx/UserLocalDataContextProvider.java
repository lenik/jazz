package net.bodz.bas.db.ctx;

import net.bodz.bas.db.Config;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;

@Priority(Priority.LOW)
public class UserLocalDataContextProvider
        extends LocalDataContextProvider {

    static final Logger logger = LoggerFactory.getLogger(UserLocalDataContextProvider.class);

    public UserLocalDataContextProvider() {
        super(Config.getConfigDir());
    }

}
