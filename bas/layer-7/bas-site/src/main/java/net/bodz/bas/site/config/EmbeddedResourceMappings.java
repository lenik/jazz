package net.bodz.bas.site.config;

import net.bodz.bas.http.config.AbstractResourceMappings;
import net.bodz.bas.http.config.ServletContextConfig;
import net.bodz.bas.http.config.ServletDescriptor;

public class EmbeddedResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    ServletDescriptor webjarsDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        webjarsDir = fn.resourceLink(config, "/webjars", "META-INF/resources/webjars", 365);
    }

}
