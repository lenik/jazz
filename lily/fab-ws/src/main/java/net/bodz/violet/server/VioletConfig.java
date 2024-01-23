package net.bodz.violet.server;

import net.bodz.bas.html.servlet.PathDispatchServlet;
import net.bodz.bas.i18n.LocaleVars;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.ctx.RequestScopeTeller;
import net.bodz.uni.echo._default.DefaultServerConfig;

public class VioletConfig
        extends DefaultServerConfig {

    protected ServletDescriptor dispatching;

    public VioletConfig() {
        configEnv();
        configServlets();
    }

    protected void configEnv() {
        LocaleVars localeCtl = LocaleVars.LOCALE;
        localeCtl.setTeller(new RequestScopeTeller());
    }

    protected void configServlets() {
        dispatching = addServlet(PathDispatchServlet.class, "/*");
        dispatching.setIndex(0);
        dispatching.setInitParam(PathDispatchServlet.ROOT_CLASS, VioletResolver.class.getName());
    }

}
