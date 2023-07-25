package net.bodz.bas.potato.element;

import java.lang.reflect.Type;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.t.event.IPropertyChangeListener;

public interface IProperty
        extends
            IPotatoElement,
            IPropertyAccessor {

    /**
     * The property type.
     *
     * @return Type of the property, never <code>null</code>.
     */
    @Override
    Class<?> getPropertyClass();

    Type getPropertyGenericType();

    boolean isReadable();

    boolean isWritable();

    /**
     * A bound property can cause property change event.
     *
     * @return <code>true</code> If this is a bound property.
     * @see IPropertyDescriptor#isBound()
     */
    boolean isPropertyChangeSource();

    void addPropertyChangeListener(Object instance, IPropertyChangeListener listener);

    void addPropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener);

    void removePropertyChangeListener(Object instance, IPropertyChangeListener listener);

    void removePropertyChangeListener(Object instance, String propertyName, IPropertyChangeListener listener);

}
