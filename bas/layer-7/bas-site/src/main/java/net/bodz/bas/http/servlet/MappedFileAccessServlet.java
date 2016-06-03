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
    protected void setParameter(String name, String value)
            throws ServletException {
        super.setParameter(name, value);
        switch (name) {
        case ATTRIBUTE_MAPPING_CLASS:
            try {
                Class<?> mappingClass = Class.forName(value);
                mapping = (IFilePathMapping) mappingClass.newInstance();
            } catch (ReflectiveOperationException e) {
                throw new ServletException("Can't create mapping: " + e.getMessage(), e);
            }
            break;
        }
    }

    @Override
    public void init()
            throws ServletException {
        super.init();
        if (mapping == null)
            throw new IllegalConfigException(ATTRIBUTE_MAPPING_CLASS + " isn't set.");
    }

    @Override
    protected File getLocalRoot(HttpServletRequest req) {
        return mapping.getLocalRoot(req);
    }

}
