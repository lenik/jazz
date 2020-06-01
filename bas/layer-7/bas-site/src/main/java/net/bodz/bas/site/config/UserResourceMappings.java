package net.bodz.bas.site.config;

import java.io.File;

import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.IBasicSiteAnchors;

public class UserResourceMappings
        extends AbstractResourceMappings
        implements IBasicSiteAnchors {

    public static final IAnchor filesAnchor = _webApp_.join("files/");

    ServletContextConfig config;

    File homeDir = SysProps.userHome;
    File filesDir = new File(homeDir, "files");

    ServletDescriptor homeLink;
    ServletDescriptor filesLink;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;

        if (new File(homeDir, "_public_").exists())
            homeLink = fn.localLink(config, "/.home", homeDir.getPath(), 30);

        // if ((filesDir.isDirectory())) {
        // // if (!filesDir.mkdirs())
        // // throw new LazyLoadException("Failed to mkdirs: " + filesDir);
        // filesLink = fn.localLink(config,"/~files", filesDir.getPath(), 30);
        // }

    }

}
