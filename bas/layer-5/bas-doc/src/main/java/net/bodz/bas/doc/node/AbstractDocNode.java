package net.bodz.bas.doc.node;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.doc.property.Color;
import net.bodz.bas.io.BCharOut;
import net.bodz.bas.t.list.AutoLists;
import net.bodz.bas.t.list.IListEx;

public abstract class AbstractDocNode
        implements
            INode {

    final INode parent;
    final Map<String, Object> attributes = new HashMap<>();

    String styleClass;
    Color color;
    Color background;
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

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    @Override
    public String toString() {
        BCharOut buf = new BCharOut();
        dump(buf);
        return buf.toString();
    }

}
