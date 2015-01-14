package net.bodz.bas.site.config;

import net.bodz.bas.http.config.AbstractResourceMappings;
import net.bodz.bas.http.config.ServletContextConfig;
import net.bodz.bas.http.config.ServletDescriptor;

public class DesignResourceMappings
        extends AbstractResourceMappings {

    ServletDescriptor imgLink;

    @Override
    public void servlets(ServletContextConfig config) {
        imgLink = localLink("/img", "/mnt/istore/projects/design/img", 100).install(config);
    }

}
