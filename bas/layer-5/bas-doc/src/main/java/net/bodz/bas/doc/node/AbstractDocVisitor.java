package net.bodz.bas.doc.node;

public class AbstractDocVisitor
        implements
            IDocVisitor {

    public static final int UNLIMIT = -1;

    int maxDepth;
    int depth;

    public AbstractDocVisitor() {
        this(UNLIMIT);
    }

    public AbstractDocVisitor(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    protected IDocVisitor getNextVisitor() {
        return this;
    }

    protected boolean enter() {
        if (maxDepth != UNLIMIT && depth >= maxDepth)
            return false;
        depth++;
        return true;
    }

    protected void leave() {
        depth--;
    }

    @Override
    public void havePars(IHavePars havePars) {
        for (IPar par : havePars.getPars())
            par.accept(getNextVisitor());
    }

    @Override
    public void haveRuns(IHaveRuns haveRuns) {
        for (IRun run : haveRuns.getRuns())
            run.accept(getNextVisitor());
    }

    @Override
    public void document(Document doc) {
        if (enter()) {
            havePars(doc);
            leave();
        }
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        if (enter()) {
            for (Part part : partGroup.parts)
                part.accept(getNextVisitor());
            leave();
        }
    }

    @Override
    public void part(Part part, int index) {
        if (enter()) {
            havePars(part);
            leave();
        }
    }

    @Override
    public void list(ListPar list) {
        if (enter()) {
            int index = 0;
            int itemIndex = 0;
            for (IPar par : list.pars) {
                boolean item = par.isListItem();
                if (item)
                    listItem((ListItem) par, index, itemIndex);
                else
                    par.accept(getNextVisitor());
                index++;
                if (item)
                    itemIndex++;
            }
            leave();
        }
    }

    @Override
    public void listItem(ListItem item, int childIndex, int itemIndex) {
        if (enter()) {
            havePars(item);
            leave();
        }
    }

    @Override
    public void table(Table table) {
        if (enter()) {
            for (TableRow row : table.rows)
                row.accept(getNextVisitor());
            leave();
        }
    }

    @Override
    public void tableRow(TableRow row, int index) {
        if (enter()) {
            for (TableCell cell : row.cells)
                cell.accept(getNextVisitor());
            leave();
        }
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        if (enter()) {
            havePars(cell);
            leave();
        }
    }

    @Override
    public void textBox(TextBox textBox) {
        if (enter()) {
            havePars(textBox);
            leave();
        }
    }

    @Override
    public void textPar(TextPar textPar) {
        if (enter()) {
            haveRuns(textPar);
            leave();
        }
    }

    @Override
    public void textRun(TextRun textRun) {
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        if (enter()) {
            haveRuns(runGroup);
            leave();
        }
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        if (enter()) {
            haveRuns(fontEnv);
            leave();
        }
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        if (enter()) {
            haveRuns(fontStyleEnv);
            leave();
        }
    }

    @Override
    public void hr(Hr hr) {
    }

    @Override
    public void image(Image image) {
    }

}
