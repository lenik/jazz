package net.bodz.bas.doc.node;

import java.util.List;

import net.bodz.bas.doc.node.util.FullDocVisitor;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.word.DocNum;

public class ListPar
        extends AbstractParGroup {

    boolean multiLevel;

    IListStyle style;
    boolean ordered;
    int startNumber;

    int itemCount;

    public DocNum _docNum;

    public ListPar(INode parent) {
        this(parent, false);
    }

    public ListPar(INode parent, boolean ordered) {
        super(parent);
        this.style = ordered ? ListStyles.decimal : ListStyles.disc;
        this.ordered = ordered;
    }

    @Override
    public NodeType getType() {
        return NodeType.LIST;
    }

    public boolean isSimpleList() {
        return !multiLevel;
    }

    public boolean isMultiLevel() {
        return multiLevel;
    }

    public void setMultiLevel(boolean multiLevel) {
        this.multiLevel = multiLevel;
    }

    public ListPar getRootLevel() {
        if (multiLevel) {
            ListItem parentItem = closest(ListItem.class);
            if (parentItem != null) {
                ListPar parentList = parentItem.getParent();
                if (parentList.isMultiLevel())
                    return parentList.getRootLevel();
            }
        }
        return this;
    }

    /**
     * Get multi-level depth. root-level have level 0.
     *
     * for single-level lists, returns 0.
     */
    public int getLevel() {
        if (multiLevel) {
            ListItem parentItem = closest(ListItem.class);
            if (parentItem != null) {
                ListPar parentList = parentItem.getParent();
                if (parentList.isMultiLevel())
                    return parentList.getLevel() + 1;
            }
        }
        return 0;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public IListStyle getListStyle() {
        return style;
    }

    public void setListStyle(IListStyle style) {
        if (style == null)
            throw new NullPointerException("style");
        this.style = style;
    }

    public List<IListStyle> getStyleVector() {
        accept(new FullDocVisitor() {
            @Override
            public void list(ListPar list) {
                list.getListStyle();
            }

            @Override
            public void listItem(IPar par, int index, int itemIndex) {
                super.listItem(par, index, itemIndex);
            }
        });
        return null;
    }

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getItemCount() {
        int count = 0;
        for (IPar par : pars)
            if (par.isListItem())
                count++;
        return count;
    }

    @Override
    public boolean canReduce() {
        for (IPar par : pars)
            if (!par.canReduce())
                return false;
        return true;
    }

    @Override
    protected void nodeAccept(IDocVisitor visitor) {
        visitor.list(this);
    }

    @Override
    public void internalAccept(IDocVisitor visitor) {
        int index = 0;
        int itemIndex = 0;
        for (IPar par : pars) {
            boolean item = par.isListItem();
            visitor.listItem(par, index, item ? itemIndex : -1);
            index++;
            if (item)
                itemIndex++;
        }
    }

    public ListItem addItem() {
        synchronized (pars) {
            itemCount++;
            return pars.append(new ListItem(this));
        }
    }

    @Override
    public String toString() {
        StringBuilder a = new StringBuilder();
        int i = 1;
        synchronized (pars) {
            for (IPar par : pars) {
                a.append(style.format(i));
                if (style.isOrdered())
                    a.append(".");
                a.append(' ');
                par.buildText(a);
                a.append("\n");
            }
        }
        return a.toString();
    }

}
