package net.bodz.bas.doc.node;

public class ListItem
        extends ParWrapper {

    public ListItem(ListPar parent) {
        super(parent);
    }

    @Override
    public NodeType getType() {
        return NodeType.LIST_ITEM;
    }

    @Override
    public boolean isListItem() {
        return true;
    }

    @Override
    public ListPar getParent() {
        return (ListPar) super.getParent();
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        int index = getChildIndex();
        int itemIndex = getItemIndex();
        visitor.listItem(this, index, itemIndex);
    }

}
