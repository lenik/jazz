package net.bodz.bas.typer.std;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.repr.form.SortOrder;
import net.bodz.bas.rtx.MutableAttributes;

public class MutableTypedAttributes
        extends MutableAttributes
        implements
            ITypedAttributes {

    Map<String, AttributeDescriptor> attributeDescriptors = new HashMap<>();

    public MutableTypedAttributes() {
        super();
    }

    public MutableTypedAttributes(Map<String, Object> attributeMap) {
        super(attributeMap);
    }

    public MutableTypedAttributes(SortOrder order) {
        super(order);
    }

    @Override
    public IAttributeDescriptor getAttributeDescriptor(String name) {
        return attributeDescriptors.get(name);
    }

    public synchronized AttributeDescriptor makeAttributeDescriptor(String name) {
        AttributeDescriptor descriptor = attributeDescriptors.get(name);
        if (descriptor == null) {
            descriptor = new AttributeDescriptor();
            attributeDescriptors.putIfAbsent(name, descriptor);
        }
        return descriptor;
    }

    public void setAttributeDescriptor(String name, AttributeDescriptor descriptor) {
        if (descriptor == null)
            throw new NullPointerException("descriptor");
        attributeDescriptors.put(name, descriptor);
    }

    @Override
    public void removeAttribute(String name) {
        super.removeAttribute(name);
        attributeDescriptors.remove(name);
    }

    public void setAttributeLabel(String name, String label) {
        if (name == null)
            throw new NullPointerException("name");
        if (label == null)
            throw new NullPointerException("label");
        makeAttributeDescriptor(name).label = label;
    }

    public void setAttributeDescription(String name, String description) {
        if (name == null)
            throw new NullPointerException("name");
        if (description == null)
            throw new NullPointerException("description");
        makeAttributeDescriptor(name).description = description;
    }

    public void setAttributeIcon(String name, String icon) {
        if (name == null)
            throw new NullPointerException("name");
        if (icon == null)
            throw new NullPointerException("icon");
        makeAttributeDescriptor(name).icon = icon;
    }

    public void setAttributeType(String name, Class<?> type) {
        if (name == null)
            throw new NullPointerException("name");
        if (type == null)
            throw new NullPointerException("type");
        makeAttributeDescriptor(name).type = type;
    }

    @Override
    public <T> T getAttributeTyper(String name, Class<T> typerClass) {
        if (attributeDescriptors != null) {
            AttributeDescriptor descriptor = attributeDescriptors.get(name);
            if (descriptor != null) {
                T typer = descriptor.getTyper(typerClass);
                if (typer != null)
                    return typer;
            }
        }
        return ITypedAttributes.super.getAttributeTyper(name, typerClass);
    }

    public <T> void addAttributeTyper(String name, T typer) {
        if (typer == null)
            throw new NullPointerException("typer");

        @SuppressWarnings("unchecked")
        Class<T> typerClass = (Class<T>) typer.getClass();

        addAttributeTyper(name, typer, typerClass);
    }

    public <T> void addAttributeTyper(String name, T typer, Class<T> typerClass) {
        if (name == null)
            throw new NullPointerException("name");
        if (typer == null)
            throw new NullPointerException("typer");
        if (typerClass == null)
            throw new NullPointerException("typerClass");
        makeAttributeDescriptor(name).setTyper(typerClass, typer);
    }

}