package net.bodz.bas.doc.node;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.list.AutoLists;
import net.bodz.bas.t.list.IListEx;

public abstract class AbstractDocNode
        implements
            IDocNode {

    final IDocNode parent;
    final Map<String, Object> attributes = new HashMap<>();

    String styleClass;
    String color;
    String background;
    // String border;

    public AbstractDocNode(IDocNode parent) {
        this.parent = parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        return (T) attributes.get(name);
    }

    @Override
    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    @Override
    public IDocNode getParent() {
        return parent;
    }

    @Override
    public IListEx<? extends IDocNode> getChildren() {
        return AutoLists.emptyAutoList();
    }

    @Override
    public final void accept(IDocVisitor visitor) {
        visitor.beginNode(this);

        for (String key : attributes.keySet()) {
            Object val = attributes.get(key);
            visitor.attribute(key, val);
        }

        nodeAccept(visitor);

        visitor.endNode(this);
    }

    protected void nodeAccept(IDocVisitor visitor) {
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        dump(buf);
        return buf.toString();
    }

}
