package net.bodz.bas.db.ctx;

import java.io.File;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.Priority;

public class DefaultLocalDataHub
        extends LocalDataHub {

    static final Logger logger = LoggerFactory.getLogger(DefaultLocalDataHub.class);

    public DefaultLocalDataHub() {
        super(getDefaultFolder());
    }

    @Override
    public int getPriority() {
        return Priority.LOW;
    }

    public static File getDefaultFolder() {
        StackTraceElement ste=null;
        ste.getClassName();

        File dataDir = SysProps.dataDir;
        File optionsDir = new File(dataDir, "bas-db/connectOptions");
        if (!optionsDir.exists()) {
            logger.info("create directory " + optionsDir);
            optionsDir.mkdirs();
        }
        return optionsDir;
    }

}
