package net.bodz.bas.servlet.config;

public abstract class AbstractServletContextConfigurer
        implements IServletContextConfigurer {

    @Override
    public int getPriority() {
        return 0;
    }

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
