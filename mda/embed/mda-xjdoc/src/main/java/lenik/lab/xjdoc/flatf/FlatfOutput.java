package lenik.lab.xjdoc.flatf;

import java.io.IOException;
import java.io.Writer;

import lenik.lab.xjdoc.dstr.DomainString;

public class FlatfOutput
        implements IFlatfOutput {

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

    public String getIndent() {
        return indent;
    }

    public void setIndent(String indent) {
        if (indent == null)
            throw new NullPointerException("indent");
        this.indent = indent;
        langSeparator = "\n" + indent;
        lineSeparator = "\n" + indent + indent;
    }

    @Override
    public void sectionBegin(String sectionName)
            throws IOException {
        writer.append("[" + sectionName + "]\n");
    }

    @Override
    public void sectionEnd()
            throws IOException {
        writer.append("\n");
    }

    @Override
    public void attribute(String name, String text)
            throws IOException {
        writer.write(indent + name + " = " + text + "\n");
    }

    public void attribute(String name, DomainString dstr)
            throws IOException {
        String mlstr = dstr.toMultiLangString("\n    ", "\n        ");
        attribute(name, mlstr);
    }

}
