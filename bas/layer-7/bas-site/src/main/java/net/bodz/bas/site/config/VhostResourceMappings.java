package net.bodz.bas.site.config;

import java.io.File;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;

public class VhostResourceMappings
        extends AbstractResourceMappings
        implements IBasicSiteAnchors {

    public static final IAnchor filesAnchor = _webApp_.join("files/");

    ServletContextConfig config;

    ServletDescriptor homeLink;
    ServletDescriptor sitesVLink;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;

        File homeDir = SysProps.userHome;
        File sitesDir = new File(homeDir, "sites");

        if ((sitesDir.isDirectory())) {
            // if (!filesDir.mkdirs())
            // throw new LazyLoadException("Failed to mkdirs: " + filesDir);
            sitesVLink = fn.localVLink(config, "/files", sitesDir.getPath(), 30);
        }
    }

}
