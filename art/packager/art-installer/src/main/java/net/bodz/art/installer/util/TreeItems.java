package net.bodz.art.installer.util;

import java.util.Collection;

import net.bodz.bas.err.OutOfDomainException;
import net.bodz.bas.err.UnexpectedException;

import org.eclipse.swt.widgets.TreeItem;

public class TreeItems {

    public static final int UNKNOWN = -1;
    public static final int NONE = 0;
    public static final int PARTIAL = 1;
    public static final int FULL = 2;

    public static int stateAdd(int a, int b) {
        assert b != UNKNOWN : "b must be known";
        switch (a) {
        case UNKNOWN:
            return b;
        case NONE:
            return b == NONE ? NONE : PARTIAL;
        case PARTIAL:
            return PARTIAL;
        case FULL:
            return b == FULL ? FULL : PARTIAL;
        }
        throw new UnexpectedException();
    }

    public static int getState(TreeItem item) {
        boolean checked = item.getChecked();
        boolean grayed = item.getGrayed();
        if (checked)
            return grayed ? PARTIAL : FULL;
        return grayed ? UNKNOWN : NONE;
    }

    public static void setState(TreeItem item, int state) {
        switch (state) {
        case UNKNOWN:
            item.setChecked(false);
            item.setGrayed(false);
            break;
        case NONE:
            item.setChecked(false);
            item.setGrayed(false);
            break;
        case PARTIAL:
            item.setChecked(true);
            item.setGrayed(true);
            break;
        case FULL:
            item.setChecked(true);
            item.setGrayed(false);
            break;
        default:
            throw new OutOfDomainException("state", state);
        }
    }

    public static void setState(TreeItem item, int state, boolean chained) {
        if (state != FULL && state != NONE)
            throw new OutOfDomainException("state", state, "FULL/NONE");
        // System.out.printf("setState(%s, %d, %s)\n", item, state, chained);
        if (!chained)
            setState(item, state);
        setChildrenState(item, state); // this affects the item also.
        updateParentState(item);
    }

    public static void updateParentState(TreeItem item) {
        if (item == null)
            throw new NullPointerException("item");
        TreeItem parent = item.getParentItem();
        if (parent == null)
            return;
        int parentState = UNKNOWN;
        TreeItem[] siblings = parent.getItems();
        for (TreeItem sibling : siblings) {
            int siblingState = getState(sibling);
            parentState = stateAdd(parentState, siblingState);
        }
        setState(parent, parentState);
        updateParentState(parent);
    }

    /**
     * @param item
     *            parent item and its children whose state will be set.
     */
    public static void setChildrenState(TreeItem item, int state) {
        // System.out.printf("setChildrenState(%s, %d)\n", item, state);
        setState(item, state);
        for (TreeItem child : item.getItems())
            setChildrenState(child, state);
    }

    public static void listChildren(TreeItem item, Collection<TreeItem> list) {
        list.add(item);
        for (TreeItem child : item.getItems())
            listChildren(child, list);
    }

}
