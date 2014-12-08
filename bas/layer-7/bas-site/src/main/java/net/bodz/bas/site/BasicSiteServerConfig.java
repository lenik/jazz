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

        backgroundsDir = addServlet(FileAccessorServlet.class, "/backgrounds/*");
        backgroundsDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "/usr/share/backgrounds");

        fontsDir = addServlet(FileAccessorServlet.class, "/fonts/*");
        fontsDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "/usr/share/fonts");

        fontsFontAwesomeDir = addServlet(FileAccessorServlet.class, "/fonts/font-awesome/*");
        fontsFontAwesomeDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "/usr/share/fonts-font-awesome");

        iconsDir = addServlet(FileAccessorServlet.class, "/icons/*");
        iconsDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "/usr/share/icons");

        javascriptDir = addServlet(FileAccessorServlet.class, "/js/*");
        javascriptDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "/usr/share/javascript");

        webjarsDir = addServlet(ClassResourceAccessorServlet.class, "/webjars/*");
        webjarsDir.setInitParam(FileAccessorServlet.ATTRIBUTE_PATH, //
                "META-INF/resources/webjars");

    }

}
