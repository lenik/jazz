package net.bodz.bas.db;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.db.ctx.DefaultContextIds;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class Config {

    static final Logger logger = LoggerFactory.getLogger(Config.class);

    static File dataDir = SysProps.dataDir;
    static File configDir = new File(dataDir, "config");

    static final boolean autoMkdirs = true;

    public static File getDataDir() {
        return dataDir;
    }

    public static File getConfigDir() {
        if (autoMkdirs && ! configDir.exists()) {
            logger.info("create directory " + configDir);
            configDir.mkdirs();
        }
        return configDir;
    }

    public static File getConfigDir(String contextId) {
        File dir = new File(configDir, contextId);
        return dir;
    }

    public static List<File> getDefaultConfigDirs() {
        Collection<String> contextIds = DefaultContextIds.resolve();
        List<File> dirs = new ArrayList<>();
        for (String contextId : contextIds) {
            File dir = getConfigDir(contextId);
            dirs.add(dir);
        }
        return dirs;
    }

}
