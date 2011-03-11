package net.bodz.bas.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class QuotedSplitProcessor
        extends QuotedStringProcessor {
    private final boolean dequote;
    private final Pattern delimitorPattern;
    private final int limit;

    private int remainingPartCount;
    private List<String> parts;
    private StringBuilder partBuffer;

    public QuotedSplitProcessor(QuoteFormat quoteFormat, boolean dequote, String delimitor, int limit) {
        super(quoteFormat);
        if (delimitor == null)
            throw new NullPointerException("delimitor");
        this.dequote = dequote;
        this.delimitorPattern = Pattern.compile(delimitor);
        this.limit = limit;
    }

    @Override
    protected void matched(String part) {
        if (dequote)
            part = processQuotedText(part);
        partBuffer.append(part);
    }

    @Override
    protected void unmatched(String text) {
        String[] v = delimitorPattern.split(text, remainingPartCount);
        if (v.length == 0) // Expected??
            return;
        for (int i = 0; i < v.length - 1; i++) {
            partBuffer.append(v[i]);
            parts.add(partBuffer.toString());
            partBuffer.setLength(0);
        }
        remainingPartCount -= v.length - 1;
        partBuffer.append(v[v.length - 1]);
    }

    public synchronized List<String> splitList(String s) {
        if (limit <= 0) {
            remainingPartCount = Integer.MAX_VALUE;
            parts = new ArrayList<String>();
        } else {
            remainingPartCount = limit;
            parts = new ArrayList<String>(limit);
        }
        partBuffer = new StringBuilder();
        process(s);
        parts.add(partBuffer.toString());
        return parts;
    }

    public String[] split(String s) {
        return splitList(s).toArray(new String[0]);
    }

}
