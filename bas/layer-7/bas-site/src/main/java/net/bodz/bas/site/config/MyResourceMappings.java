package net.bodz.bas.site.config;

import net.bodz.bas.http.config.AbstractResourceMappings;
import net.bodz.bas.http.config.ServletContextConfig;
import net.bodz.bas.http.config.ServletDescriptor;

public class MyResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    ServletDescriptor chunkDir;
    ServletDescriptor publicDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        chunkDir = localLink("/chunk", "/home/lenik/chunk", 365).install(config);
        publicDir = localLink("/public", "/home/lenik/public", 1).install(config);
    }

}
