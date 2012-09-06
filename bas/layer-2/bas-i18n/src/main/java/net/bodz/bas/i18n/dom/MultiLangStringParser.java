package net.bodz.bas.i18n.dom;

public class MultiLangStringParser
        extends MultiTagStringParser {

    private XDomainString domainString;

    /**
     * Parse multi-lang string in format of <code>lang1 "..." lang2 "..."</code> into domain string.
     * 
     * @return The parsed {@link DomainString domain string}.
     */
    @Override
    public synchronized XDomainString parse(String s) {
        super.parse(s);
        return domainString;
    }

    @Override
    protected void reset() {
        super.reset();
        domainString = new XDomainString();
    }

    @Override
    protected void commit(String tag, String string) {
        domainString.put(tag, string);
    }

    @Override
    public String toString() {
        return String.valueOf(domainString);
    }

}
