package net.bodz.bas.text.rst;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RstElement
        implements IRstElement, IElementHandler, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String[] args;

    private List<IRstAttribute> attributes;
    private List<IRstElement> children;

    // private transient Map<String, IStructfAttribute> attributeMap;

    /**
     * Create a new structf-element.
     * 
     * @param name
     *            Non-<code>null</code> element name.
     * @param args
     *            Non-<code>null</code> element arguments.
     */
    public RstElement(String name, String... args) {
        this.name = name;
        this.args = args;
        attributes = new ArrayList<IRstAttribute>();
        children = new ArrayList<IRstElement>();
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
    public List<IRstAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public List<IRstElement> getChildren() {
        return children;
    }

    /** ⇱ Implementation Of {@link IElementHandler}. */
    /* _____________________________ */static section.iface __ELEMENT_HANDLER__;

    @Override
    public boolean attribute(String name, String data) {
        RstAttribute attribute = new RstAttribute(name, data);
        attributes.add(attribute);
        return true;
    }

    @Override
    public IElementHandler beginChild(String name, String[] args) {
        return new RstElement(name, args);
    }

    @Override
    public boolean endChild(IRstElement element) {
        children.add(element);
        return true;
    }

    @Override
    public void complete(IRstElement element) {
    }

}
