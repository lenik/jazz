package net.bodz.bas.ant;

import java.io.File;

import net.bodz.bas.lang.err.CreateException;
import net.bodz.bas.lang.err.IllegalUsageException;
import net.bodz.bas.types.util.Empty;
import net.bodz.bas.util.Factory;

public class ValueConstruct extends WithParameters {

    private Factory<Object> factory;

    void setFactory(Factory<Object> factory) {
        if (this.factory != null)
            throw new IllegalUsageException("Factory is already specified: "
                    + this.factory);
        this.factory = factory;
    }

    public void setObject(Object obj) {
        setFactory(new Factory.Static<Object>(obj));
    }

    /**
     * @param className
     *            Must be visible to the classloader of this task class. That
     *            means, bodz_bas should not in the bootstrap classpath,
     */
    public void setClassName(String className) {
        setFactory(new Factory.ByClassName(0, className));
    }

    public void setXml(String xml) { // logger...
        setFactory(new Factory.ByXML(xml, null));
    }

    public void setXmlFile(File xmlFile) { // logger
        setFactory(new Factory.ByXMLFile(xmlFile, null));
    }

    public Object create() throws CreateException {
        return create(null, null);
    }

    public Object create(Class<?>[] prependTypes, Object[] prependValues)
            throws CreateException {
        if (factory == null)
            throw new IllegalUsageException(
                    "Don't know how to get bean instance");
        Class<?>[] types = prependTypes(prependTypes).toArray(Empty.Classes);
        Object[] values = prependValues(prependValues).toArray(Empty.Objects);
        return factory._create(types, values);
    }

}
