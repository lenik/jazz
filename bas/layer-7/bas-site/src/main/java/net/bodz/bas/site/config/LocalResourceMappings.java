package net.bodz.bas.site.config;

import net.bodz.bas.http.config.AbstractResourceMappings;
import net.bodz.bas.http.config.ServletContextConfig;
import net.bodz.bas.http.config.ServletDescriptor;

public class LocalResourceMappings
        extends AbstractResourceMappings {

    ServletContextConfig config;
    ServletDescriptor backgroundsDir;
    ServletDescriptor fontsDir;
    ServletDescriptor fontsFontAwesomeDir;
    ServletDescriptor iconsDir;
    ServletDescriptor javascriptDir;
    ServletDescriptor webjarsDir;

    @Override
    public void servlets(ServletContextConfig config) {
        this.config = config;
        backgroundsDir = localLink("/backgrounds", "/usr/share/backgrounds", 365).install(config);
        fontsDir = localLink("/fonts", "/usr/share/fonts", 365).install(config);
        fontsFontAwesomeDir = localLink("/fonts/font-awesome", "/usr/share/fonts-font-awesome", 365).install(config);
        iconsDir = localLink("/icons", "/usr/share/icons", 365).install(config);
        javascriptDir = localLink("/js", "/usr/share/javascript", 365).install(config);
        webjarsDir = resourceLink("/webjars", "META-INF/resources/webjars", 365);
    }

}
