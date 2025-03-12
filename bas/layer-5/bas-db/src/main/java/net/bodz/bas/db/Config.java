package net.bodz.bas.db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.db.ctx.DefaultContextIds;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class Config {

    static final Logger logger = LoggerFactory.getLogger(Config.class);

    static Path dataDir = SysProps.dataDir;
    static Path configDir = dataDir.resolve("config");

    static final boolean autoMkdirs = true;

    public static Path getDataDir() {
        return dataDir;
    }

    public static Path getConfigDir() {
        return configDir;
    }

    public static Path getOrCreateConfigDir()
            throws IOException {
        if (autoMkdirs && !Files.exists(configDir)) {
            logger.info("create directory " + configDir);
            Files.createDirectories(configDir);
        }
        return configDir;
    }

    public static Path getConfigDir(String contextId) {
        Path dir = configDir.resolve(contextId);
        return dir;
    }

    public static List<Path> getDefaultConfigDirs() {
        Collection<String> contextIds = DefaultContextIds.resolve();
        List<Path> dirs = new ArrayList<>();
        for (String contextId : contextIds) {
            Path dir = getConfigDir(contextId);
            dirs.add(dir);
        }
        return dirs;
    }

}
