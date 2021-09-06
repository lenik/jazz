package net.bodz.bas.fmt.json.obj;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;

import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.AbstractJsonDumper;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.ReflectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.decl.Stop;

import com.googlecode.openbeans.BeanInfo;
import com.googlecode.openbeans.IntrospectionException;
import com.googlecode.openbeans.Introspector;
import com.googlecode.openbeans.PropertyDescriptor;

public class BeanJsonDumper
        extends AbstractJsonDumper<BeanJsonDumper> {

    static final Logger logger = LoggerFactory.getLogger(BeanJsonDumper.class);

    public BeanJsonDumper(IJsonOut out) {
        super(out);
    }

    @Override
    protected boolean dumpGenericObject(boolean boxed, Class<?> type, Object obj, int depth)
            throws IOException, FormatException {
        if (boxed)
            out.object();
        try {
            return dumpMembers(type, obj, depth);
        } finally {
            if (boxed)
                out.endObject();
        }
    }

    protected boolean dumpMembers(Class<?> type, Object obj, int depth)
            throws IOException, FormatException {

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            logger.errorf(e, "Failed to get bean info of %s: %s.", type, e.getMessage());
            out.key("error");
            formatException(true, depth + 1, e);
            return true;
        }

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method getter = propertyDescriptor.getReadMethod();
            if (getter == null)
                continue;

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            Class<?> declaringClass = getter.getDeclaringClass();
            if (ReflectOptions.stopClasses.contains(declaringClass))
                continue;

            // TODO value-property
            Class<?> propertyType = propertyDescriptor.getPropertyType();
            if (propertyType.isAnnotationPresent(Stop.class))
                continue;

            String propertyName = propertyDescriptor.getName();
            String path = markset.path(propertyName);
            if (!isIncluded(path))
                continue;

            Object propertyValue;
            try {
                propertyValue = getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                logger.error(e, "Failed to invoke getter: " + e.getMessage());
                out.key(propertyName);
                formatException(true, depth + 1, e);
                continue;
            }

            if (propertyValue == null) {
                if (includeNull) {
                    out.key(propertyName);
                    out.value(null);
                }
            }

            else {
                out.key(propertyName);
                _dumpOnce(true, propertyValue, depth + 1, propertyName);
            }
        }
        return true;
    }

    public static String toString(Object obj) {
        StringWriter buf = new StringWriter();
        JsonWriter out = new JsonWriter(buf);
        try {
            new BeanJsonDumper(out).dumpBoxed(obj);
        } catch (IOException e) {
            throw new UnexpectedException(e.getMessage(), e);
        } catch (FormatException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return buf.toString();
    }

}
