package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

/**
 * Keyed-Tag: The first word in the text is treated as the key for the tag.
 */
public class LocaleSpecI18nStringTagType
        extends AbstractTagType {

    @Override
    public iString parseJavadoc(String tagNameSpec, Object cont, String string, INegotiation negotiation)
            throws ParseException {

        XiString istr = (XiString) cont;
        if (istr == null)
            istr = new XiString();

        istr.put(tagNameSpec, string);
        return istr;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object _istr, INegotiation negotiation)
            throws FormatException, IOException {

        iString istr = (iString) _istr;

        for (Entry<String, String> ent : istr.entrySet()) {
            String path = ent.getKey();
            String tagName;

            if (path.isEmpty())
                tagName = rootTagName;
            else
                tagName = rootTagName + "." + path;

            writer.writeTag(tagName, ent.getValue());
        }
    }

    @GeneratedByCopyPaste(I18nStringTagType.class)
    @Override
    public iString parseEntry(Object cont, String suffix, String string, INegotiation negotiation)
            throws ParseException {
        iString text = XiString.parseMultiLangString(string);
        return text;
    }

    @GeneratedByCopyPaste(I18nStringTagType.class)
    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws IOException {
        iString dstr = (iString) value;

        String indent = out.getIndent();
        String domainSep = "\n" + indent + "    ";
        String lineSep = "\n" + indent + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(prefix, mlstr);
    }

}
