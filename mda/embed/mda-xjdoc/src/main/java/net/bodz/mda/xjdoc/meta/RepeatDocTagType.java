package net.bodz.mda.xjdoc.meta;

import java.io.IOException;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class RepeatDocTagType
        extends RepeatTagType {

    /**
     * @param valueCont
     *            is skipped, if multiple dstr-tag occurred, only the last one is used.
     * @return {@link DomainString}.
     */
    @Override
    protected Object parseValueJavadoc(String valueString) {
        DomainString dstr = DomainString.parseParaLang(valueString);
        return dstr;
    }

    @Override
    protected String formatValueJavadoc(Object value) {
        if (value == null)
            return null;
        DomainString dstr = (DomainString) value;
        return dstr.toParaLangString();
    }

    @Override
    protected Object parseValueAttribute(String string) {
        DomainString dstr = DomainString.parseMultiLangString(string);
        return dstr;
    }

    @Override
    protected void writeValueAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        DomainString dstr = (DomainString) value;

        String indent = out.getIndent();
        String domainSep = "\n" + indent + "    ";
        String lineSep = "\n" + indent + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(prefix, mlstr);
    }

}
