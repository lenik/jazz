package net.bodz.bas.site;

import net.bodz.bas.http.servlet.ClassResourceAccessorServlet;
import net.bodz.bas.http.servlet.FileAccessorServlet;
import net.bodz.uni.echo._default.DefaultServerConfig;
import net.bodz.uni.echo.config.ServletDescriptor;

public class BasicSiteServerConfig
        extends DefaultServerConfig {

    ServletDescriptor backgroundsDir;
    ServletDescriptor fontsDir;
    ServletDescriptor fontsFontAwesomeDir;
    ServletDescriptor iconsDir;
    ServletDescriptor javascriptDir;
    ServletDescriptor webjarsDir;

    public BasicSiteServerConfig() {
        backgroundsDir = addLocalLink("/backgrounds", "/usr/share/backgrounds", 365);
        fontsDir = addLocalLink("/fonts", "/usr/share/fonts", 365);
        fontsFontAwesomeDir = addLocalLink("/fonts/font-awesome", "/usr/share/fonts-font-awesome", 365);
        iconsDir = addLocalLink("/icons", "/usr/share/icons", 365);
        javascriptDir = addLocalLink("/js", "/usr/share/javascript", 365);
        webjarsDir = addResourceLink("/webjars", "META-INF/resources/webjars", 365);
    }

    protected ServletDescriptor addLocalLink(String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = addServlet(FileAccessorServlet.class, mapping);
        servlet.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(FileAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

    protected ServletDescriptor addResourceLink(String linkName, String target, int maxAgeDays) {
        String mapping = linkName + "/*";
        int maxAge = maxAgeDays * 86400;
        ServletDescriptor servlet = addServlet(ClassResourceAccessorServlet.class, mapping);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_PATH, target);
        servlet.setInitParam(ClassResourceAccessorServlet.ATTRIBUTE_MAX_AGE, maxAge);
        return servlet;
    }

}
