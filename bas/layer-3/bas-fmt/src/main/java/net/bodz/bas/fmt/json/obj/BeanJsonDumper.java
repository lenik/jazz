package net.bodz.bas.fmt.json.obj;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.Transient;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.json.JSONWriter;

import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.AbstractJsonDumper;

public class BeanJsonDumper
        extends AbstractJsonDumper {

    public BeanJsonDumper(JSONWriter out) {
        super(out);
    }

    @Override
    protected void formatObject(Class<?> type, Object obj)
            throws ReflectiveOperationException, IOException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            throw new ReflectiveOperationException(e.getMessage(), e);
        }

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method getter = propertyDescriptor.getReadMethod();
            if (getter == null)
                continue;

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            String name = propertyDescriptor.getName();
            Object value = getter.invoke(obj);

            if (value == null) {
                out.key(name);
                out.value(null);
            } else if (marks.push(value)) {
                out.key(name);
                _formatRaw(value);
                marks.pop();
            }
        }
    }

    public static String toString(Object obj) {
        StringWriter buf = new StringWriter();
        JSONWriter out = new JSONWriter(buf);
        try {
            new BeanJsonDumper(out).dump(obj);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        }
        return buf.toString();
    }

}
