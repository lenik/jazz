package net.bodz.bas.http.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

public class ServletDescriptor
        extends AbstractPluginDescriptor {

    Class<? extends Servlet> servletClass;
    List<String> mappings = new ArrayList<String>();
    Map<String, String> initParameterMap = new HashMap<String, String>();

    public ServletDescriptor(Class<? extends Servlet> servletClass) {
        this(null, servletClass);
    }

    public ServletDescriptor(String id, Class<? extends Servlet> servletClass) {
        super(id);
        if (servletClass == null)
            throw new NullPointerException("servletClass");
        this.servletClass = servletClass;
    }

    /**
     * Get the initialize order. Holders with order<0, are initialized on use. Those with order>=0
     * are initialized in increasing order when the handler is started.
     */
    @Override
    public int getPriority() {
        return super.getPriority();
    }

    /**
     * Set the initialize order. Holders with order<0, are initialized on use. Those with order>=0
     * are initialized in increasing order when the handler is started.
     */
    @Override
    public void setPriority(int priority) {
        super.setPriority(priority);
    }

    public Class<? extends Servlet> getServletClass() {
        return servletClass;
    }

    public List<String> getMappings() {
        return mappings;
    }

    public void addMapping(String mapping) {
        if (mapping == null)
            throw new NullPointerException("mapping");
        mappings.add(mapping);
    }

    public Map<String, String> getInitParamMap() {
        return initParameterMap;
    }

    public void setInitParam(String key, Object value) {
        if (key == null)
            throw new NullPointerException("key");
        if (value == null)
            throw new NullPointerException("value");
        initParameterMap.put(key, value.toString());
    }

    public void removeInitParam(String key) {
        initParameterMap.remove(key);
    }

    public ServletDescriptor install(ServletContextConfig config) {
        config.addServlet(this);
        return this;
    }

    @Override
    public String toString() {
        return servletClass.getName();
    }

}
