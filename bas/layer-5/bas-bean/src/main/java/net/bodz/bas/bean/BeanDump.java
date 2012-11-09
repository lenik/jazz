package net.bodz.bas.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import net.bodz.bas.c.java.util.Iterables;
import net.bodz.bas.sio.IPrintOut;
import net.bodz.bas.sio.Stdio;

public class BeanDump {

    public static void dumpProperties(Object bean)
            throws IntrospectionException {
        dumpProperties(bean, Stdio.cout);
    }

    public static void dumpProperties(Object bean, IPrintOut out)
            throws IntrospectionException {
        if (bean == null)
            throw new NullPointerException("bean");
        Class<? extends Object> beanClass = bean.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(beanClass);
        PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
        Arrays.sort(properties, new Comparator<PropertyDescriptor>() {
            @Override
            public int compare(PropertyDescriptor o1, PropertyDescriptor o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for (PropertyDescriptor property : properties) {
            Method readf = property.getReadMethod();
            String name = property.getName();
            Object value;
            try {
                value = readf.invoke(bean);
            } catch (ReflectiveOperationException e) {
                value = e.toString();
            }
            out.println(name + ": " + value);
            // out.println("    attributes: ");
            for (String attr : Iterables.otp(property.attributeNames())) {
                Object attrValue = property.getValue(attr);
                out.println("    ATTR " + attr + ": " + attrValue);
            }
        }
        out.flush();
    }

}
