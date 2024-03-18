package net.bodz.bas.fmt.json.obj;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.c.org.json.JsonWriter;
import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.fmt.json.AbstractJsonDumper;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.PropertyOrder;
import net.bodz.bas.fmt.json.ReflectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.bean.Internal;
import net.bodz.bas.meta.bean.Transient;
import net.bodz.bas.meta.decl.Stop;

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
//        } catch (Exception e) {
//            out.entry("FAILED", e);
        } finally {
            if (boxed)
                out.endObject();
        }
    }

    protected boolean dumpMembers(Class<?> type, Object obj, int depth)
            throws IOException, FormatException {
        if (obj == null)
            throw new NullPointerException("obj");

        IBeanInfo beanInfo;
        try {
            beanInfo = Introspectors.getBeanInfo(type);
        } catch (NoClassDefFoundError e) {
            // logger.errorf(e, "Failed to get bean info of %s: %s.", type, e.getMessage());
            out.key("error");
            formatException(true, depth + 1, e);
            return true;
        } catch (IntrospectionException e) {
            // logger.errorf(e, "Failed to get bean info of %s: %s.", type, e.getMessage());
            out.key("error");
            formatException(true, depth + 1, e);
            return true;
        }

        List<IPropertyDescriptor> properties = selectMembers(beanInfo);

        for (IPropertyDescriptor property : properties) {
            Method getter = property.getReadMethod();

            String propertyName = property.getName();
            Object propertyValue;
            try {
                propertyValue = getter.invoke(obj);
            } catch (NoClassDefFoundError e) {
                // logger.error(e, "Failed to invoke getter: " + e.getMessage());
                out.key(propertyName);
                formatException(true, depth + 1, e);
                continue;
            } catch (ReflectiveOperationException e) {
                // logger.error(e, "Failed to invoke getter: " + e.getMessage());
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

    public List<IPropertyDescriptor> selectMembers(IBeanInfo beanInfo) {
        List<IPropertyDescriptor> props = new ArrayList<>();

        for (IPropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method getter = propertyDescriptor.getReadMethod();
            if (getter == null)
                continue;

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            if (getter.isAnnotationPresent(Internal.class))
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
            if (! isIncluded(path))
                continue;

            props.add(propertyDescriptor);
        }

        props.sort(PropertyOrder.INSTANCE);
        return props;
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
