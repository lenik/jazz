package net.bodz.bas.doc.io;

import java.io.IOException;

import net.bodz.bas.doc.node.*;
import net.bodz.bas.doc.property.Color;
import net.bodz.bas.doc.property.ElementType;
import net.bodz.bas.doc.property.HorizAlignment;
import net.bodz.bas.doc.property.MeasureLength;
import net.bodz.bas.doc.property.PartLevel;
import net.bodz.bas.err.UnexpectedException;
import net.bodz.bas.io.res.IStreamInputSource;
import net.bodz.bas.log.Logger;
import net.bodz.bas.log.LoggerFactory;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.bas.t.list.IAutoList;
import net.bodz.bas.t.stack.ContextStack;
import net.bodz.bas.t.stack.NodePredicates;

public class DomWriter
        implements IStructDocWriter<DomWriter>,
                   IDomCreatorImpl {

    static class np
            extends NodePredicates {
    }

    static final Logger logger = LoggerFactory.getLogger(DomWriter.class);

    public final ContextStack<INode> stack = new ContextStack<>();

    private IDataFormat dataFormat = SimpleFormat.INSTANCE;

    public DomWriter(Document document) {
        stack.push(document);
    }

    @Override
    public IDataFormat getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(IDataFormat dataFormat) {
        if (dataFormat == null)
            throw new NullPointerException("dataFormat");
        this.dataFormat = dataFormat;
    }

    @Override
    public DomWriter data(Object data) {
        String text = formatData(data);
        text(text);
        return this;
    }

    @Override
    public INode getContext() {
        return stack.top();
    }

    @Override
    public IHavePars makePars() {
        IHavePars havePars = stack.popFor(np.HAVE_PARS, null);
        if (havePars != null)
            return havePars;
        // stack.popAhead(np.PART);
        throw new IllegalStateException("no available pars");
    }

    @Override
    public IHaveRuns makeRuns() {
        IHaveRuns haveRuns = stack.popFor(np.HAVE_RUNS, null);
        if (haveRuns != null)
            return haveRuns;
        IHavePars havePars = stack.popFor(np.HAVE_PARS, null);
        if (havePars != null) {
            IAutoList<IPar> pars = havePars.getPars();
            int n = pars.size();
            if (n != 0) {
                IPar lastPar = pars.get(n - 1);
                if (lastPar.haveRuns()) {
                    return (IHaveRuns) lastPar;
                }
            }
            TextPar textPar = havePars.addTextPar();
            return textPar;
        }
        throw new IllegalStateException("no available runs");
    }

    @Override
    public void enter(INode node) {
        stack.push(node);
    }

    @Override
    public DomWriter begin() {
        IHaveRuns runs = stack.popFor(np.HAVE_RUNS);
        IHaveRuns env = runs.addEnv();
        stack.push(env);
        return this;
    }

    @Override
    public DomWriter begin(String className) {
        IHaveRuns runs = stack.popFor(np.HAVE_RUNS);
        RunGroup env = runs.addEnv();
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
                stack.popFor(np.IS_PAR);
                break;
            case GENERIC_SECTION:
                stack.popFor(np.PART);
                break;
            case CHAPTER:
                stack.popFor(np.CHAPTER);
                break;
            case SECTION:
                stack.popFor(np.SECTION);
                break;
            case SUBSECTION:
                stack.popFor(np.SUBSECTION);
                break;
            case SUBSUBSECTION:
                stack.popFor(np.SUBSUBSECTION);
                break;
            case PARAGRAPH:
                stack.popFor(np.PARAGRAPH);
                break;
            case SUBPARAGRAPH:
                stack.popFor(np.SUBPARAGRAPH);
                break;
            case UNORDERED_LIST:
                stack.popFor(np.UL);
                break;
            case ORDERED_LIST:
                stack.popFor(np.OL);
                break;
            case ITEM:
                stack.popFor(np.LIST_ITEM);
                return this;
            case TABLE:
                stack.popFor(np.TABLE);
                break;
            case TR:
                stack.popFor(np.TABLE_ROW);
                break;
            case TH:
                stack.popFor(np.TABLE_CELL);
                break;
            case TD:
                stack.popFor(np.TABLE_CELL);
                break;
            default:
                throw new UnexpectedException();
        }
        stack.pop();
        return this;
    }

    @Override
    public void endAll() {
        stack.popFor(np.DOC);
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
                IHavePars pars = (IHavePars) top;
                pars.getPars().append((IPar) node);
            } //
            else if (node.isRun() && top.haveRuns()) {
                IHaveRuns runs = (IHaveRuns) top;
                runs.getRuns().append((IRun) node);
            }
        }
        return this;
    }

    @Override
    public DomWriter section(PartLevel level, String title) {
        if (title == null)
            throw new NullPointerException("title");

        PartGroup group = null;

        group = stack.popFor(NodePredicates.partGroup(level), null);
        if (group == null) {
            // not found? create one
            PartGroup above = null;
            if (level.level > PartLevel.LEVEL_MIN)
                above = stack.findFromTop(NodePredicates.partGroupAbove(level));

            IHavePars pars = stack.popFor(np.HAVE_PARS);
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
        IHavePars pars = makePars();
        ListPar listPar = pars.addListPar(ordered);
        stack.push(listPar);
        return this;
    }

    @Override
    public DomWriter item() {
        ListPar listPar = stack.popFor(np.LIST);
        ListItem item = listPar.addItem();
        stack.push(item);
        return this;
    }

    protected FontEnv beginFont() {
        IHaveRuns runs = makeRuns();
        return stack.push(runs.addFontEnv());
    }

    protected FontStyleEnv beginFontStyle() {
        IHaveRuns runs = makeRuns();
//        IHaveRuns runs = stack.findFromTop(np.HAVE_RUNS);
//        if (runs != null) {
//            runs = stack.popAhead(np.HAVE_RUNS);
//        } else {
//            IHavePars pars = stack.findFromTop(np.HAVE_PARS);
//            if (pars == null)
//                throw new IllegalStateException();
//            TextPar par = pars.addTextPar();
//            runs = par;
//        }
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
        IHavePars pars = makePars();
        TextPar textPar = pars.addTextPar();
        stack.push(textPar);
        return this;
    }

    @Override
    public DomWriter table(int headRows, int headColumns) {
        IHavePars pars = makePars();
        Table table = pars.addTable();
        table.firstRows = headRows;
        table.firstColumns = headColumns;
        if (headRows > 0)
            table.hBands = true;
        else if (headColumns > 0)
            table.vBands = true;
        stack.push(table);
        return this;
    }

    @Override
    public DomWriter tr() {
        Table table = stack.popFor(np.TABLE);
        TableRow row = table.rows.append();
        stack.push(row);
        return this;
    }

    DomWriter _tr(boolean header, Object... cells) {
        tr();
        for (Object cell : cells)
            if (header)
                th(cell);
            else
                td(cell);
        TableRow row = (TableRow) stack.top();
        row.expandLastColumn();
        return end();
    }

    @Override
    public DomWriter trHead(Object... cells) {
        return _tr(true, cells);
    }

    @Override
    public DomWriter trHead(String... cells) {
        return _tr(true, (Object[]) cells);
    }

    @Override
    public DomWriter tr(Object... cells) {
        return _tr(false, cells);
    }

    @Override
    public DomWriter tr(String... cells) {
        return _tr(false, (Object[]) cells);
    }

    @Override
    public DomWriter th() {
        TableRow row = stack.popFor(np.TABLE_ROW);
        TableCell cell = row.cells.append();
        cell.setHeader(true);
        stack.push(cell);
        return this;
    }

    @Override
    public DomWriter td() {
        TableRow row = stack.popFor(np.TABLE_ROW);
        TableCell cell = row.cells.append();
        stack.push(cell);
        return this;
    }

    @Override
    public DomWriter breakFill(BreakerType breakType) {
        IHaveRuns runs = makeRuns();
        runs.addBreak(breakType);
        return this;
    }

    @Override
    public DomWriter hr() {
        IHavePars pars = makePars();
        pars.addHr();
        return this;
    }

    @Override
    public DomWriter image(IStreamInputSource source, MeasureLength width, MeasureLength height) {
        Image image;

        IHaveRuns runs = stack.popFor(np.HAVE_RUNS, null);
        if (runs == null) {
            IHavePars pars = stack.popFor(np.HAVE_PARS, null);
            if (pars == null)
                throw new IllegalStateException();
            image = pars.addImage();
        } else {
            image = runs.addImage();
        }

        image.setSource(source);
        image.setWidth(width);
        image.setHeight(height);
        return this;
    }

    @Override
    public DomWriter setAlign(HorizAlignment alignment) {
        IPar par = stack.popFor(np.IS_PAR);
        if (par != null)
            par.setAlignment(alignment);
        else {
            // logger.warn("no context par.");
            throw new IllegalStateException("no context par");
        }
        return this;
    }

    @Override
    public DomWriter font(String family, MeasureLength size) {
        IHaveRuns runs = makeRuns();
        FontEnv font = runs.addFontEnv();
        if (family != null)
            font.setFamily(family);
        if (size != null)
            font.setSize(size);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter color(Color color) {
        IHaveRuns runs = makeRuns();
        FontEnv font = runs.addFontEnv();
        font.setColor(color);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter backgroundColor(Color color) {
        IHaveRuns runs = makeRuns();
        FontEnv font = runs.addFontEnv();
        font.setBackground(color);
        stack.push(font);
        return this;
    }

    @Override
    public DomWriter text(String s) {
        INode top = stack.top();
        if (top.getType() == NodeType.TEXT_RUN) {
            TextRun run = (TextRun) top;
            run.addText(s);
        } else if (top.haveRuns()) { // *env
            IHaveRuns runs = (IHaveRuns) top;
            runs.addText(s);
        } else if (top.havePars()) {
            IHavePars pars = (IHavePars) top;
            pars.addText(s);
        } else {
            throw new IllegalStateException();
        }
        return this;
    }

    @Override
    public void writeChar(int ch)
            throws IOException {
        String s = new String(new int[] { ch }, 0, 1);
        text(s);
    }

    @Override
    public void write(@NotNull char[] chars, int off, int len)
            throws IOException {
        String s = new String(chars, off, len);
        text(s);
    }

    public boolean isClosed() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return stack.toString();
    }

}
