package net.bodz.mda.xjdoc.tagtype;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.flatf.IFlatfOutput;
import net.bodz.bas.i18n.dom.MultiLangStrings;
import net.bodz.bas.i18n.dom.ParaLangStrings;
import net.bodz.bas.i18n.dom.iString;

public class HeadedTextTag
        extends HeadedDocTag<iString> {

    @Override
    protected iString parseJavadoc(String s)
            throws ParseException {
        return ParaLangStrings.parse(s);
    }

    @Override
    protected String formatJavadoc(iString value) {
        return value.toParaLangString();
    }

    @Override
    protected iString parseFlatf(String s)
            throws ParseException {
        return MultiLangStrings.parse(s);
    }

    protected String formatFlatf(IFlatfOutput out, iString value) {
        iString dstr = this.getText();

        String hskip = out.getHskip();
        String domainSep = "\n" + hskip + "    ";
        String lineSep = "\n" + hskip + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        return mlstr;
    }

}
