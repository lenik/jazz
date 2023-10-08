package net.bodz.bas.doc.node.util;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.io.ITreeOut;

public class DocNodeDumper
        extends FullDocVisitor {

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
    public void beginNode(INode node) {
        String typeName = null;
        switch (node.getType()) {
        case PART:
            Part section = (Part) node;
            PartGroup group = section.getParent();
            typeName = "part/" + group.getLevel();
        default:
        }

        if (typeName == null) {
            typeName = node.getClass().getSimpleName();
            if (typeName.startsWith("Doc"))
                typeName = typeName.substring(3);
            typeName = Strings.lcfirst(typeName);
        }

        out.printf("%s {\n", typeName);
        out.enter();
    }

    @Override
    public void endNode(INode node) {
        out.leave();
        out.println("}");
    }

    @Override
    public void attribute(String name, Object value) {
        if (showAttributes)
            out.println("@" + name + " = " + value);
    }

    @Override
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
    public void document(Document doc) {
        out.println("title: " + doc.title.getText());
        doc.internalAccept(this);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        out.println("level: " + partGroup.getLevel());
        partGroup.internalAccept(this);
    }

    @Override
    public void part(Part part, int index) {
        out.println("title: " + part.title.getText());
        part.internalAccept(this);
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        if (cell.pars.size() == 1) {
            IPar par = cell.pars.get(0);
            if (par.isTextPar()) {
                TextPar textPar = (TextPar) par;
                if (textPar.isSimpleText()) {
                    String text = textPar.getText();
                    if (text != null)
                        text = text.trim();
                    chars(text);
                    return;
                }
            }
        }
        cell.internalAccept(this);
    }

    @Override
    public void textPar(TextPar textPar) {
        property("lineSpacing", textPar.getLineSpacing());
        property("indent", textPar.getIndent());
        property("leadingIndent", textPar.getLeadingIndent());
        textPar.internalAccept(this);
    }

    @Override
    public void chars(String s) {
        if (s == null)
            if (!showNulls)
                return;
        if (showChars)
            out.println("\"" + s + "\"");
    }

}
