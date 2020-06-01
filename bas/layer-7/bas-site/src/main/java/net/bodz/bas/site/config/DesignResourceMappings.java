package net.bodz.bas.site.config;

import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;

public class DesignResourceMappings
        extends AbstractResourceMappings {

    ServletDescriptor imgLink;

    @Override
    public void servlets(ServletContextConfig config) {
        imgLink = fn.localLink(config, "/d-img", "/mnt/istore/projects/design/img", 100).install(config);
    }

}
