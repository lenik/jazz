package net.bodz.bas.doc.node;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.doc.attr.HorizAlignment;
import net.bodz.bas.io.ITreeOut;

public class DocNodeDumper
        implements
            IDocVisitor {

    ITreeOut out;

    boolean showAttributes = true;
    boolean showProperties = true;
    boolean showNulls = false;
    boolean showZeros = false;
    boolean showChars = true;

    String nullStr = "(null)";

    public DocNodeDumper(ITreeOut out) {
        if (out == null)
            throw new NullPointerException("out");
        this.out = out;
    }

    @Override
    public void beginNode(IDocNode node) {
        String typeName = node.getClass().getSimpleName();
        if (typeName.startsWith("Doc"))
            typeName = typeName.substring(3);
        typeName = Strings.lcfirst(typeName);
        out.printf("%s {\n", typeName);
        out.enter();
    }

    @Override
    public void endNode(IDocNode node) {
        out.leave();
        out.println("}");
    }

    @Override
    public void attribute(String name, Object value) {
        if (showAttributes)
            out.println("@" + name + " = " + value);
    }

    public void property(String name, Object value) {
        if (showProperties) {
            if (value == null) {
                if (showNulls)
                    out.println("\\" + name + ": " + nullStr);
                return;
            }
            if (!showZeros) {
                int typeId = TypeKind.getTypeId(value.getClass());
                switch (typeId) {
                case TypeId.BOOLEAN:
                    if (((Boolean) value).booleanValue() == false)
                        return;
                case TypeId.INTEGER:
                    if (((Integer) value).intValue() == 0)
                        return;
                case TypeId.LONG:
                    if (((Long) value).longValue() == 0L)
                        return;
                case TypeId.DOUBLE:
                    if (((Double) value).doubleValue() == 0.0)
                        return;
                }
            }
            out.println("\\" + name + ": " + value);
        }
    }

    @Override
    public void chars(String s) {
        if (showChars)
            out.println("\"" + s + "\"");
    }

    @Override
    public void document(DocDocument doc) {
        IDocPar title = doc.getTitle();
        if (title != null) {
            out.println("title: " + title.getText());
        }
        doc.internalAccept(this);
    }

    @Override
    public void section(DocSection section) {
        IDocPar title = section.getTitle();
        if (title != null) {
            out.println("title: " + title.getText());
        }
        section.internalAccept(this);
    }

    @Override
    public void par(IDocPar par) {
        HorizAlignment alignment = par.getAlignment();
        property("alignment", alignment);
        par.internalAccept(this);
    }

    @Override
    public void run(IDocRun run) {
        String text = run.getText();
        property("text", text);
        run.internalAccept(this);
    }

    @Override
    public void table(DocTable table) {
        table.internalAccept(this);
    }

    @Override
    public void tableRow(DocTableRow row) {
        row.internalAccept(this);
    }

    @Override
    public void tableCell(DocTableCell cell) {
        cell.internalAccept(this);
    }

    @Override
    public void list(DocListPar list) {
        list.internalAccept(this);
    }

    @Override
    public void listItem(IDocPar item) {
        item.internalAccept(this);
    }

    @Override
    public void textPar(DocTextPar textPar) {
        property("lineSpacing", textPar.lineSpacing);
        property("indent", textPar.indent);
        property("leadingIndent", textPar.leadingIndent);
        textPar.internalAccept(this);
    }

    @Override
    public void textRun(DocTextRun textRun) {
        textRun.internalAccept(this);
    }

}
