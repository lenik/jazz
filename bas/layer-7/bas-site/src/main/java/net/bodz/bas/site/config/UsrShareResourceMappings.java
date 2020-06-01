package net.bodz.bas.site.config;

import net.bodz.bas.servlet.config.AbstractResourceMappings;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.servlet.config.ServletDescriptor;

public class UsrShareResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    ServletDescriptor backgroundsDir;
    ServletDescriptor fontsDir;
    ServletDescriptor fontsFontAwesomeDir;
    ServletDescriptor iconsDir;
    ServletDescriptor javascriptDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        backgroundsDir = fn.localLink(config, "/backgrounds", "/usr/share/backgrounds", 365).install(config);
        fontsDir = fn.localLink(config, "/libfont", "/usr/share/fonts", 365).install(config);
        fontsFontAwesomeDir = fn.localLink(config, "/libfont/font-awesome", "/usr/share/fonts-font-awesome", 365)
                .install(config);
        iconsDir = fn.localLink(config, "/icons", "/usr/share/icons", 365).install(config);
        javascriptDir = fn.localLink(config, "/libjs-alt", "/usr/share/javascript", 365).install(config);
    }

}
