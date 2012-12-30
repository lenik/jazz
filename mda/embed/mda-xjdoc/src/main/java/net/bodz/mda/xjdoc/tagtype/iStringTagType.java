package net.bodz.mda.xjdoc.tagtype;

import java.io.IOException;

import net.bodz.bas.i18n.dom.iString;
import net.bodz.bas.i18n.dom.XiString;
import net.bodz.bas.rtx.INegotiation;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class iStringTagType
        extends AbstractTagType {

    /**
     * @param cont
     *            is skipped: if there are duplicated tags (with the same tagname), take last one.
     * @return {@link iString}.
     */
    @Override
    public Object parseJavadoc(Object cont, String string, INegotiation negotiation) {
        iString text = XiString.parseParaLangString(string);
        return text;
    }

    @Override
    public String[] formatJavadoc(Object value, INegotiation negotiation) {
        if (value == null)
            return null;
        iString text = (iString) value;
        return new String[] { text.toParaLangString() };
    }

    @Override
    public iString parseEntry(Object cont, String suffix, String string, INegotiation negotiation) {
        iString text = XiString.parseMultiLangString(string);
        return text;
    }

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

    static final iStringTagType INSTANCE = new iStringTagType();

    public static iStringTagType getInstance() {
        return INSTANCE;
    }

}
