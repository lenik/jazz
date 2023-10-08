package net.bodz.bas.doc.io;

import java.io.IOException;

import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.t.stack.ContextStack;
import net.bodz.bas.t.stack.NodePredicates;

public class DomWriter
        implements
            IStructDocWriter<DomWriter> {

    static class np
            extends NodePredicates {
    }

    public final ContextStack<INode> stack = new ContextStack<>();

    public DomWriter(Document document) {
        stack.push(document);
    }

    @Override
    public DomWriter begin() {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        AbstractRunGroup env = runs.addEnv();
        stack.push(env);
        return this;
    }

    @Override
    public DomWriter begin(String className) {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        AbstractRunGroup env = runs.addEnv();
        env.setStyleClass(className);
        return this;
    }

    @Override
    public DomWriter end() {
        stack.pop();
        return this;
    }

    @Override
    public DomWriter end(ElementType elementType) {
        switch (elementType) {
        case BLOCK:
            stack.popAhead(np.IS_PAR);
            break;
        case GENERIC_SECTION:
            stack.popAhead(np.IS_PART);
            break;
        case CHAPTER:
            stack.popAhead(np.CHAPTER);
            break;
        case SECTION:
            stack.popAhead(np.SECTION);
            break;
        case SUBSECTION:
            stack.popAhead(np.SUBSECTION);
            break;
        case SUBSUBSECTION:
            stack.popAhead(np.SUBSUBSECTION);
            break;
        case PARAGRAPH:
            stack.popAhead(np.PARAGRAPH);
            break;
        case SUBPARAGRAPH:
            stack.popAhead(np.SUBPARAGRAPH);
            break;
        case UNORDERED_LIST:
            stack.popAhead(np.UL);
            break;
        case ORDERED_LIST:
            stack.popAhead(np.OL);
            break;
        case ITEM:
            stack.popAhead(np.LIST);
            return this;
        case TABLE:
            stack.popAhead(np.TABLE);
            break;
        case TR:
            stack.popAhead(np.TABLE_ROW);
            break;
        case TH:
            stack.popAhead(np.TABLE_CELL);
            break;
        case TD:
            stack.popAhead(np.TABLE_CELL);
            break;
        default:
            throw new UnexpectedException();
        }
        stack.pop();
        return this;
    }

    @Override
    public void endAll() {
        stack.popAhead(np.IS_DOC);
    }

    @Override
    public void flush() {
    }

    @Override
    public DomWriter attribute(String name, Object value) {
        stack.top().setAttribute(name, value);
        return this;
    }

    @Override
    public DomWriter put(Object element) {
        INode top = stack.top();
        if (element instanceof INode) {
            INode node = (INode) element;
            if (node.isPar() && top.havePars()) {
                AbstractParGroup pars = (AbstractParGroup) top;
                pars.pars.append((IPar) node);
            } //
            else if (node.isRun() && top.haveRuns()) {
                AbstractRunGroup runs = (AbstractRunGroup) top;
                runs.runs.append((IRun) node);
            }
        }
        return this;
    }

    @Override
    public DomWriter section(PartLevel level, String title) {
        if (title == null)
            throw new NullPointerException("title");

        PartGroup group = null;

        group = stack.popAhead(NodePredicates.sectionGroup(level), null);
        if (group == null) {
            // not found? create one
            PartGroup above = null;
            if (level.level > PartLevel.LEVEL_MIN)
                above = stack.findFromTop(NodePredicates.sectionGroupAbove(level));

            AbstractParGroup pars = stack.popAhead(np.HAVE_PARS);
            if (above != null)
                for (PartLevel sl = above.getLevel().getDown(); sl.level < level.level; sl = sl.getDown()) {
                    group = pars.addSectionGroup(sl);
                    pars = group.addPart(null/* title */);
                }
            group = pars.addSectionGroup(level);
            stack.push(group);
        }

        Part section = group.addPart(title);
        stack.push(section);
        return this;
    }

    @Override
    public DomWriter list(boolean ordered) {
        AbstractParGroup pars = stack.popAhead(np.HAVE_PARS);
        ListPar listPar = pars.addListPar(ordered);
        stack.push(listPar);
        return this;
    }

    @Override
    public DomWriter item() {
        ListPar listPar = stack.popAhead(np.LIST);
        TextPar item = listPar.addItem().addTextPar();
        stack.push(item);
        return this;
    }

    protected FontEnv beginFont() {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        return stack.push(runs.addFontEnv());
    }

    protected FontStyleEnv beginFontStyle() {
        IHaveRuns runs = stack.findFromTop(np.HAVE_RUNS);
        if (runs != null) {
            runs = stack.popAhead(np.HAVE_RUNS);
        } else {
            AbstractParGroup pars = stack.findFromTop(np.HAVE_PARS);
            if (pars == null)
                throw new IllegalStateException();
            TextPar par = pars.addTextPar();
            runs = par;
        }
        return stack.push(runs.addFontStyleEnv());
    }

    @Override
    public DomWriter b() {
        beginFontStyle().setBold(true);
        return this;
    }

    @Override
    public DomWriter i() {
        beginFontStyle().setItalic(true);
        return this;
    }

    @Override
    public DomWriter u() {
        beginFontStyle().setUnderline(true);
        return this;
    }

    @Override
    public DomWriter p() {
        AbstractParGroup pars = stack.popAhead(np.HAVE_PARS);
        TextPar textPar = pars.addTextPar();
        stack.push(textPar);
        return this;
    }

    @Override
    public DomWriter table() {
        AbstractParGroup pars = stack.popAhead(np.HAVE_PARS);
        Table table = pars.addTable();
        stack.push(table);
        return this;
    }

    @Override
    public DomWriter tr() {
        Table table = stack.popAhead(np.TABLE);
        TableRow row = table.rows.append();
        stack.push(row);
        return this;
    }

    @Override
    public DomWriter th() {
        TableRow row = stack.popAhead(np.TABLE_ROW);
        TableCell cell = row.cells.append();
        cell.setHeader(true);
        stack.push(cell);
        return this;
    }

    @Override
    public DomWriter td() {
        TableRow row = stack.popAhead(np.TABLE_ROW);
        TableCell cell = row.cells.append();
        stack.push(cell);
        return this;
    }

    @Override
    public DomWriter hr() {
        stack.popAhead(np.HAVE_PARS).addHr();
        return this;
    }

    @Override
    public DomWriter font(String family, MeasureLength size) {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        FontEnv font = new FontEnv(runs);
        if (family != null)
            font.setFamily(family);
        if (size != null)
            font.setSize(size);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter fontFamily(String family) {
        return font(family, null);
    }

    @Override
    public DomWriter fontSize(MeasureLength size) {
        return font(null, size);
    }

    @Override
    public DomWriter color(String color) {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        FontEnv font = new FontEnv(runs);
        font.setColor(color);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter backgroundColor(String color) {
        AbstractRunGroup runs = stack.popAhead(np.HAVE_RUNS);
        FontEnv font = new FontEnv(runs);
        font.setBackground(color);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter data(Object data) {
        String s = data == null ? null : data.toString();
        text(s);
        return this;
    }

    @Override
    public DomWriter text(String s) {
        INode top = stack.top();
        top.setText(s);
        return this;
    }

    @Override
    public void write(int ch)
            throws IOException {
        String s = new String(new int[] { ch }, 0, 1);
        text(s);
    }

    @Override
    public void write(char[] chars, int off, int len)
            throws IOException {
        String s = new String(chars, off, len);
        text(s);
    }

    @Override
    public boolean isClosed() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

}
