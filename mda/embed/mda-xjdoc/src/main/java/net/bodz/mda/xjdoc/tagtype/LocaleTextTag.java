package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.CopyAndPaste;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.model.AbstractDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public class LocaleTextTag
        extends AbstractDocTag<iString> {

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        XiString xs = (XiString) data;
        if (xs == null)
            xs = new XiString();

        String path = extension == null ? null : extension.replace('.', '-');
        xs.put(path, javadoc);
    }

    @Override
    public void writeJavadoc(String name, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        iString istr = data;

        for (Entry<String, String> ent : istr.entrySet()) {
            String path = ent.getKey();
            String tagName;

            if (path.isEmpty())
                tagName = name;
            else
                tagName = name + "." + path.replace('-', '.');

            writer.writeTag(tagName, ent.getValue());
        }
    }

    @CopyAndPaste(TextTag.class)
    @Override
    public void parseAttribute(String attributeName, String extension, String string, IOptions options)
            throws ParseException {
        data = MultiLangStrings.parse(string);
    }

    @CopyAndPaste(TextTag.class)
    @Override
    public void writeObject(IFlatfOutput out, String name, IOptions options)
            throws IOException, FormatException {
        iString dstr = getText();
        if (dstr != null) {
            String indent = out.getHskip();
            String domainSep = "\n" + indent + "    ";
            String lineSep = "\n" + indent + "        ";
            String mlstr = dstr.toMultiLangString(domainSep, lineSep);

            // out.attribute(prefix, dstr);
            out.attribute(name, mlstr);
        }
    }

}
