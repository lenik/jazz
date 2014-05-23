package net.bodz.bas.xml.dom;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractXmlNode<self_t extends IXmlNode>
        implements IXmlNode {

    private IXmlTag parent;

    public AbstractXmlNode(IXmlTag parent) {
        if (parent != null) {
            parent.insert(this);
            this.parent = parent;
        }
    }

    @Override
    public final IXmlTag getParent() {
        return parent;
    }

    @SuppressWarnings("unchecked")
    @Override
    public self_t detach() {
        if (parent != null) {
            parent.remove(this);
            parent = null;
        }
        return (self_t) this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public self_t attach(IXmlTag newParent) {
        if (newParent == null)
            throw new NullPointerException("newParent");
        if (newParent != parent) {
            detach();
            newParent.insert(this);
            parent = newParent;
        }
        return (self_t) this;
    }

    @Override
    public int getPosition() {
        IXmlTag parent = getParent();
        if (parent == null)
            return -1;
        int pos = 0;
        for (IXmlNode child : parent.getChildren()) {
            if (child == this)
                return pos;
            else
                pos++;
        }
        return -1;
    }

    @Override
    public Collection<? extends IXmlNode> getChildren() {
        return Collections.emptyList();
    }

}
