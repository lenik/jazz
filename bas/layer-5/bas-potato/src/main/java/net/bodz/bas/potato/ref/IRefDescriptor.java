package net.bodz.bas.potato.ref;

import net.bodz.bas.potato.model.IPotatoElement;
import net.bodz.bas.potato.model.IProperty;
import net.bodz.bas.potato.model.IType;

public interface IRefDescriptor
        extends IPotatoElement {

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
