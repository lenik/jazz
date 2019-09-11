package net.bodz.bas.ui.model.action;

import java.util.Set;
import java.util.TreeSet;

import net.bodz.bas.i18n.dom1.ElementComparator;
import net.bodz.bas.meta.codegen.IndexedType;
import net.bodz.bas.ui.dom1.UiObject;

@IndexedType
public abstract class UiLocationDecl
        extends UiObject {

    private boolean mergeChildren;
    private boolean mergeToParent;

    private UiLocationDecl parent;
    private Set<UiLocationDecl> children;

    public UiLocationDecl() {
        children = new TreeSet<UiLocationDecl>(ElementComparator.LOCALE);
    }

    public abstract LocationType getLocationType();

    public UiLocationDecl getParent() {
        return parent;
    }

    public void setParent(UiLocationDecl parent) {
        this.parent = parent;
    }

    public Set<UiLocationDecl> getChildren() {
        return children;
    }

    public void addChild(UiLocationDecl child) {
        if (child == null)
            throw new NullPointerException("child");
        children.add(child);
    }

    public boolean isMergeToParent() {
        return mergeToParent;
    }

    public void setMergeToParent(boolean mergeToParent) {
        this.mergeToParent = mergeToParent;
    }

    /**
     * Merge children locations into this location.
     * 
     * for each child: move child.children to here, and draw a separater.
     */
    public boolean isMergeChildren() {
        return mergeChildren;
    }

    public void setMergeChildren(boolean mergeChildren) {
        this.mergeChildren = mergeChildren;
    }

}
