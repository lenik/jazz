package net.bodz.bas.program.model;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.potato.element.IProperty;
import net.bodz.bas.potato.element.IType;
import net.bodz.bas.potato.provider.bean.BeanProperty;
import net.bodz.mda.xjdoc.model.MethodDoc;

public class PropertyOption
        extends AbstractOption {

    IType type;
    BeanProperty property;
    IPropertyDescriptor propertyDescriptor;

    public PropertyOption(BeanProperty property, MethodDoc doc) {
        super("property:" + property.getName(), //
                property.getName(), //
                findPropertyGenericType(property), doc);

        propertyDescriptor = property.getPropertyDescriptor();

        type = property.getDeclaringType();
    }

    static Type findPropertyGenericType(BeanProperty property) {
        IPropertyDescriptor propertyDescriptor = property.getPropertyDescriptor();
        Method getter = propertyDescriptor.getReadMethod();
        if (getter != null)
            return getter.getGenericReturnType();

        Method setter = propertyDescriptor.getWriteMethod();
        if (setter == null)
            throw new UnexpectedException("Neither getter or setter is defined for property " + propertyDescriptor);

        Type[] paramTypes = setter.getGenericParameterTypes();
        return paramTypes[0];
    }

    @Override
    public IProperty property() {
        return property;
    }

}
