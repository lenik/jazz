package net.bodz.bas.typer.std;

import net.bodz.bas.rtx.IAttributes;
import net.bodz.bas.typer.Typers;

public interface ITypedAttributes
        extends
            IAttributes {

    IAttributeDescriptor getAttributeDescriptor(String name);

    default String getAttributeLabel(String name) {
        IAttributeDescriptor descriptor = getAttributeDescriptor(name);
        return descriptor == null ? null : descriptor.getLabel();
    }

    default String getAttributeDescription(String name) {
        IAttributeDescriptor descriptor = getAttributeDescriptor(name);
        return descriptor == null ? null : descriptor.getDescription();
    }

    default String getAttributeIcon(String name) {
        IAttributeDescriptor descriptor = getAttributeDescriptor(name);
        return descriptor == null ? null : descriptor.getIcon();
    }

    default Class<?> getAttributeType(String name) {
        IAttributeDescriptor descriptor = getAttributeDescriptor(name);
        return descriptor == null ? null : descriptor.getType();
    }

    default <T> T getAttributeTyper(String name, Class<T> typerClass) {
        IAttributeDescriptor descriptor = getAttributeDescriptor(name);
        if (descriptor != null) {
            Class<?> type = descriptor.getType();
            return Typers.getTyper(type, typerClass);
        }
        return null;
    }

}
