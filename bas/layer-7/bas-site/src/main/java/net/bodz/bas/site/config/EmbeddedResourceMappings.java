package net.bodz.bas.site.config;

import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.site.IBasicSiteAnchors;

/**
 * @see IBasicSiteAnchors#_webjars_
 */
public class EmbeddedResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    public ServletDescriptor webjarsDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        webjarsDir = fn.resAlias(config, "/webjars", "META-INF/resources/webjars", 365);
    }

}
