package net.bodz.mda.xjdoc.tagtype;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.err.ParseException;

public class TokensTagType
        extends AbstractScalarTagType<String[]> {

    private String separator;
    private boolean normalizeSpace;
    private boolean removeEmptyToken;

    public TokensTagType(String separator, boolean normalizeSpace) {
        if (separator == null)
            throw new NullPointerException("separator");
        this.separator = separator;
        this.normalizeSpace = normalizeSpace;
        if (separator.equals(" "))
            removeEmptyToken = true;
    }

    @Override
    public Class<?> getValueType() {
        return String[].class;
    }

    @Override
    protected String[] parse(String s)
            throws ParseException {
        List<String> tokenList = _parse(s);
        return tokenList.toArray(new String[0]);
    }

    List<String> _parse(String s)
            throws ParseException {
        List<String> tokenList = new ArrayList<String>();

        int len = s.length();
        int endptr = 0;
        while (endptr < len) {
            int pos = s.indexOf(separator, endptr);
            if (pos == -1)
                pos = len;

            String token = s.substring(endptr, pos);
            endptr = pos + separator.length();

            if (normalizeSpace)
                token = token.trim();

            if (removeEmptyToken && token.isEmpty())
                continue;

            tokenList.add(token);
        }
        return tokenList;
    }

    @Override
    public String format(String[] array) {
        StringBuilder sb = new StringBuilder(array.length * 32);
        for (int index = 0; index < array.length; index++) {
            if (index != 0) {
                if (normalizeSpace)
                    sb.append(' ');
                sb.append(separator);
            }
            sb.append(array[index]);
        }
        return sb.toString();
    }

    public static final TokensTagType SPACE_SEPARATED = new TokensTagType(" ", true);
    public static final TokensTagType COMMA_SEPARATED = new TokensTagType(",", true);

}
