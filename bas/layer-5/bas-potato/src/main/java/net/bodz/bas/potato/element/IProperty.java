package net.bodz.bas.potato.element;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.bodz.bas.bean.api.IPropertyDescriptor;
import net.bodz.bas.t.event.IPropertyChangeListener;

public interface IProperty
        extends IPotatoElement,
                IPropertyAccessor {

    default String getQName() {
        return getDeclaringType().getName() + "::" + getName();
    }

    IProperty getSuperProperty();

    default Iterable<IProperty> ancestorsToRoot(boolean includeThis) {
        return () -> new AncestorsToRootIterator(IProperty.this, includeThis);
    }

    default Iterable<IProperty> ancestorsFromRoot(boolean includeThis) {
        List<IProperty> list = new ArrayList<>();
        for (IProperty p : ancestorsToRoot(includeThis))
            list.add(p);
        Collections.reverse(list);
        return list;
    }

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
