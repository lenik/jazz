package net.bodz.mda.xjdoc.tags;

import java.io.IOException;

import net.bodz.bas.i18n.dom.DomainString;
import net.bodz.bas.i18n.dom.XDomainString;
import net.bodz.bas.lang.negotiation.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class DocTagType
        extends AbstractTagType {

    /**
     * @param cont
     *            is skipped: if there are duplicated tags (with the same tagname), take last one.
     * @return {@link DomainString}.
     */
    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation) {
        DomainString text = XDomainString.parseParaLang(string);
        return text;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation) {
        if (value == null)
            return null;
        DomainString text = (DomainString) value;
        return new String[] { text.toParaLangString() };
    }

    @Override
    public Object parseEntry(Object cont, String suffix, String string, INegotiation negotiation) {
        DomainString text = XDomainString.parseMultiLang(string);
        return text;
    }

    @Override
    public void writeEntries(IFlatfOutput out, String prefix, Object value, INegotiation negotiation)
            throws IOException {
        DomainString dstr = (DomainString) value;

        String indent = out.getIndent();
        String domainSep = "\n" + indent + "    ";
        String lineSep = "\n" + indent + "        ";
        String mlstr = dstr.toMultiLangString(domainSep, lineSep);

        // out.attribute(prefix, dstr);
        out.attribute(prefix, mlstr);
    }

    static final DocTagType INSTANCE = new DocTagType();

    public static DocTagType getInstance() {
        return INSTANCE;
    }

}
