package net.bodz.mda.xjdoc.meta;

import net.bodz.bas.i18n.dstr.DomainString;

public class IndexedDocTagType
        extends IndexedTagType {

    /**
     * @param valueCont
     *            is skipped, if multiple dstr-tag occurred, only the last one is used.
     * @return {@link DomainString}.
     */
    @Override
    protected Object parseValue(Object cont, String valueString) {
        DomainString dstr = DomainString.parseParaLang(valueString);
        return dstr;
    }

    @Override
    protected String formatValue(Object value) {
        if (value == null)
            return null;
        DomainString dstr = (DomainString) value;
        return dstr.toString();
    }

}
