package net.bodz.bas.i18n.dom;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.meta.decl.ThreadUnsafe;

@ThreadUnsafe
public class MultiLangStringParser
        extends MultiTagStringParser {

    private XiString istr;

    /**
     * Parse multi-lang string in format of <code>lang1 "..." lang2 "..."</code> into domain string.
     *
     * @return The parsed {@link iString domain string}.
     * @throws ParseException
     */
    @Override
    public synchronized XiString parse(String s)
            throws ParseException {
        super.parse(s);
        return istr;
    }

    @Override
    protected void reset() {
        super.reset();
        istr = new XiString();
    }

    @Override
    protected void acceptTaggedString(String tag, String string) {
        istr.put(tag, string);
    }

    @Override
    public String toString() {
        return String.valueOf(istr);
    }

}
