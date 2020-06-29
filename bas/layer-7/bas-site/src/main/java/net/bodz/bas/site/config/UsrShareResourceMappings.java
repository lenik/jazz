package net.bodz.bas.site.config;

import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;

public class UsrShareResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    public ServletDescriptor backgroundsDir;
    public ServletDescriptor fontsDir;
    public ServletDescriptor fontsFontAwesomeDir;
    public ServletDescriptor iconsDir;
    public ServletDescriptor javascriptDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        backgroundsDir = fn.dirAlias(config, "/backgrounds", "/usr/share/backgrounds", 365).install(config);
        fontsDir = fn.dirAlias(config, "/libfont", "/usr/share/fonts", 365).install(config);
        fontsFontAwesomeDir = fn.dirAlias(config, "/libfont/font-awesome", "/usr/share/fonts-font-awesome", 365)
                .install(config);
        iconsDir = fn.dirAlias(config, "/icons", "/usr/share/icons", 365).install(config);
        javascriptDir = fn.dirAlias(config, "/libjs-alt", "/usr/share/javascript", 365).install(config);
    }

}
