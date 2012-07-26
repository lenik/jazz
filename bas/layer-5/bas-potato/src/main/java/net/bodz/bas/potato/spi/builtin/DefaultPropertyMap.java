package net.bodz.bas.potato.spi.builtin;

import java.beans.BeanInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import net.bodz.bas.potato.spi.bean.BeanProperty;
import net.bodz.bas.potato.spi.reflect.ReflectProperty;
import net.bodz.bas.potato.traits.IProperty;
import net.bodz.bas.potato.traits.IPropertyMap;
import net.bodz.bas.util.order.ComparableComparator;

public class DefaultPropertyMap
        implements IPropertyMap {

    Map<String, IProperty> map;

    public DefaultPropertyMap() {
        map = new TreeMap<String, IProperty>(ComparableComparator.getInstance());
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection<IProperty> getProperties() {
        return map.values();
    }

    @Override
    public IProperty getProperty(String propertyName) {
        return map.get(propertyName);
    }

    public DefaultPropertyMap addClassFields(Class<?> clazz) {
        addFields(clazz.getFields());
        return this;
    }

    public DefaultPropertyMap addFields(Field... fields) {
        for (Field field : fields) {
            String name = field.getName();
            ReflectProperty reflectProperty = new ReflectProperty(field);
            map.put(name, reflectProperty);
        }
        return this;
    }

    public DefaultPropertyMap addBeanProperties(BeanInfo beanInfo) {
        Class<?> beanClass = beanInfo.getBeanDescriptor().getBeanClass();
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String name = propertyDescriptor.getName();
            BeanProperty beanProperty = new BeanProperty(beanClass, propertyDescriptor);
            map.put(name, beanProperty);
        }
        return this;
    }

}
