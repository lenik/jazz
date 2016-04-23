package net.bodz.bas.ui.model.action;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.io.ITreeOut;
import net.bodz.bas.ui.dom1.IUiElement;

public class UiActionNode {

    private UiActionNode parent;
    private Set<UiActionNode> children;

    private IUiElement element;
    private Object context;

    // private int type;

    public UiActionNode(IUiElement element) {
        this(null, element);
    }

    public UiActionNode(UiActionNode parent, IUiElement element) {
        this.parent = parent;
        this.children = new TreeSet<>(UiActionNodeComparator.INSTANCE);
        if (parent != null)
            parent.addChild(this);

        this.element = element;
    }

    public boolean isRoot() {
        return parent == null;
    }

    /**
     * @return 0 for root node.
     */
    public int getDepth() {
        UiActionNode node = this;
        int depth = 0;
        while ((node = node.parent) != null)
            depth++;
        return depth;
    }

    public UiActionNode getParent() {
        return parent;
    }

    public Set<UiActionNode> getChildren() {
        return children;
    }

    public void addChild(UiActionNode child) {
        children.add(child);
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public IUiElement getElement() {
        return element;
    }

    public Object getContext() {
        return context;
    }

    public void dump(ITreeOut out) {
        Class<?> c = element.getClass();
        while (!c.getSimpleName().startsWith("Ui"))
            c = c.getSuperclass();
        out.println(c.getSimpleName() + " " + element);

        if (!isEmpty()) {
            out.enter();
            for (UiActionNode child : children)
                child.dump(out);
            out.leave();
        }
    }
}
