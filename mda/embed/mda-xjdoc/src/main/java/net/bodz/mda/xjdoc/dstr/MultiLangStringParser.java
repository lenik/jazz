package net.bodz.mda.xjdoc.dstr;

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
        domainString = null;
    }

    @Override
    protected void commit(String tag, String string) {
        if (tag == null) {
            if (domainString != null)
                throw new IllegalStateException("The default lang must be the first.");
            domainString = new DomainString(string);
        } else {
            if (domainString == null)
                domainString = new DomainString(null, null); // No default value.
            domainString.resolve(tag, string);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(domainString);
    }

}
