package net.bodz.bas.doc.node;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.list.AutoLists;
import net.bodz.bas.t.list.IListEx;

public abstract class AbstractDocNode
        implements
            INode {

    final INode parent;
    final Map<String, Object> attributes = new HashMap<>();

    String styleClass;
    String color;
    String background;
    // String border;

    public AbstractDocNode(INode parent) {
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
    public INode getParent() {
        return parent;
    }

    @Override
    public IListEx<? extends INode> getChildren() {
        return AutoLists.emptyAutoList();
    }

    @Override
    public final void accept(IDocVisitor visitor) {
        visitor.beginNode(this);

        for (String key : attributes.keySet()) {
            Object val = attributes.get(key);
            visitor.attribute(key, val);
        }

        nodeProperties(visitor);
        nodeAccept(visitor);

        visitor.endNode(this);
    }

    protected void nodeProperties(IDocVisitor visitor) {
    }

    protected void nodeAccept(IDocVisitor visitor) {
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        dump(buf);
        return buf.toString();
    }

}
