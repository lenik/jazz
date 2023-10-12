package net.bodz.bas.doc.node.util;

import net.bodz.bas.c.string.Strings;
import net.bodz.bas.c.type.TypeId;
import net.bodz.bas.c.type.TypeKind;
import net.bodz.bas.doc.node.*;
import net.bodz.bas.io.ITreeOut;

public class DocNodeDumper
        extends AbstractDocVisitor {

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

    void beginNode(INode node) {
        String typeName = node.getClass().getSimpleName();
        typeName = Strings.lcfirst(typeName);

        switch (node.getType()) {
        case PART:
            Part section = (Part) node;
            typeName += "/" + section.getLevel();
        default:
        }

        out.printf("%s {\n", typeName);
        out.enter();
    }

    void endNode(INode node) {
        out.leave();
        out.println("}");
    }

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
    public void document(Document doc) {
        beginNode(doc);
        out.println("title: " + doc.title.getText());

        super.document(doc);
        endNode(doc);
    }

    @Override
    public void partGroup(PartGroup partGroup) {
        beginNode(partGroup);
        out.println("level: " + partGroup.getLevel());

        super.partGroup(partGroup);
        endNode(partGroup);
    }

    @Override
    public void part(Part part, int index) {
        beginNode(part);
        out.println("title: " + part.title.getText());

        super.part(part, index);
        endNode(part);
    }

    @Override
    public void list(ListPar list) {
        beginNode(list);
        super.list(list);
        endNode(list);
    }

    @Override
    public void listItem(ListItem item, int index, int itemIndex) {
        beginNode(item);
        super.listItem(item, index, itemIndex);
        endNode(item);
    }

    @Override
    public void table(Table table) {
        beginNode(table);
        super.table(table);
        endNode(table);
    }

    @Override
    public void tableRow(TableRow row, int index) {
        beginNode(row);
        super.tableRow(row, index);
        endNode(row);
    }

    @Override
    public void tableCell(TableCell cell, int index) {
        beginNode(cell);

        boolean simple = false;
        if (cell.pars.size() == 1) {
            // shortcut for simple text: don't create pars.
            IPar par = cell.pars.get(0);
            if (par.isTextPar()) {
                TextPar textPar = (TextPar) par;
                if (textPar.isSimpleText()) {
                    String text = textPar.getText();
                    if (text != null)
                        text = text.trim();
                    chars(text);
                    simple = true;
                }
            }
        }
        if (!simple)
            super.tableCell(cell, index);
        endNode(cell);
    }

    @Override
    public void textBox(TextBox textBox) {
        beginNode(textBox);
        super.textBox(textBox);
        endNode(textBox);
    }

    @Override
    public void textPar(TextPar textPar) {
        beginNode(textPar);
        property("lineSpacing", textPar.getLineSpacing());
        property("indent", textPar.getIndent());
        property("leadingIndent", textPar.getLeadingIndent());
        super.textPar(textPar);
        endNode(textPar);
    }

    @Override
    public void textRun(TextRun textRun) {
        beginNode(textRun);
        for (String s : textRun.textList)
            chars(s);
        endNode(textRun);
    }

    @Override
    public void runGroup(RunGroup runGroup) {
        beginNode(runGroup);
        super.runGroup(runGroup);
        endNode(runGroup);
    }

    @Override
    public void fontEnv(FontEnv fontEnv) {
        beginNode(fontEnv);
        super.fontEnv(fontEnv);
        endNode(fontEnv);
    }

    @Override
    public void fontStyleEnv(FontStyleEnv fontStyleEnv) {
        beginNode(fontStyleEnv);
        super.fontStyleEnv(fontStyleEnv);
        endNode(fontStyleEnv);
    }

    public void chars(String s) {
        if (s == null)
            if (!showNulls)
                return;
        if (showChars)
            out.println("\"" + s + "\"");
    }

    @Override
    public void hr(Hr hr) {
        beginNode(hr);
        super.hr(hr);
        endNode(hr);
    }

    @Override
    public void image(Image image) {
        beginNode(image);
        super.image(image);
        beginNode(image);
    }

}
