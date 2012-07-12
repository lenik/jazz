package net.bodz.bas.text.flatf;

import java.io.IOException;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.sio.ICharOut;

public class FlatfOutput
        implements IFlatfOutput {

    ICharOut out;
    String indent;
    int depth;
    String langSeparator;
    String lineSeparator;

    public FlatfOutput(ICharOut out) {
        this.out = out;
        setIndent("    ");
    }

    @Override
    public ICharOut getCharOut() {
        return out;
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
    public void pi(String command, String data)
            throws IOException {
        out.write("%" + command + " " + data + "\n");
    }

    @Override
    public void sectionBegin(String sectionName)
            throws IOException {
        out.write("\n[" + sectionName + "]\n");
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
            out.write(indent);
        out.write(name + " = " + linecont + "\n");
    }

    @Override
    public void attribute(String name, DomainString text)
            throws IOException {
        String mlstr = text.toMultiLangString(langSeparator, lineSeparator);
        attribute(name, mlstr);
    }

    @Override
    public String toString() {
        return "Writer=" + out;
    }

}
