package net.bodz.bas.http.servlet;

import java.io.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import net.bodz.bas.err.IllegalConfigException;
import net.bodz.bas.site.file.IFilePathMapping;

public class MappedFileAccessServlet
        extends AbstractFileAccessServlet {

    private static final long serialVersionUID = 1L;

    public static final String ATTRIBUTE_MAPPING_CLASS = "mapping-class";
    public static final String ATTRIBUTE_START_PATH = "start-path";
    public static final String ATTRIBUTE_MAX_AGE = "max-age";

    private IFilePathMapping mapping;

    @Override
    protected void setInitParameter(String name, String value)
            throws ServletException {
        super.setInitParameter(name, value);
        switch (name) {
        case ATTRIBUTE_MAPPING_CLASS:
            try {
// ClassLoader cl = getClass().getClassLoader();
// URLClassLoaders.dump(cl,Stdio.cout);
// Class<?> mappingClass = Class.forName(value, true, ClassLoaders.getRuntimeClassLoader());
                Class<?> mappingClass = Class.forName(value);
                mapping = (IFilePathMapping) mappingClass.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new ServletException("Can't create mapping: " + e.getMessage(), e);
            }
            break;
        }
    }

    @Override
    public void _init()
            throws ServletException {
        if (mapping == null)
            throw new IllegalConfigException(ATTRIBUTE_MAPPING_CLASS + " isn't set.");
    }

    @Override
    protected File getLocalRoot(HttpServletRequest req) {
        return mapping.getLocalRoot(req);
    }

}
