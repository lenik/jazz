package net.bodz.bas.ant.util;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.tools.ant.types.Path;

import net.bodz.bas.c.java.io.FileURL;
import net.bodz.bas.c.java.net.URLClassLoaders;
import net.bodz.bas.err.CreateException;
import net.bodz.bas.err.IllegalUsageException;
import net.bodz.bas.jvm.stack.Caller;
import net.bodz.bas.t.factory.DecodeXMLFactory;
import net.bodz.bas.t.factory.DecodeXMLFileFactory;
import net.bodz.bas.t.factory.IFactory;
import net.bodz.bas.t.factory.ScriptingInstantiator;

public class ValueConstruct
        extends WithParameters {

    private IFactory<Object> factory;
    private URLClassLoader loader;

    public ValueConstruct() {
        ClassLoader parent = Caller.getCallerClassLoader(0);
        loader = new URLClassLoader(new URL[0], parent);
    }

    void setFactory(IFactory<Object> factory) {
        if (this.factory != null)
            throw new IllegalUsageException("Factory is already specified: " + this.factory);
        this.factory = factory;
    }

    public void setObject(Object obj) {
        setFactory(new IFactory.Static<Object>(obj));
    }

    /**
     * @param className
     *            Must be visible to the classloader of this task class. That means, bodz_bas should
     *            not in the bootstrap classpath,
     */
    public void setClassName(int caller, String className) {
        setFactory(new ScriptingInstantiator(loader, className));
    }

    public void setXml(String xml) { // logger...
        setFactory(new DecodeXMLFactory(xml, null));
    }

    public void setXmlFile(File xmlFile) { // logger
        setFactory(new DecodeXMLFileFactory(xmlFile, null));
    }

    public void addConfiguredClasspath(Path path) {
        String[] paths = path.list();
        URL[] urls = new URL[paths.length];
        for (int i = 0; i < paths.length; i++) {
            File loc = new File(paths[i]);
            URL url = FileURL.toURL(loc, null);
            urls[i] = url;
        }
        URLClassLoaders.addURLs(loader, urls);
    }

    public Object create()
            throws CreateException {
        return create(null, null);
    }

    public Object create(Class<?>[] prependTypes, Object[] prependValues)
            throws CreateException {
        if (factory == null)
            throw new IllegalUsageException("Don\'t know how to get bean instance");
        Class<?>[] types = prependTypes(prependTypes).toArray(new Class[0]);
        Object[] values = prependValues(prependValues).toArray(new Object[0]);
        return factory._create(types, values);
    }

}
