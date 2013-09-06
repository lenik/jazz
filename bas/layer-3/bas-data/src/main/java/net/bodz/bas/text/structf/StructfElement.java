package net.bodz.bas.text.structf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StructfElement
        implements IStructfElement, Serializable, IStructfHandler {

    private static final long serialVersionUID = 1L;

    private String name;
    private String[] args;

    private List<IStructfAttribute> attributes;
    private List<IStructfElement> children;

    // private transient Map<String, IStructfAttribute> attributeMap;

    /**
     * Create a new structf-element.
     * 
     * @param name
     *            Non-<code>null</code> element name.
     * @param args
     *            Non-<code>null</code> element arguments.
     */
    public StructfElement(String name, String... args) {
        this.name = name;
        this.args = args;
        attributes = new ArrayList<IStructfAttribute>();
        children = new ArrayList<IStructfElement>();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public String[] getArguments() {
        return args;
    }

    public void setArguments(String[] args) {
        if (args == null)
            throw new NullPointerException("args");
        this.args = args;
    }

    @Override
    public List<IStructfAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public List<IStructfElement> getChildren() {
        return children;
    }

    /** ⇱ Implementation Of {@link IStructfHandler}. */
    ;

    @Override
    public boolean attribute(String name, String data) {
        StructfAttribute attribute = new StructfAttribute(name, data);
        attributes.add(attribute);
        return true;
    }

    @Override
    public IStructfHandler beginChild(String name, String[] args)
            throws StructfHandlerException {
        return new StructfElement(name, args);
    }

    @Override
    public boolean endChild(IStructfElement element) {
        children.add(element);
        return true;
    }

    @Override
    public void element(IStructfElement element)
            throws StructfHandlerException {
    }

}
