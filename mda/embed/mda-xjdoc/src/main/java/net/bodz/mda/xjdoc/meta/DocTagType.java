package net.bodz.mda.xjdoc.meta;

import java.io.IOException;

import net.bodz.bas.i18n.dstr.DomainString;
import net.bodz.bas.text.flatf.IFlatfOutput;

public class DocTagType
        extends ScalarTagType {

    /**
     * @param cont
     *            is skipped, if multiple dstr-tag occurred, only the last one is used.
     * @return {@link DomainString}.
     */
    @Override
    public Object parseJavadoc(Object cont, String string) {
        DomainString text = DomainString.parseParaLang(string);
        return text;
    }

    @Override
    public String[] formatJavadoc(Object value) {
        if (value == null)
            return null;
        DomainString text = (DomainString) value;
        return new String[] { text.toParaLangString() };
    }

    @Override
    public Object parseAttribute(Object cont, String suffix, String string) {
        DomainString text = DomainString.parseMultiLangString(string);
        return text;
    }

    @Override
    public void writeAttributes(IFlatfOutput out, String prefix, Object value)
            throws IOException {
        DomainString text = (DomainString) value;
        out.attribute(prefix, text);
    }

    public static final DocTagType INSTANCE = new DocTagType();

}
