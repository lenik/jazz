package net.bodz.bas.doc.node.conv;

import net.bodz.bas.c.object.Nullables;
import net.bodz.bas.c.string.Strings;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.node.util.FullDocVisitor;
import net.bodz.bas.doc.node.util.IListStyle;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.io.ITreeOut;

public class MarkdownConverter
        extends FullDocVisitor {

    ITreeOut out;

    public MarkdownConverter(ITreeOut out) {
        this.out = out;
    }

    @Override
    public void endNode(INode node) {
        if (node.isPar())
            out.println();
    }

    @Override
    public void document(Document doc) {
        String title = doc.title.getText();
        if (!Nullables.isEmpty(title)) {
            out.println("# " + title);
            out.println();
        }
        doc.internalAccept(this);
    }

    @Override
    public void part(Part part, int index) {
        // reserved # for title.
        String sym = Strings.repeat(part.getLevel().level + 1, '#');
        String title = part.title.getText();
        out.println(sym + " " + title);
        out.println();
        part.internalAccept(this);
    }

    @Override
    public void hr(Hr hr) {
        out.println();
        out.println("----------");
        out.println();
    }

    @Override
    public void list(ListPar list) {
        list.internalAccept(this);
        out.println();
    }

    @Override
    public void listItem(IPar par, int index, int itemIndex) {
        if (par.isListItem()) {
            ListItem item = (ListItem) par;
            ListPar list = item.getParent();
            int startNumber = list.getStartNumber();
            int itemCount = list.getItemCount();

            IListStyle listStyle = list.getListStyle();
            String text = listStyle.format(itemIndex + startNumber, itemCount);
            out.print(text);

            if (list.isOrdered())
                out.print('.');
            out.print(' ');

            par.internalAccept(this);
        } else {
            // if (par.isTextPar())
            out.print("    "); // indent in
            par.accept(this);
        }
    }

    static String alignPattern(HorizAlignment alignment) {
        switch (alignment) {
        case LEFT:
        default:
            return "---";
        case RIGHT:
            return "--:";
        case CENTER:
            return ":-:";
        case FILL:
            return "---";
        }
    }

    @Override
    public void tableRow(TableRow row, int index) {
        out.print("| ");
        row.internalAccept(this);
        out.println();

        if (index == 0) {
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
                alignPatterns.append(alignPattern(alignment));
                alignPatterns.append(" | ");
            }
            out.println(alignPatterns); // md table requires.
        }
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        cell.internalAccept(CELL_CONV);
        out.print(" | ");
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        fontEnv.internalAccept(this);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        StringBuilder mod = new StringBuilder();
        if (fontStyleEnv.isBold())
            mod.append("**");
        if (fontStyleEnv.isItalic())
            mod.append("*");
        if (fontStyleEnv.isStrikeline())
            mod.append("~~");
        if (fontStyleEnv.isUnderline())
            mod.append("_");
        out.print(mod);
        fontStyleEnv.internalAccept(this);
        mod.reverse();
        out.print(mod);
    }

    @Override
    public void image(Image image) {
        String href = image.getHref();
        String description = image.getDescription();
        out.printf("[%s](%s)\n", description, href);
    }

    @Override
    public void chars(String s) {
        out.print(s);
    }

    class CellConv
            extends FullDocVisitor {

        @Override
        public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
            StringBuilder mod = new StringBuilder();
            if (fontStyleEnv.isBold())
                mod.append("**");
            if (fontStyleEnv.isItalic())
                mod.append("*");
            if (fontStyleEnv.isStrikeline())
                mod.append("~~");
            if (fontStyleEnv.isUnderline())
                mod.append("_");
            out.print(mod);
            fontStyleEnv.internalAccept(this);
            mod.reverse();
            out.print(mod);
        }

        @Override
        public void chars(String s) {
            if (s == null)
                return;
            s = s.replace('\n', ' ');
            s = s.replace("|", "\\|");
            out.print(s);
        }

    }

    CellConv CELL_CONV = new CellConv();

}
