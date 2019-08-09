package net.bodz.bas.c.java.util.regex;

public class QuotedStringProcessor
        extends TextPrepByParts {

    protected final QuoteFormat quoteFormat;

    public QuotedStringProcessor(QuoteFormat quoteFormat) {
        super(quoteFormat.quotePattern);
        this.quoteFormat = quoteFormat;
    }

    public QuotedStringProcessor(char quoteChar) {
        this(new QuoteFormat(quoteChar));
    }

    public QuotedStringProcessor(char... quoteChars) {
        this(new QuoteFormat(quoteChars));
    }

    @Override
    protected String matched(String part) {
        String dequoted = processQuotedText(part);
        return dequoted;
    }

    @Override
    protected String unmatched(String part) {
        String s = processNormalText(part);
        return s;
    }

    protected String processNormalText(String s) {
        return s;
    }

    protected String processQuotedText(String quotedText) {
        String body = quotedText.substring(quoteFormat.quoteOpenLen, quotedText.length() - quoteFormat.quoteCloseLen);
        return Unescape.unescape(body);
    }

}
