package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.rtx.IOptions;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class I18nStringTagType
        extends AbstractTagType {

    /**
     * @param cont
     *            is skipped: if there are duplicated tags (with the same tagname), take last one.
     * @return {@link iString}.
     */
    @Override
    public iString parseJavadoc(String tagNameSpec, Object cont, String string, IOptions options) {
        iString text = XiString.parseParaLangString(string);
        return text;
    }

    @Override
    public void writeJavadoc(String rootTagName, IJavadocWriter writer, Object value, IOptions options)
            throws IOException {
        if (value != null) {
            iString istr = (iString) value;
            String paraLang = istr.toParaLangString();
            writer.writeTag(rootTagName, paraLang);
        }
    }

    @Override
    public iString parseEntry(Object cont, String suffix, String string, IOptions options)
            throws ParseException {
        iString text = XiString.parseMultiLangString(string);
        return text;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, IOptions options)
            throws IOException {
        iString dstr = (iString) value;

        String hskip = out.getHskip();
        String domainSep = "\n" + hskip + "    ";
        String lineSep = "\n" + hskip + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(prefix, mlstr);
    }

    static final I18nStringTagType INSTANCE = new I18nStringTagType();

    public static I18nStringTagType getInstance() {
        return INSTANCE;
    }

}
