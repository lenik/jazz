package net.bodz.bas.servlet.persist;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

import net.bodz.bas.c.jakarta.servlet.http.AbstractServletContextListener;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class ServletContextRegistry
        extends AbstractServletContextListener {

    static final Logger logger = LoggerFactory.getLogger(ServletContextRegistry.class);

    public static final String PARAM_PATH = "registry.path";
    public static final String PARAM_VCSCMD = "registry.vcscmd";

    RegistryScheme scheme;
    AppPersistor persistor;

    static {
        // Not used. Please set the init-param.
        // AppRegistry.useDefaultRootDir();
    }

    public ServletContextRegistry() {
    }

    public ServletContextRegistry(Path homeDir) {
        init(homeDir);
    }

    void init(Path homeDir) {
        scheme = new RegistryScheme(homeDir);
        persistor = new AppPersistor(scheme);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        if (persistor == null) {
            String paramPath = servletContext.getInitParameter(PARAM_PATH);
            if (paramPath == null)
                throw new IllegalArgumentException(String.format(//
                        "Init-param %s isn't set.", PARAM_PATH));
            // String contextName = toContextName(servletContext.getContextPath());
            // scheme = RegistryScheme.getInstance(contextName);
            Path homeDir = Paths.get(paramPath);
            init(homeDir);
        }

        try {
            persistor.loadServletContext(servletContext);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        try {
            persistor.save(servletContext);
        } catch (Exception e) {
            logger.error(e, "Failed to save servlet context: " + e.getMessage());
        }
    }

    static String toContextName(String contextPath) {
        while (contextPath.startsWith("/"))
            contextPath = contextPath.substring(1);
        if (contextPath.isEmpty())
            return "__default__";
        contextPath = contextPath.replace("/", "__");
        return contextPath;
    }

}
