package net.bodz.bas.i18n.dom;

public class MultiLangStringParser
        extends MultiTagStringParser {

    private DomainString domainString;

    /**
     * Parse multi-lang string in format of <code>lang1 "..." lang2 "..."</code> into domain string.
     * 
     * @return The parsed {@link DomainString domain string}.
     */
    @Override
    public synchronized DomainString parse(String s) {
        super.parse(s);
        return domainString;
    }

    @Override
    protected void reset() {
        super.reset();
        domainString = new DomainString();
    }

    @Override
    protected void commit(String tag, String string) {
        domainString.create(tag, string);
    }

    @Override
    public String toString() {
        return String.valueOf(domainString);
    }

}
