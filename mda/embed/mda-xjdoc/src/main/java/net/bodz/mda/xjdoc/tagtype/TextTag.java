package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.ParaLangStrings;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.mda.xjdoc.model.AbstractDocTag;
import net.bodz.mda.xjdoc.model.javadoc.IJavadocWriter;

public class TextTag
        extends AbstractDocTag<iString> {

    public TextTag() {
    }

    public TextTag(iString text) {
        this.data = text;
    }

    @Override
    public void parseJavadoc(String tagName, String extension, String javadoc, IOptions options)
            throws ParseException {
        data = ParaLangStrings.parse(javadoc);
    }

    @Override
    public void writeJavadoc(String annotation, IJavadocWriter writer, IOptions options)
            throws IOException, FormatException {
        if (data != null) {
            iString istr = data;
            String paraLang = istr.toParaLangString();
            writer.writeTag(annotation, paraLang);
        }
    }

    @Override
    public void parseAttribute(String attributeName, String extension, String text, IOptions options)
            throws ParseException {
        data = MultiLangStrings.parse(text);
    }

    @Override
    public void writeObject(IFlatfOutput out, String attributeName, IOptions options)
            throws IOException, FormatException {
        iString dstr = this.getText();

        String hskip = out.getHskip();
        String domainSep = "\n" + hskip + "    ";
        String lineSep = "\n" + hskip + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(attributeName, mlstr);
    }

}
