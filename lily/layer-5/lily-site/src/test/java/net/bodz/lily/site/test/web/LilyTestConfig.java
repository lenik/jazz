package net.bodz.lily.site.test.web;

import net.bodz.bas.html.servlet.PathDispatchServlet;
import net.bodz.bas.i18n.LocaleVars;
import net.bodz.bas.servlet.config.ServletDescriptor;
import net.bodz.bas.servlet.ctx.RequestScopeTeller;
import net.bodz.uni.echo._default.DefaultServerConfig;

public class LilyTestConfig
        extends DefaultServerConfig {

    protected ServletDescriptor dispatching;

    public LilyTestConfig() {
        configEnv();
        configServlets();
    }

    protected void configEnv() {
        LocaleVars localeCtl = LocaleVars.LOCALE;
        localeCtl.setTeller(new RequestScopeTeller());
    }

    protected void configServlets() {
        dispatching = addServlet(PathDispatchServlet.class, "/*");
        dispatching.setInitParam(PathDispatchServlet.ROOT_CLASS, LilyTestSite.class.getName());
    }

}
