package net.bodz.bas.c.bean;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import net.bodz.bas.bean.api.IBeanInfo;
import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.bean.api.IntrospectionException;
import net.bodz.bas.bean.api.Introspectors;
import net.bodz.bas.io.IPrintOut;
import net.bodz.bas.io.Stdio;
import net.bodz.bas.t.iterator.Iterables;

public class BeanDump {

    public static void dumpProperties(Object bean)
            throws IntrospectionException {
        dumpProperties(bean, Stdio.cout);
    }

    public static void dumpProperties(Object bean, IPrintOut out)
            throws IntrospectionException {
        if (bean == null)
            throw new NullPointerException("bean");
        Class<?> beanClass = bean.getClass();
        IBeanInfo beanInfo = Introspectors.getBeanInfo(beanClass);
        IPropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        Arrays.sort(properties, new Comparator<IPropertyDescriptor>() {
            @Override
            public int compare(IPropertyDescriptor o1, IPropertyDescriptor o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (IPropertyDescriptor property : properties) {
            Method readf = property.getReadMethod();
            String name = property.getName();
            Object value;
            try {
                value = readf.invoke(bean);
            } catch (ReflectiveOperationException e) {
                value = e.toString();
            }
            out.println(name + ": " + value);
            // out.println(" attributes: ");
            for (String attr : Iterables.otp(property.attributeNames())) {
                Object attrValue = property.getValue(attr);
                out.println("    ATTR " + attr + ": " + attrValue);
            }
        }
        out.flush();
    }

}
