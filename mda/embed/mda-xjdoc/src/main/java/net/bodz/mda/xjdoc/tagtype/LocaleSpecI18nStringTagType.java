package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;
import java.util.Map.Entry;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.meta.codegen.GeneratedByCopyPaste;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;

/**
 * Keyed-Tag: The first word in the text is treated as the key for the tag.
 */
public class LocaleSpecI18nStringTagType
        extends AbstractTagType {

    @Override
    public iString parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options)
            throws ParseException {

        XiString istr = (XiString) cont;
        if (istr == null)
            istr = new XiString();

        String path = tagNameSpec == null ? null : tagNameSpec.replace('.', '-');
        istr.put(path, string);
        return istr;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object _istr, IOptions options)
            throws IOException {

        iString istr = (iString) _istr;

        for (Entry<String, String> ent : istr.entrySet()) {
            String path = ent.getKey();
            String tagName;

            if (path.isEmpty())
                tagName = rootTagName;
            else
                tagName = rootTagName + "." + path.replace('-', '.');

            writer.writeTag(tagName, ent.getValue());
        }
    }

    @GeneratedByCopyPaste(I18nStringTagType.class)
    @Override
    public iString parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException {
        iString text = iString.fn.parseMultiLangString(string);
        return text;
    }

    @GeneratedByCopyPaste(I18nStringTagType.class)
    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
        iString dstr = (iString) value;

        String indent = out.getHskip();
        String domainSep = "\n" + indent + "    ";
        String lineSep = "\n" + indent + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(prefix, mlstr);
    }

}
