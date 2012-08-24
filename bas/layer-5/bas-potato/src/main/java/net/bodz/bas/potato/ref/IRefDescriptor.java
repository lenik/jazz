package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.traits.IElement;
import net.bodz.bas.potato.traits.IProperty;
import net.bodz.bas.potato.traits.IType;

public interface IRefDescriptor
        extends IElement {

    IProperty getDeclaringProperty();

    /**
     * The value type.
     * 
     * @return Type of the value, never <code>null</code>.
     */
    Class<?> getValueType();

    IType getPotatoType();

    boolean isReadable();

    boolean isWritable();

    boolean isValueChangeSource();

}
