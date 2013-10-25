package net.bodz.bas.t.tree;

import org.eclipse.swt.widgets.TreeItem;

public class QuadStateTreeItems
        implements IQuadState {

    public static int getState(TreeItem item, int grayState) {
        if (item.getChecked())
            return item.getGrayed() ? grayState : ONE;
        else
            return 0;
    }

    public static void setState(TreeItem item, int state) {
        item.setChecked(state != ZERO);
        item.setGrayed(state != ZERO && state != ONE);
    }

    /**
     * Set state of the tree item and its descendants.
     * 
     * @param item
     *            The start item.
     */
    public static void setStateRec(TreeItem item, int state, int startDepth) {
        if (startDepth == 0)
            setState(item, state);
        else
            startDepth--;

        for (TreeItem child : item.getItems())
            setStateRec(child, state, startDepth);
    }

    public static void updateAncestorStates(TreeItem item) {
        if (item == null)
            throw new NullPointerException("item");

        TreeItem[] children = item.getItems();
        if (children.length != 0) {
            int state = Q0;
            for (TreeItem child : children)
                state |= getState(child, Q1);

            int oldState = getState(item, Q1);
            if (oldState != state)
                setState(item, state);
        }

        TreeItem parentItem = item.getParentItem();
        if (parentItem != null)
            updateAncestorStates(parentItem);
    }

}
