package net.bodz.bas.i18n.dom;

import java.util.Map.Entry;

public class MultiLangStringFormatter {

    char quoteStartChar = '"';
    char quoteEndChar = '"';
    String domainSeparator = " ";
    String lineSeparator;

    public char getQuoteStartChar() {
        return quoteStartChar;
    }

    public void setQuoteStartChar(char quoteStartChar) {
        this.quoteStartChar = quoteStartChar;
    }

    public char getQuoteEndChar() {
        return quoteEndChar;
    }

    public void setQuoteEndChar(char quoteEndChar) {
        this.quoteEndChar = quoteEndChar;
    }

    public String getDomainSeparator() {
        return domainSeparator;
    }

    public void setDomainSeparator(String domainSeparator) {
        this.domainSeparator = domainSeparator;
    }

    public String getLineSeparator() {
        return lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        this.lineSeparator = lineSeparator;
    }

    public String format(DomainString dstr) {
        StringBuilder sb = new StringBuilder();
        for (Entry<String, DomainString> trEntry : dstr) {
            String lang = trEntry.getKey();
            String text = trEntry.getValue().getValue();
            if (text == null)
                continue;
            if (sb.length() != 0)
                sb.append(domainSeparator);
            if (lang != null)
                sb.append(lang);
            if (sb.length() != 0)
                sb.append(' ');
            quote(sb, text);
        }
        return sb.toString();
    }

    void quote(StringBuilder out, String s) {
        out.append(quoteStartChar);
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
            case '\n':
                out.append("\\n");
                if (lineSeparator != null) {
                    out.append(quoteEndChar);
                    out.append(lineSeparator);
                    out.append(quoteStartChar);
                }
                continue;
            case '\t':
                out.append("\\t");
                continue;
            case '\\':
            case '\"':
            case '\'':
                out.append('\\');
                break;
            }
            out.append(ch);
        }
        out.append(quoteEndChar);
    }

}
