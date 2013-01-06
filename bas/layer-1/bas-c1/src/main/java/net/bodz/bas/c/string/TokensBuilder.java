package net.bodz.bas.c.string;

public class TokensBuilder
        extends AbstractStringBuilder {

    private static final long serialVersionUID = 1L;

    private final String tokenSeparator;

    public TokensBuilder(String separator) {
        this(separator, 16);
    }

    public TokensBuilder(String separator, int capacity) {
        super(capacity);
        if (separator == null)
            throw new NullPointerException("tokenSeparator");
        this.tokenSeparator = separator;
    }

    public void appendToken(String token) {
        if (length() != 0)
            append(tokenSeparator);
        append(token);
    }

}
