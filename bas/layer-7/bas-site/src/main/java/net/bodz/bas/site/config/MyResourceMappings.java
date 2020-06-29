package net.bodz.bas.site.config;

import java.io.File;

import net.bodz.bas.c.system.SystemProperties;
import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;

public class MyResourceMappings
        extends AbstractResourceMappings {

    public ServletContextConfig config;
    public ServletDescriptor chunkDir;
    public ServletDescriptor publicDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;

        String userHome = SystemProperties.getUserHome();
        File home = new File(userHome);

        File chunk = new File(home, ".chunk");
        if (!chunk.exists())
            chunk = new File(home, "chunk");
        chunkDir = fn.dirAlias(config, "/chunk", chunk.getPath(), 365).install(config);

        File public_ = new File(home, "public");
        publicDir = fn.dirAlias(config, "/public", public_.getPath(), 1).install(config);
    }

}
