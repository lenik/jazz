package net.bodz.bas.doc.node.conv;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.io.ITreeOut;

public class MarkdownConverter
        implements
            IDocVisitor {

    ITreeOut out;

    public MarkdownConverter(ITreeOut out) {
        this.out = out;
    }

    @Override
    public void document(Document doc) {
        String title = doc.title.getText();
        if (!Nullables.isEmpty(title)) {
            out.println("# " + title);
            out.println();
        }
        havePars(doc);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        for (Part part : partGroup.parts)
            part.accept(this);
    }

    @Override
    public void part(Part part, int index) {
        // reserved # for title.
        String sym = Strings.repeat(part.getLevel().level + 1, '#');
        String title = part.title.getText();
        out.println(sym + " " + title);
        out.println();
        havePars(part);
    }

    @Override
    public void list(ListPar list) {
        havePars(list);
        out.println();
    }

    @Override
    public void listItem(ListItem item, int index, int itemIndex) {
        ListPar list = item.getParent();
        int startNumber = list.getStartNumber();
        int itemCount = list.getItemCount();

        IListStyle listStyle = list.getListStyle();
        String text = listStyle.format(itemIndex + startNumber, itemCount);
        out.print(text);

        if (list.isOrdered())
            out.print('.');
        out.print(' ');
    }

    @Override
    public void table(Table table) {
        for (TableRow row : table.rows)
            row.accept(this);
    }

    @Override
    public void tableRow(TableRow row, int rowIndex) {
        out.print("| ");

        for (TableCell cell : row.cells)
            cell.accept(this);

        out.println();

        if (rowIndex == 0) {
            Table table = row.getParent();
            TableRow topRow = table.rows.get(0);
            int ccMax = table.getMaxColumnCount();

            StringBuilder alignPatterns = new StringBuilder(ccMax * 10);
            alignPatterns.append("| ");
            int ccTop = topRow.cells.size();
            for (int i = 0; i < ccMax; i++) {
                HorizAlignment alignment = HorizAlignment.LEFT;
                if (i < ccTop) {
                    TableCell cell = topRow.cells.get(i);
                    alignment = cell.getAlignment();
                }
                alignPatterns.append(MarkdownFn.alignPattern(alignment));
                alignPatterns.append(" | ");
            }
            out.println(alignPatterns); // md table requires.
        }
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        out.print(" | ");
        cell.accept(cellBuilder);
    }

    @Override
    public void textBox(TextBox textBox) {
        havePars(textBox);
    }

    @Override
    public void textPar(TextPar textPar) {
        INode parent = textPar.getParent();
        if (parent.getType() == NodeType.LIST)
            out.print("    "); // indent in

        haveRuns(textPar);
    }

    @Override
    public void textRun(TextRun textRun) {
        for (String s : textRun.textList) {
            out.print(s);
        }
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        haveRuns(runGroup);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        haveRuns(fontEnv);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        String mod1 = MarkdownFn.mod(fontStyleEnv, false);
        out.print(mod1);

        haveRuns(fontStyleEnv);

        String mod2 = MarkdownFn.mod(fontStyleEnv, true);
        out.print(mod2);
    }

    @Override
    public void hr(Hr hr) {
        out.println();
        out.println("----------");
        out.println();
    }

    @Override
    public void image(Image image) {
        String href = image.getHref();
        String description = image.getDescription();
        out.printf("[%s](%s)\n", description, href);
    }

    class CellConv
            extends AbstractDocVisitor {

        @Override
        public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
            String mod = MarkdownFn.mod(fontStyleEnv, false);
            out.print(mod);
            // TODO end...
        }

        @Override
        public void textRun(TextRun textRun) {
            for (String s : textRun.textList) {
                if (s == null)
                    return;
                s = s.replace('\n', ' ');
                s = s.replace("|", "\\|");
                out.print(s);
            }
        }

    }

    IDocVisitor cellBuilder = new CellConv().depth();

}
