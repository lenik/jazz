package net.bodz.bas.cli.model;

import java.beans.PropertyDescriptor;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.spi.bean.BeanProperty;

public class PropertyOption
        extends AbstractOption {

    Class<?> beanClass;
    PropertyDescriptor propertyDescriptor;

    public PropertyOption(PropertyDescriptor propertyDescriptor) {
        super(propertyDescriptor.getReadMethod().getDeclaringClass(), //
                propertyDescriptor.getName(), //
                propertyDescriptor.getReadMethod(), // Only annotations on the getter are used.
                propertyDescriptor.getPropertyType());
        this.beanClass = propertyDescriptor.getReadMethod().getDeclaringClass();
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public IProperty property() {
        return new BeanProperty(beanClass, propertyDescriptor);
    }

}
