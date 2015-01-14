package net.bodz.bas.http.config;

public abstract class AbstractServletContextConfigurer
        implements IServletContextConfigurer {

    @Override
    public void filters(ServletContextConfig config) {
    }

    @Override
    public void servlets(ServletContextConfig config) {
    }

    @Override
    public void others(ServletContextConfig config) {
    }

}
