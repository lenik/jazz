package net.bodz.bas.ui.model.action;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom1.ElementComparator;
import net.bodz.bas.io.ITreeOut;

public class UiLocationNode {

    private UiLocationNode parent;
    private Set<UiLocationNode> children;

    private UiLocationDecl decl;
    private Set<IAction> actions;

    public UiLocationNode(UiLocationDecl decl) {
        this(decl, null);
    }

    public UiLocationNode(UiLocationDecl decl, UiLocationNode parent) {
        this.decl = decl;
        this.actions = new TreeSet<>(ElementComparator.LOCALE);

        this.parent = parent;
        this.children = new TreeSet<>(UiLocationNodeComparator.INSTANCE);
        if (parent != null)
            parent.addChild(this);
    }

    public UiLocationNode getParent() {
        return parent;
    }

    public Set<UiLocationNode> getChildren() {
        return children;
    }

    public void addChild(UiLocationNode child) {
        children.add(child);
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }

    public UiLocationDecl getDecl() {
        return decl;
    }

    public Set<IAction> getActions() {
        return actions;
    }

    public void addAction(IAction action) {
        actions.add(action);
    }

    public void dump(ITreeOut out) {
        out.println(decl);
        out.enter();
        for (IAction action : actions)
            out.println("Action: " + action);
        for (UiLocationNode child : children)
            child.dump(out);
        out.leave();
    }
}
