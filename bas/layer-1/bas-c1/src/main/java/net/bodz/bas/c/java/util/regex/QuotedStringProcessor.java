package net.bodz.bas.c.java.util.regex;

import java.io.IOException;

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
    protected void matched(CharSequence in, int start, int end, Appendable out)
            throws IOException {
        processQuotedText(in, start, end, out);
    }

    @Override
    protected void unmatched(CharSequence in, int start, int end, Appendable out)
            throws IOException {
        processNormalText(in, start, end, out);
    }

    protected void processNormalText(CharSequence in, int start, int end, Appendable out)
            throws IOException {
        out.append(in, start, end);
    }

    protected String processQuotedText(CharSequence in, int start, int end, Appendable out) {
        // String quotedText = in.substring(start, end);
        int bodyStart = start + quoteFormat.quoteOpenLen;
        int bodyEnd = end - quoteFormat.quoteCloseLen;
        String body = in.subSequence(bodyStart, bodyEnd).toString();
        return Unescape.unescape(body);
    }

}
