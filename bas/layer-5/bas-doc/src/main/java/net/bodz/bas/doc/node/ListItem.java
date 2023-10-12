package net.bodz.bas.doc.node;

import java.util.List;

import net.bodz.bas.t.stack.NodePredicates;

public class ListItem
        extends AbstractParGroup {

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

    public ListPar getList() {
        return getParent();
    }

    public int getListLevel() {
        return getList().getLevel();
    }

    public String getMLNumber() {
        List<ListItem> chain = ancestors(NodePredicates.LIST_ITEM);
        int n = chain.size();
        StringBuilder sb = new StringBuilder(n * 4);
        int count = 0;
        for (int i = n - 1; i >= 0; i--) { // in reversed order, so level-1 comes at first.
            ListItem item = chain.get(i);
            int ord = item.getItemIndex() + 1;
            if (count != 0)
                sb.append(".");
            sb.append(ord);
            count++;
        }
        if (n == 1)
            sb.append(".");
        sb.append(" ");
        return sb.toString();
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        int index = getChildIndex();
        int itemIndex = getItemIndex();
        visitor.listItem(this, index, itemIndex);
    }

}
