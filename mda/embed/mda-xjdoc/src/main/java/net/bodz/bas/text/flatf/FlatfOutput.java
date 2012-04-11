package net.bodz.bas.text.flatf;

import java.io.IOException;
import java.io.Writer;

import net.bodz.bas.i18n.dstr.DomainString;

public class FlatfOutput
        implements IFlatfOutput {

    int depth;
    Writer writer;
    String indent;
    String langSeparator;
    String lineSeparator;

    public FlatfOutput(Writer writer) {
        if (writer == null)
            throw new NullPointerException("writer");
        this.writer = writer;
        setIndent("    ");
    }

    @Override
    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        if (indent == null)
            throw new NullPointerException("indent");
        this.indent = indent;
        langSeparator = "\n" + indent + indent;
        lineSeparator = "\n" + indent + indent + indent;
    }

    @Override
    public void sectionBegin(String sectionName)
            throws IOException {
        writer.append("\n[" + sectionName + "]\n");
        depth++;
    }

    @Override
    public void sectionEnd()
            throws IOException {
        // writer.append("\n");
        depth--;
    }

    @Override
    public void attribute(String name, String string)
            throws IOException {
        String linecont = string.replace("\n", "\\\n");
        for (int d = 0; d < depth; d++)
            writer.write(indent);
        writer.write(name + " = " + linecont + "\n");
    }

    @Override
    public void attribute(String name, DomainString text)
            throws IOException {
        String mlstr = text.toMultiLangString(langSeparator, lineSeparator);
        attribute(name, mlstr);
    }

    @Override
    public String toString() {
        return "Writer=" + writer;
    }

}
