package net.bodz.mda.xjdoc.meta;

import net.bodz.bas.i18n.dstr.DomainString;

public class DocTagType
        extends AbstractTagType {

    /**
     * @param cont
     *            is skipped, if multiple dstr-tag occurred, only the last one is used.
     * @return {@link DomainString}.
     */
    @Override
    public Object parse(Object cont, String string) {
        DomainString dstr = DomainString.parseParaLang(string);
        return dstr;
    }

    @Override
    public String[] format(Object value) {
        if (value == null)
            return null;
        DomainString dstr = (DomainString) value;
        return new String[] { dstr.toString() };
    }

    public static final DocTagType INSTANCE = new DocTagType();

}
