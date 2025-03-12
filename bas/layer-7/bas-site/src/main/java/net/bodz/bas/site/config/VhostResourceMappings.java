package net.bodz.bas.site.config;

import java.nio.file.Path;

import net.bodz.bas.c.java.nio.file.FileFn;
import net.bodz.bas.c.system.SysProps;
import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.ctx.IAnchor;
import net.bodz.bas.site.DefaultSiteDirs;
import net.bodz.bas.site.IBasicSiteAnchors;

public class VhostResourceMappings
        extends AbstractResourceMappings
        implements
            IBasicSiteAnchors {

    /**
     * @see DefaultSiteDirs#getDataAnchor(String)
     */
    public static final IAnchor filesAnchor = _webApp_.join("files/");

    ServletContextConfig config;

    public ServletDescriptor siteHomeAlias;
    public ServletDescriptor siteFilesAlias;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;

        Path homeDir = SysProps.userHome;
        Path sitesDir = homeDir.resolve("sites");

        if (FileFn.isDirectory(sitesDir)) {
            // if (!filesDir.mkdirs())
            // throw new LazyLoadException("Failed to mkdirs: " + filesDir);
            // siteHomeAlias = fn.vdirAlias(config, "", sitesDir.getPath(), 30);
            siteFilesAlias = fn.vdirAlias(config, "/files", sitesDir.toString(), 30);
        }
    }

}
