package net.bodz.bas.db.ctx;

import java.io.File;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;

@Priority(Priority.LOW)
public class DefaultDataContextProvider
        extends LocalDataContextProvider {

    static final Logger logger = LoggerFactory.getLogger(DefaultDataContextProvider.class);

    public DefaultDataContextProvider() {
        super(getDefaultFolder());
    }

    public static File getDefaultFolder() {
        File dataDir = SysProps.dataDir;
        File optionsDir = new File(dataDir, "bas-db/connectOptions");
        if (!optionsDir.exists()) {
            logger.info("create directory " + optionsDir);
            optionsDir.mkdirs();
        }
        return optionsDir;
    }

}
