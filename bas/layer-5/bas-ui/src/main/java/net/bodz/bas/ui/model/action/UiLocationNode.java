package net.bodz.bas.ui.model.action;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom1.ElementComparator;

public class UiLocationNode {

    private UiLocationNode parent;
    private List<UiLocationNode> items;

    private UiLocationDecl decl;
    private Set<IAction> actions;

    public UiLocationNode(UiLocationDecl decl) {
        this(decl, null);
    }

    public UiLocationNode(UiLocationDecl decl, UiLocationNode parent) {
        this.decl = decl;
        this.actions = new TreeSet<>(ElementComparator.LOCALE);
    }

    public UiLocationNode getParent() {
        return parent;
    }

    public List<UiLocationNode> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
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

}
