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
import net.bodz.bas.fmt.json.ReflectOptions;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;

public class BeanJsonDumper
        extends AbstractJsonDumper<BeanJsonDumper> {

    static final Logger logger = LoggerFactory.getLogger(BeanJsonDumper.class);

    public BeanJsonDumper(JSONWriter out) {
        super(out);
    }

    @Override
    protected void formatObjectMembers(Class<?> type, Object obj, int depth, String prefix)
            throws IOException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
        } catch (IntrospectionException e) {
            logger.errorf(e, "Failed to get bean info of %s: %s.", type, e.getMessage());
            out.key("error");
            formatException(e);
            return;
        }

        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            Method getter = propertyDescriptor.getReadMethod();
            if (getter == null)
                continue;

            if (getter.isAnnotationPresent(Transient.class))
                continue;

            if (ReflectOptions.stopClasses.contains(getter.getDeclaringClass()))
                continue;

            String propertyName = propertyDescriptor.getName();
            String qname = prefix == null ? null : //
                    (prefix.isEmpty() ? propertyName : (prefix + "." + propertyName));
            if (!isIncluded(qname))
                continue;

            Object propertyValue;
            try {
                propertyValue = getter.invoke(obj);
            } catch (ReflectiveOperationException e) {
                logger.error(e, "Failed to invoke getter: " + e.getMessage());
                out.key(propertyName);
                formatException(e);
                continue;
            }

            if (propertyValue == null) {
                if (includeNull) {
                    out.key(propertyName);
                    out.value(null);
                }
            }

            else if (marks.push(propertyValue)) {
                out.key(propertyName);
                __formatRaw_nonnull(propertyValue, depth + 1, qname);
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
