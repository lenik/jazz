package net.bodz.violet.server;

import java.util.Locale;

import net.bodz.bas.httpd.BasHttpd;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.xml.dom.XmlFormatter;

public class VioletServer
        extends BasHttpd {

    public static final int PORT = 8502;

    @Override
    protected void initSystemGlobals()
            throws Exception {
        XmlFormatter.NULL_VERBATIM = "<i class='fa fa-times zu-null'></i>";
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
    }

    @Override
    protected ServletContextConfig createConfig() {
        return new VioletConfig();
    }

    @Override
    protected void configure(ServletContextConfig config) {
        config.setPortNumber(PORT);
    }

    public static void main(String[] args)
            throws Exception {
        new VioletServer().execute(args);
    }

}
