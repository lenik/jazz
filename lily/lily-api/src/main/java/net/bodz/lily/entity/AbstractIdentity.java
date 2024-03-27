package net.bodz.lily.entity;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.potato.element.IProperty;

public abstract class AbstractIdentity
        implements
            IIdentity {

    @Override
    public void parse(String... columns)
            throws ParseException {
        IIdentityTypeInfo type = getType();
        String[] propertyNames = type.getPropertyNames();
        if (propertyNames.length != columns.length)
            throw new IllegalArgumentException("mismatched column count.");
        for (int i = 0; i < columns.length; i++) {
            String name = propertyNames[i];
            IProperty property = type.getProperty(name);
            Class<?> propertyClass = property.getPropertyClass();
            try {
                Object val = StrVar.parseAny(propertyClass, columns[i]);
                property.setValue(this, val);
            } catch (ParseException e) {
                throw new ParseException("error parse property " + name + ": " + e.getMessage(), e);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    protected static DefaultIdentityTypeInfoBuilder typeBuilder() {
        return new DefaultIdentityTypeInfoBuilder();
    }

    protected static DefaultIdentityTypeInfoBuilder typeBuilder(Class<? extends IIdentity> idClass) {
        return typeBuilder().idClass(idClass);
    }

}
