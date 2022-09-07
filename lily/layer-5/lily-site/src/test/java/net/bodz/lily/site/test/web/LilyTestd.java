package net.bodz.lily.site.test.web;

import java.util.Locale;

import net.bodz.bas.httpd.BasHttpd;
import net.bodz.bas.servlet.config.ServletContextConfig;
import net.bodz.bas.site.vhost.IVirtualHostResolver;
import net.bodz.bas.site.vhost.PostgresBackedVhostResolver;
import net.bodz.bas.site.vhost.VirtualHostManager;
import net.bodz.bas.xml.dom.XmlFormatter;

public class LilyTestd
        extends BasHttpd {

    public static final int PORT = 8080;

    static IVirtualHostResolver vhostResolver;

    public LilyTestd() {
    }

    @Override
    protected void initSystemGlobals()
            throws Exception {
        XmlFormatter.NULL_VERBATIM = "<i class='fa fa-times zu-null'></i>";
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
    }

    @Override
    protected void initVirtualHosts(VirtualHostManager manager)
            throws Exception {
        vhostResolver = PostgresBackedVhostResolver.getProjectDefault();
        manager.add(vhostResolver);
    }

    @Override
    protected ServletContextConfig createConfig() {
        return new LilyTestConfig();
    }

    @Override
    protected void configure(ServletContextConfig config) {
        config.setPortNumber(PORT);
    }

    public static void main(String[] args)
            throws Exception {
        new LilyTestd().execute(args);
    }

}
