package net.bodz.bas.doc.node;

import net.bodz.bas.doc.attr.ElementType;
import net.bodz.bas.doc.attr.MeasureLength;
import net.bodz.bas.doc.attr.SectionLevel;
import net.bodz.bas.doc.io.IDocumentWriter;
import net.bodz.bas.err.UnexpectedException;

public class DocWriter
        implements
            IDocumentWriter<DocWriter> {

    DocNodeStack stack = new DocNodeStack();

    public DocWriter(DocDocument document) {
        stack.push(document);
    }

    @Override
    public DocWriter begin() {
        DocRunGroup runs = stack.popUntilRuns();
        DocRunGroup env = runs.addEnv();
        stack.push(env);
        return this;
    }

    @Override
    public DocWriter begin(String className) {
        DocRunGroup runs = stack.popUntilRuns();
        DocRunGroup env = runs.addEnv();
        env.styleClass = className;
        return this;
    }

    @Override
    public DocWriter end() {
        stack.pop();
        return this;
    }

    @Override
    public DocWriter end(ElementType elementType) {
        switch (elementType) {
        case BLOCK:
            stack.popUntil(IDocPar.class);
            break;
        case CHAPTER:
            stack.popUntilSection(SectionLevel.CHAPTER);
            break;
        case SECTION:
            stack.popUntilSection(SectionLevel.SECTION);
            break;
        case SUBSECTION:
            stack.popUntilSection(SectionLevel.SUBSECTION);
            break;
        case SUBSUBSECTION:
            stack.popUntilSection(SectionLevel.SUBSUBSECTION);
            break;
        case PARAGRAPH:
            stack.popUntilSection(SectionLevel.PARAGRAPH);
            break;
        case SUBPARAGRAPH:
            stack.popUntilSection(SectionLevel.SUBPARAGRAPH);
            break;
        case UNORDERED_LIST:
            stack.popUntilList(false);
            break;
        case ORDERED_LIST:
            stack.popUntilList(true);
            break;
        case ITEM:
            stack.popUntil(DocListPar.class);
            return this;
        case TABLE:
            stack.popUntil(DocTable.class);
            break;
        case TR:
            stack.popUntil(DocTableRow.class);
            break;
        case TH:
            stack.popUntil(DocTableCell.class);
            break;
        case TD:
            stack.popUntil(DocTableCell.class);
            break;
        default:
            throw new UnexpectedException();
        }
        stack.pop();
        return this;
    }

    @Override
    public void endAll() {
        stack.popUntilDoc();
    }

    @Override
    public void flush() {
    }

    @Override
    public DocWriter attribute(String name, Object value) {
        stack.top().setAttribute(name, value);
        return this;
    }

    @Override
    public DocWriter put(Object element) {
        IDocNode top = stack.top();
        if (element instanceof IDocNode) {
            IDocNode node = (IDocNode) element;
            if (node.isPar() && top.havePars()) {
                DocParGroup pars = (DocParGroup) top;
                pars.pars.append((IDocPar) node);
            } //
            else if (node.isRun() && top.haveRuns()) {
                DocRunGroup runs = (DocRunGroup) top;
                runs.runs.append((IDocRun) node);
            }
        }
        return this;
    }

    @Override
    public DocWriter section(SectionLevel level, Object title) {
        if (title == null)
            throw new NullPointerException("title");
        DocDocument doc = stack.popUntilDoc();
        DocSection section = doc.sections.append().level(level);
        if (title instanceof DocTextPar)
            section.setTitle((DocTextPar) title);
        else
            section.getTitle().setText(title.toString());
        stack.push(section);
        return this;
    }

    @Override
    public DocWriter list(boolean ordered) {
        DocParGroup pars = stack.popUntilPars();
        DocListPar listPar = pars.addListPar(ordered);
        stack.push(listPar);
        return this;
    }

    @Override
    public DocWriter item() {
        DocListPar listPar = stack.popUntil(DocListPar.class);
        DocTextPar item = new DocTextPar(listPar);
        stack.push(item);
        return this;
    }

    protected FontEnv beginFont() {
        DocRunGroup runs = stack.popUntilRuns();
        return stack.push(runs.addFontEnv());
    }

    protected FontStyleEnv beginFontStyle() {
        DocRunGroup runs = stack.popUntilRuns();
        return stack.push(runs.addFontStyleEnv());
    }

    @Override
    public DocWriter b() {
        beginFontStyle().bold = true;
        return this;
    }

    @Override
    public DocWriter i() {
        beginFontStyle().italic = true;
        return this;
    }

    @Override
    public DocWriter u() {
        beginFontStyle().underline = true;
        return this;
    }

    @Override
    public DocWriter p() {
        DocParGroup pars = stack.popUntilPars();
        DocTextPar textPar = new DocTextPar(pars);
        stack.push(textPar);
        return this;
    }

    @Override
    public DocWriter table() {
        DocParGroup pars = stack.popUntilPars();
        DocTable table = pars.addTable();
        stack.push(table);
        return this;
    }

    @Override
    public DocWriter tr() {
        DocTable table = stack.popUntil(DocTable.class);
        DocTableRow row = table.rows.append();
        stack.push(row);
        return this;
    }

    @Override
    public DocWriter th() {
        DocTableRow row = stack.popUntil(DocTableRow.class);
        DocTableCell cell = row.cells.append();
        cell.setHeader(true);
        stack.push(cell);
        return this;
    }

    @Override
    public DocWriter td() {
        DocTableRow row = stack.popUntil(DocTableRow.class);
        DocTableCell cell = row.cells.append();
        stack.push(cell);
        return this;
    }

    @Override
    public DocWriter hr() {
        stack.popUntilPars().addHr();
        return this;
    }

    @Override
    public DocWriter font(String family, MeasureLength size) {
        DocRunGroup runs = stack.popUntilRuns();
        FontEnv font = new FontEnv(runs);
        if (family != null)
            font.setFamily(family);
        if (size != null)
            font.setSize(size);
        stack.push(font);
        return this;
    }

    @Override
    public DocWriter fontFamily(String family) {
        return font(family, null);
    }

    @Override
    public DocWriter fontSize(MeasureLength size) {
        return font(null, size);
    }

    @Override
    public DocWriter color(String color) {
        DocRunGroup runs = stack.popUntilRuns();
        FontEnv font = new FontEnv(runs);
        font.color = color;
        stack.push(font);
        return this;
    }

    @Override
    public DocWriter backgroundColor(String color) {
        DocRunGroup runs = stack.popUntilRuns();
        FontEnv font = new FontEnv(runs);
        font.background = color;
        stack.push(font);
        return this;
    }

    @Override
    public DocWriter text(String s) {
        IDocNode top = stack.top();
        top.setText(s);
        return this;
    }

    @Override
    public String toString() {
        return stack.toString();
    }

}
