package net.bodz.bas.cli.model;

import java.beans.PropertyDescriptor;

public class PropertyOption
        extends AbstractOption {

    PropertyDescriptor property;

    public PropertyOption(PropertyDescriptor property) {
        super(property.getReadMethod().getDeclaringClass(), //
                property.getName(), //
                property.getReadMethod(), // Only annotations on the getter method are used.
                property.getPropertyType());
        this.property = property;
    }

}
