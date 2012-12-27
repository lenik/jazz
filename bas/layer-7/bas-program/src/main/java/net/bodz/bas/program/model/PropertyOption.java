package net.bodz.bas.program.model;

import java.beans.PropertyDescriptor;

import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.spi.bean.BeanProperty;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class PropertyOption
        extends TransientOption {

    Class<?> beanClass;
    PropertyDescriptor propertyDescriptor;

    public PropertyOption(PropertyDescriptor propertyDescriptor, MethodDoc getterDoc) {
        super(propertyDescriptor.getName(), //
                propertyDescriptor.getPropertyType(), //
                propertyDescriptor.getReadMethod(), // Only annotations on the getter are used.
                getterDoc);
        this.beanClass = propertyDescriptor.getReadMethod().getDeclaringClass();
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public IProperty property() {
        return new BeanProperty(beanClass, propertyDescriptor);
    }

}
